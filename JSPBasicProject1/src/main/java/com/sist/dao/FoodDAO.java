package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	// DBCP => �������� ����� �����ϴ�. ���ø����̼ǿ����� ����� �Ұ����ϴ�(Connection ���� => ��Ĺ���� ����)
	//  => ����) ���ӽð��� ���� �� �ִ�(����ӵ� ���), ������ �پ��, Connection�� ������ ������ �� �־� ���� �ٿ�ε尡 ���� �ʴ´�
	// JDBC = DBCP = ORM(Mybatis, JPA)
	/*
	 * 	SELECT * FROM project_category
	 *  WHERE cno=1;
	 *  INSERT INTO ~~
	 *  => void save()
	 *  => CategoryVO findByCno(int cno);
	 */
	public void getConnection()//�̸� ������� Connection ��ü�� ������ �´�
	   {
	      try
	      {
	         //��ϵ� ��ġ�� ����
	         Context init=new InitialContext();//JNDI(��������)
	         Context c=(Context)init.lookup("java://comp/env");
	         DataSource ds=(DataSource)c.lookup("jdbc/oracle");
	         conn=ds.getConnection();
	      }catch(Exception ex)
	      {
	         System.out.println(ex.getMessage());
	      }
	   }
	   public void disConnection() //�����ϱ� ���� ��ȯ
	   {
	      try
	      {
	         if(ps!=null) ps.close();
	         if(conn!=null) conn.close();
	      }catch(Exception ex) {}
	   }
	   public ArrayList<CategoryVO> categoryAllData()
	   {
	      ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
	      try
	      {
	         getConnection(); //Connection ������ ����
	         String sql="SELECT cno,title,subject,poster "
	                  +"FROM project_category "
	                  +"ORDER BY cno ASC ";
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
	      }catch(Exception ex)
	      {
	         ex.printStackTrace();
	      }
	      finally
	      {
	         //��ȯ
	         disConnection();
	      }
	      return list;
	   }
	}
