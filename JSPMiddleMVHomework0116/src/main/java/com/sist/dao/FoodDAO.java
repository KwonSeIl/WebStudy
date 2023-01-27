package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import com.sist.vo.FoodVO;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	

	public void getConnection()
	{
		try
		{
			Context init=new InitialContext();
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle"); 
			conn=ds.getConnection();
		}catch(Exception ex) {}
	}
	
	//Connection 
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	
	public FoodVO foodDetailData(int fno)
	{
		   FoodVO vo=new FoodVO();
		   try
		   {
			   getConnection();
			   String sql="SELECT fno,name,score,poster,tel,type,time,parking,menu,price,address "
					     +"FROM food_location "
					     +"WHERE fno=?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, fno);
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   vo.setFno(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setScore(rs.getDouble(3));
			   vo.setPoster(rs.getString(4));
			   vo.setTel(rs.getString(5));
			   vo.setType(rs.getString(6));
			   vo.setTime(rs.getString(7));
			   vo.setParking(rs.getString(8));
			   vo.setMenu(rs.getString(9));
			   vo.setPrice(rs.getString(10));
			   vo.setAddress(rs.getString(11));
			   rs.close();
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   disConnection();
		   }
		   return vo;
	}
	public ArrayList<FoodVO> FoodListData()
	   {
	      ArrayList<FoodVO> list=new ArrayList<FoodVO>();
	      try
	      {
	         getConnection();
	         String sql="SELECT fno,name,poster "
	                  +"FROM food_location "
	        		  +"WHERE fno<40";
	         ps=conn.prepareStatement(sql);
	         ResultSet rs=ps.executeQuery();
	         while(rs.next())
	         {
	            FoodVO vo=new FoodVO();
	            vo.setFno(rs.getInt(1));
	            vo.setName(rs.getString(2));
	            vo.setPoster(rs.getString(3));
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
