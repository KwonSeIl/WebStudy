package com.sist.dao;
import java.util.*;

import com.sist.vo.NoticeVO;

import java.sql.*;
public class NoticeDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	//Footer에 저장 => 상위 5개 가져올 것
	public List<NoticeVO> noticeTop5()
	{
		List<NoticeVO> list=new ArrayList<NoticeVO>();
		try
		{
			conn=CreateConnection.getConnection();
			String sql="SELECT no,name,subject,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS'),rownum "
					+"FROM (SELECT no,name,subject,regdate "
					+"FROM project_notice ORDER BY hit DESC) "
					+"WHERE rownum<=5"; //Top-N (인기순위)
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				NoticeVO vo=new NoticeVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setDbday(rs.getString(4));
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	/*
	 * 	type
	 * 	1. [일반공지]
	 *  2. [이번트공지]
	 *  3. [맛집공지]
	 *  4. [스토어 공지]
	 *  5. [서울여행 공지]
	 */
	// List,Detail => user/admin이 동일 pro_no_pk
	public List<NoticeVO> noticeListData(int page)
	{
		List<NoticeVO> list=new ArrayList<NoticeVO>();
		try
		{
			conn=CreateConnection.getConnection();
			String sql="SELECT no,type,name,subject,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS'),hit,rownum "
					+"FROM (SELECT no,type,name,subject,regdate,hit,rownum as num "
					+"FROM (SELECT /*+ INDEX_DESC(project_notice pro_no_pk)*/ no,type,name,subject,regdate,hit "
					+"FROM project_notice)) "
					+"WHERE num BETWEEN ? AND ?"; //인라인뷰
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				NoticeVO vo=new NoticeVO();
				vo.setNo(rs.getInt(1));
				vo.setType(rs.getInt(2));
				vo.setName(rs.getString(3));
				vo.setSubject(rs.getString(4));
				vo.setDbday(rs.getString(5));
				vo.setHit(rs.getInt(6));
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	public int noticeTotalPage()
	{
		int total=0;
		try
		{
			conn=CreateConnection.getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM project_notice";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			CreateConnection.disConnection(conn, ps);
		}
		return total;
	}
}
