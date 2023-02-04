package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
public class ReserveDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	public List<FoodVO> reserveFoodListData(String fd)
	{
		List<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			conn=CreateConnection.getConnection();
			String sql="SELECT fno,poster,name,address,tel,rownum "
					+ "FROM food_location "
					+ "WHERE rownum<=50 AND type LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, fd);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				String poster=rs.getString(2);
				poster=poster.substring(0,poster.indexOf("^"));
				poster=poster.replace("#", "&");
				vo.setPoster(poster);
				vo.setName(rs.getString(3));
				String address=rs.getString(4);
				address=address.substring(0,address.lastIndexOf("지")).trim();
				vo.setAddress(address);
				vo.setTel(rs.getString(5));
				list.add(vo);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			CreateConnection.disConnection(conn, ps);
		}return list;
	}
}
