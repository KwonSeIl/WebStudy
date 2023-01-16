package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
// Connection�� �̸� ���� -> ������ Connection�� �ּҸ� �� ���
// Connection�� ��ü ������ ������ �� �ִ� -> �޸� ����
// ���ῡ �ҿ�Ǵ� �ð��� ���� �� �ִ� -> ���� �ӵ��� ���� �Ѵ�
// ���� ������ �ٿ���� �ʴ´�
// �� ���α׷��� �Ϲ�ȭ
// MyBatis, JPA ==> ���̺귯�� (DBCP)

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

public class FoodDAO {
	private Connection conn; //�̸� �����(��Ĺ)
	private PreparedStatement ps; //�ۼ���
	//Connection�� �ּ� ���
	public void getConnection()
	{
		//Ž���� ���� => Ž���� ���� -> C����̹��� ���� -> Connection ��ü �ּ� ������ ����
		try
		{
			Context itit=new  InitialContext();
			Context c=(Context)itit.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		}catch(Exception ex) {}
	}
	//��ȯ
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			// POOL�� ��ȯ(POOL: Connection ��ü�� ����� �޸� ����)
		}catch(Exception ex) {}
	}
	//DB ���� => ���
	//1. ī�װ� �б�
	public ArrayList<CategoryVO> categoryListData()
	{
		ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		try
		{
			getConnection();
			String sql="SELECT /*+ INDEX_ASC(project_category pc_cno_pk)*/ cno,title,subject,poster "
					+"FROM project_category";
			ps=conn.prepareStatement(sql);
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
			disConnection(); //��ȯ
		}
		return list;
	}
	//ī�װ��� ���� ���
	public ArrayList<FoodVO> foodListData(int cno)
	{
		ArrayList<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			getConnection();
			String sql="SELECT /*+ INDEX_ASC(project_food pf_fno_pk)*/ fno,name,score,address,tel,type,poster "
					+"FROM project_food "
					+"WHERE cno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setScore(rs.getDouble(3));
				String addr=rs.getString(4);
				addr=addr.substring(0, addr.lastIndexOf("��")); //${fn:substring()}
				vo.setAddress(addr.trim());
				vo.setTel(rs.getString(5));
				vo.setType(rs.getString(6));
				String poster=rs.getString(7);
				poster=poster.substring(0, poster.indexOf("^"));
				vo.setPoster(poster);
				
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
	//ī�װ� ����
	public CategoryVO categoryInfoData(int cno)
	{
		CategoryVO vo=new CategoryVO();
		try
		{
			getConnection();
			String sql="SELECT title,subject FROM project_category "
					+"WHERE cno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setTitle(rs.getString(1));
			vo.setSubject(rs.getString(2));
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
	//DAO(����Ŭ�� ����� ������ ������ ����) <======> JSP
	//JSP���� �ڹ� / HTML�� �и�
	//DAO ==> �ڹ�(Model) ==> HTML(View) ====> MV ����
	//JSTL�� ����, ${} EL
	
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

}
