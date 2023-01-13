package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.*;
public class DeptDAO {
	// 오라클 연결 객체
		private Connection conn;
		// 오라클 송수신 객체 (SQL 보내고 -> 데이터 값 받기)
		private PreparedStatement ps;
		// 오라클 연결 주소 설정
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		
		// 드라이버 연결
		public DeptDAO()
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch(Exception ex) {}
		}
		// 오라클 연결
		public void getConnection()
		{
			try
			{
				conn=DriverManager.getConnection(URL,"hr","happy");
			}catch(Exception ex) {}
		}
		// 오라클 닫기
		public void disConnection()
		{
			try
			{
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception ex) {}
		}
		
		public ArrayList<DeptVO> deptListData()
		{
			ArrayList<DeptVO> list=new ArrayList<DeptVO>();
			try
			{
				getConnection();
				String sql="SELECT deptno,dname,loc "
						+"FROM dept";
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					DeptVO vo=new DeptVO();
					vo.setDeptno(rs.getInt(1));
					vo.setDname(rs.getString(2));
					vo.setLoc(rs.getString(3));
					
					list.add(vo);
				}
				rs.close();
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				disConnection();
			}
			return list;
		}

}
