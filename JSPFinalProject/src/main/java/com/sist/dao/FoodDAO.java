package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
//DAO => Model => (DispatcherServlet) JSP
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	//기능
	//1) 카테고리 읽기
	public ArrayList<CategoryVO> foodCategoryData()
	{
		ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		try
		{
			conn=CreateConnection.getConnection();
			String sql="SELECT /*+ INDEX_ASC(project_category pc_cno_pk)*/cno,title,subject,poster "
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
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	//검색 => DAO, VO, JSP
	public ArrayList<FoodVO> foodLocationFindData(int page,String ss)
	{
		ArrayList<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			conn=CreateConnection.getConnection();
			String sql="SELECT fno,name,poster,num "
					+ "FROM (SELECT fno,name,poster,rownum as num) "
					+"FROM (SELECT fno,name,poster "
					+"FROM food_location "
					+"WHERE address LIKE '%'||?||'%')) "
					+"WHERE num BETWEEN ? AND ?";
			//인라인뷰 => Top-N만 가능
			//인기순위 5개
			ps=conn.prepareStatement(sql);
			int rowSize=20;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			ps.setString(1, ss);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				String poster=rs.getString(3);
				poster=poster.substring(0,poster.indexOf("^"));
				poster=poster.replace("#", "&");
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
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	public int foodLocationTotalPage(String ss)
	{
		int total=0;
		try
		{
			conn=CreateConnection.getConnection();
			String sql="SELECT CEIL(COUNT(*)/20.0) FROM food_location "
					+"WHERE address LIKE '%'||?||'%'";
			// WHERE REGEXP_LIKE(address,?);
			ps=conn.prepareStatement(sql);
			ps.setString(1, ss);
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
