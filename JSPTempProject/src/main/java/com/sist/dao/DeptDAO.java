package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.*;
public class DeptDAO {
	// ����Ŭ ���� ��ü
		private Connection conn;
		// ����Ŭ �ۼ��� ��ü (SQL ������ -> ������ �� �ޱ�)
		private PreparedStatement ps;
		// ����Ŭ ���� �ּ� ����
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		
		// ����̹� ����
		public DeptDAO()
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch(Exception ex) {}
		}
		// ����Ŭ ����
		public void getConnection()
		{
			try
			{
				conn=DriverManager.getConnection(URL,"hr","happy");
			}catch(Exception ex) {}
		}
		// ����Ŭ �ݱ�
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
