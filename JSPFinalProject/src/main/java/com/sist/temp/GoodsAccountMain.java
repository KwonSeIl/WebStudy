package com.sist.temp;
import java.util.*;
import java.sql.*;
public class GoodsAccountMain {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public GoodsAccountMain()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	public void goodsAccountInsert(int no,int account)
	{
		try
		{
			getConnection();
			String sql="UPDATE goods_all SET "
					+"account=?"
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, account);
			ps.setInt(2, no);
			ps.executeUpdate();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	
	public static void main(String[] args) {
		GoodsAccountMain gm=new GoodsAccountMain();
		for(int i=1;i<=6184;i++)
		{
			int rand=(int)(Math.random()*40)+10;
			gm.goodsAccountInsert(i, rand);
			try
			{
				Thread.sleep(30); //for문 많아서 열고 닫고 할 시간 없음 => 열고 닫을 시간 0.3초 설정
			}catch(Exception ex) {}
			System.out.println("i="+i);
		}
		System.out.println("수량 추가 종료!!");
	}
}
