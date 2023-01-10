package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
public class FoodDAO {
	 // ����Ŭ ���� ��ü 
    private Connection conn;
    // ����Ŭ �ۼ��� ��ü (SQL => �����Ͱ� �ޱ�)
    private PreparedStatement ps;
    // ����Ŭ ���� �ּ� 
    private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
    
    // ����̹� ���� 
    public FoodDAO()
    {
       try
       {
          Class.forName("oracle.jdbc.driver.OracleDriver");
       }catch(Exception ex){}
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
    public ArrayList<CategoryVO> categoryListData(int no)
    {
       ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
       try
       {
          getConnection();
          int s=0,e=0;
          if(no==1)
          {
             s=31;
             e=42;
          }
          else if(no==2)
          {
             s=43;
             e=48;  
          }
          else if(no==3)
          {
             s=49; 
             e=60;
          }
          
          String sql="SELECT cno,title,subject,poster "
                +"FROM project_category "
                +"WHERE cno BETWEEN ? AND ? ";
          ps=conn.prepareStatement(sql);
          ps.setInt(1, s);
          ps.setInt(2, e);
          ResultSet rs=ps.executeQuery();
          while(rs.next())
          {
             CategoryVO vo=new CategoryVO();
             vo.setCno(rs.getInt(1));
             vo.setTitle(rs.getString(2));
             vo.setSubject(rs.getString(3));
             vo.setPoster(rs.getString(4));
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
    // ī�װ��� ���� ��� ���
    public CategoryVO categoryInfoData(int cno)
    {
    	CategoryVO vo=new CategoryVO();
    	try
    	{
    		getConnection();
    		String sql="SELECT title,subject "
    				+"FROM project_category "
    				+"WHERE cno=?";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, cno);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		vo.setTitle(rs.getString(1));
    		vo.setSubject(rs.getString(2));
    		vo.setSubject(rs.getString(3));
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
    // ���� ���
    /*
     * 	�ٽ�
     *  ����) CURD -> ��û / ���� => request, response
     *  ����)���� ��θ� getRealPath()
     *  include() => ���, ��ũ ���
     */
    public ArrayList<FoodVO> category_food_list(int cno)
    {
    	ArrayList<FoodVO> list=new ArrayList<FoodVO>();
    	try
    	{
    		getConnection();
    		String sql="SELECT fno,cno,name,poster,address,score,type,tel "
    				+"FROM project_food "
    				+"WHERE cno=?";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, cno);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			FoodVO vo=new FoodVO();
    			vo.setFno(rs.getInt(1));
    			vo.setCno(rs.getInt(2));
    			vo.setName(rs.getString(3));
    			String poster=rs.getString(4);
    			poster=poster.substring(0,poster.indexOf("^"));
    			poster=poster.replace("#", "&");
    			vo.setPoster(poster);
    			String address=rs.getString(5);
    			address=address.substring(0,address.indexOf("��"));
    			vo.setAddress(address.trim());
    			vo.setScore(rs.getDouble(6));
    			vo.setType(rs.getString(7));
    			vo.setTel(rs.getString(8));
    			list.add(vo);
    		}
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
    
    //�α��� ó��
    public MemberVO isLogin(String id,String pwd)
    {
    	MemberVO vo=new MemberVO();
    	try
    	{
    		getConnection();
    		String sql="SELECT COUNT(*) FROM jsp_member "
    				+"WHERE id=?";
    		//1. ID ���� Ȯ��
    		ps=conn.prepareStatement(sql);
    		ps.setString(1, id);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		int count=rs.getInt(1);
    		rs.close();
    		
    		if(count==0)//ID�� ���� ����
    		{
    			vo.setMsg("NOID");
    		}
    		else //ID�� �����ϴ� ���� 
    		{
    			sql="SELECT pwd,name,sex FROM jsp_member "
    					+"WHERE id=?";
    			ps=conn.prepareStatement(sql);
    			ps.setString(1, id);
    			rs=ps.executeQuery();
    			rs.next();
    			String db_pwd=rs.getString(1);
    			String name=rs.getString(2);
    			String sex=rs.getString(3);
    			rs.close();
    			
    			if(pwd.equals(db_pwd)) //�α��� ����
    			{
    				vo.setMsg("OK");
    				vo.setName(name);
    				vo.setSex(sex);
    			}
    			else //��й�ȣ Ʋ�� ����
    			{
    				vo.setMsg("NOPWD");
    			}
    		}
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
    
    //�󼼺���
    public FoodVO foodDetailData(int fno)
    {
    	FoodVO vo=new FoodVO();
    	try
    	{
    		getConnection();
    		String sql="SELECT fno,name,score,poster,tel,type,time,parking,menu,good,soso,bad,price,address "
    				+"FROM project_food "
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
    		vo.setGood(rs.getInt(10));
    		vo.setSoso(rs.getInt(11));
    		vo.setBad(rs.getInt(12));
    		vo.setPrice(rs.getString(13));
    		vo.setAddress(rs.getString(14));
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
    
}
