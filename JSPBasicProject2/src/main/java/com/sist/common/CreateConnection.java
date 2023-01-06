package com.sist.common;
import java.sql.*;
public class CreateConnection {
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	public CreateConnection()
	{
		try
		{
			Class.forName("oracle.jdbc.dirver.OracleDriver");
		}catch(Exception ex) {}
	}
	public Connection getConnection()
	{
		Connection conn=null;
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
		return conn;
	}
	public void disConncetion(PreparedStatement ps,Connection conn)
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
}
