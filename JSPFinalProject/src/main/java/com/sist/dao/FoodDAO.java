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
			String sql="select fno,name,poster, num "
		               +"from (select fno,name,poster, rownum as num "
		               +"from (select fno,name,poster "
		               +"from food_location "
		               +"where address like '%'||?||'%')) "
		               +"where num between ? and ?";

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
	// () => 안에 들어가는 것: 매개변수 => ?cno=10
	// public List<FoodVO> findByCno(int cno);
	public ArrayList<FoodVO> foodListData(int cno)
	{
		ArrayList<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			conn=CreateConnection.getConnection();
			String sql="SELECT fno,cno,name,address,tel,type,poster,score "
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
				String addr=rs.getString(4);
				addr=addr.substring(0,addr.lastIndexOf("지"));
				vo.setAddress(addr.trim());
				vo.setTel(rs.getString(5));
				vo.setType(rs.getString(6));
				String poster=rs.getString(7);
				poster=poster.substring(0,poster.indexOf("^"));
				vo.setPoster(poster);
				vo.setScore(rs.getDouble(8));
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
	public CategoryVO categoryInfoData(int cno)
	{
	      CategoryVO vo=new CategoryVO();
	      try
	      {
	         conn=CreateConnection.getConnection();
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
	         CreateConnection.disConnection(conn, ps);
	      }
	      return vo; 
	}
	//MVC (1. 링크 건다 => 주의점: .do로 끝나야 함) => @RequestMapping()과 연결, 밑에 메소드 만들기 ==> DAO 만들기 ==> JSP에서 출력: Spring,Spring-Boot => 실무
	//상세보기
	public FoodVO food_detail(int fno)
	{
		FoodVO vo=new FoodVO();
		try
		{
			conn=CreateConnection.getConnection();
			// 트리거
			String sql="UPDATE project_food SET "
					+"hit=hit+1 "
					+"WHERE fno=?";
			// => 좋아요, 찜
			// => 댓글: 프로시저
			ps=conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ps.executeUpdate();
			
			sql="SELECT fno,cno,name,tel,score,poster,address,type,time,parking,menu,price,good,soso,bad "
					+"FROM project_food "
					+"WHERE fno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setFno(rs.getInt(1));
			vo.setCno(rs.getInt(2));
			vo.setName(rs.getString(3));
			vo.setTel(rs.getString(4));
			vo.setScore(rs.getDouble(5));
			vo.setPoster(rs.getString(6));
			vo.setAddress(rs.getString(7));
			vo.setType(rs.getString(8));
			vo.setTime(rs.getString(9));
			vo.setParking(rs.getString(10));
			vo.setMenu(rs.getString(11));
			vo.setPrice(rs.getString(12));
			vo.setGood(rs.getInt(13));
			vo.setSoso(rs.getInt(14));
			vo.setBad(rs.getInt(15));
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			CreateConnection.disConnection(conn, ps);
			//DAO, VO, Model(스프링: ~Controller, 스트럿츠: ~Action)
		}
		return vo;
	}
}