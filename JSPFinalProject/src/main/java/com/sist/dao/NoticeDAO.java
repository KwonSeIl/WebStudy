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
	/*
	 * 	오라클: JOIN(inner,outer), Subquery
	 * 		  View(inline View) => Top-n
	 * 		  --------------------------- 한개의 기능을 수행 시 SQL 조합
	 *  자바: 변수, 연산자, 제어문, 메소드
	 *  	라이브러리: Collection, java.util.*, java.lang.*
	 *  
	 *  JSP: 내장객체(request,response,session,cookie)
	 *  	 EL, JSTL, MVC
	 *  --------------------------------------------------------
	 *  JavaScript:변수,연산자,제어문,함수,JQUERY,AJAX
	 *  
	 *  => DAO 만드는 방법(DBCP)
	 *  
	 *  => MVC => Spring => Spring-Boot => JSP없이 HTML 출력
	 *  
	 *  => Jquery => Vue => React
	 */
	public void noticeInsert(NoticeVO vo)
	{
		try
		{
			conn=CreateConnection.getConnection();
			String sql="INSERT INTO project_notice VALUES("
					+ "(SELECT NVL(MAX(no)+1,1) FROM project_notice),?,?,?,?,SYSDATE,0)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getType());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getSubject());
			ps.setString(4, vo.getContent());
			ps.executeUpdate(); //commit()
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			CreateConnection.disConnection(conn, ps);
		}
	}
	public void noticeDelete(int no)
	{
		try
		{
			conn=CreateConnection.getConnection();
			String sql="DELETE FROM project_notice "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate(); //commit()
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			CreateConnection.disConnection(conn, ps);
		}
	}
	public NoticeVO noticeUpdateData(int no)
	{
		NoticeVO vo=new NoticeVO();
		try
		{
			conn=CreateConnection.getConnection();
			String sql="SELECT no,name,subject,content,type "
					+"FROM project_notice "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setType(rs.getInt(5));
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			CreateConnection.disConnection(conn, ps);
		}
		return vo;
	}
	public void noticeUpdate(NoticeVO vo)
	   {
	      try
	      {
	         conn=CreateConnection.getConnection();
	         String sql="UPDATE project_notice SET "
	               +"type=?,subject=?,content=? "
	               +"WHERE no=?";
	         ps=conn.prepareStatement(sql);
	         ps.setInt(1, vo.getType());
	         ps.setString(2, vo.getSubject());
	         ps.setString(3, vo.getContent());
	         ps.setInt(4, vo.getNo());
	         ps.executeUpdate();
	      }catch(Exception ex)
	      {
	         ex.printStackTrace();
	      }
	      finally
	      {
	         CreateConnection.disConnection(conn, ps);
	      }
	      
	   }
	   public NoticeVO noticeDetailData(int no)
	   {
	      NoticeVO vo=new NoticeVO();
	      try
	      {
	         conn=CreateConnection.getConnection();
	         String sql="UPDATE project_notice SET "
	        		 +"hit=hit+1 "
	        		 +"WHERE no=?"; 
	         ps=conn.prepareStatement(sql);
	         ps.setInt(1, no);
	         ps.executeUpdate();
	         
	         sql="SELECT no,name,subject,content,type,hit,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
	               +"FROM project_notice "
	               +"WHERE no=?";
	         ps=conn.prepareStatement(sql);
	         ps.setInt(1, no);
	         ResultSet rs=ps.executeQuery();
	         rs.next();
	         vo.setNo(rs.getInt(1));
	         vo.setName(rs.getString(2));
	         vo.setSubject(rs.getString(3));
	         vo.setContent(rs.getString(4));
	         vo.setType(rs.getInt(5));
	         vo.setHit(rs.getInt(6));
	         vo.setDbday(rs.getString(7));
	         rs.close();
	      }catch(Exception ex)
	      {
	         ex.printStackTrace();
	      }
	      finally
	      {
	         CreateConnection.disConnection(conn, ps);
	      }
	      return vo;
	   }
	}
