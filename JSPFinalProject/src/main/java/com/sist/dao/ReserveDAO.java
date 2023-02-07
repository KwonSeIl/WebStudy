package com.sist.dao;
import java.util.*;

import javax.servlet.http.HttpSession;

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
				    +"FROM food_location "
				    +"WHERE rownum<=50 AND type LIKE '%'||?||'%'";
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
  // 예약일 읽기
  public String reserveDayData(int fno)
  {
	  String rdate="";
	  try
	  {
		  conn=CreateConnection.getConnection();
		  String sql="SELECT reserve_day FROM food_location "
				    +"WHERE fno=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, fno);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  rdate=rs.getString(1);
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  CreateConnection.disConnection(conn, ps);
	  }
	  return rdate;
  }
  // 예약시간 
  public String reserveTimeData(int dno)
  {
	  String times="";
	  try
	  {
		  conn=CreateConnection.getConnection();
		  String sql="SELECT time FROM project_reserve_date "
				    +"WHERE dno=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, dno);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  times=rs.getString(1);
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  CreateConnection.disConnection(conn, ps);
	  }
	  return times;
  }
  public String reserveTimeRealData(int tno)
  {
	  String times="";
	  try
	  {
		  conn=CreateConnection.getConnection();
		  String sql="SELECT time FROM project_reserve_time "
				    +"WHERE tno=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, tno);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  times=rs.getString(1);
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  CreateConnection.disConnection(conn, ps);
	  }
	  return times;
  }
  // 예약 정보 저장 
  /*
   *            RNO        NOT NULL NUMBER       
				FNO                 NUMBER  O    
				ID                  VARCHAR2(20)  O
				RDATE      NOT NULL VARCHAR2(20)  O
				RTIME      NOT NULL VARCHAR2(20)  O
				INWON               NUMBER       
				RESERVE_NO NOT NULL VARCHAR2(20)  O
				OK                  CHAR(1)       'n'  
				REGDATE             DATE     SYSDATE
   */
  public void reserveOk(ReserveVO vo)
  {
	  try
	  {
		  conn=CreateConnection.getConnection();
		  String sql="INSERT INTO project_reserve VALUES("
				    +"(SELECT NVL(MAX(rno)+1,1) FROM project_reserve),?,?,?,?,?,?,'n',SYSDATE)";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, vo.getFno());
		  ps.setString(2, vo.getId());
		  ps.setString(3, vo.getRdate());
		  ps.setString(4, vo.getRtime());
		  ps.setInt(5, vo.getInwon());
		  ps.setString(6, vo.getReserve_no());
		  ps.executeUpdate();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  CreateConnection.disConnection(conn, ps);
	  }
  }
  
  // 예약 정보를 마이페이지에서 읽기
  public List<ReserveVO> reserveMyPageData(String id)
  {
	  List<ReserveVO> list=new ArrayList<ReserveVO>();
	  try
	  {
		  conn=CreateConnection.getConnection();
		  String sql="SELECT rno,r.fno,rdate,rtime,inwon,reserve_no,regdate,name,poster,tel,type,menu,parking,address,ok "
				    +"FROM project_reserve r,food_location f "
				    +"WHERE r.fno=f.fno "
				    +"AND id=? "
				    +"ORDER BY rno DESC";
		  ps=conn.prepareStatement(sql);
		  ps.setString(1, id);
		  ResultSet rs=ps.executeQuery();
		  while(rs.next())
		  {
			  ReserveVO vo=new ReserveVO();
			  vo.setRno(rs.getInt(1));
			  vo.setFno(rs.getInt(2));
			  vo.setRdate(rs.getString(3));
			  vo.setRtime(rs.getString(4));
			  vo.setInwon(rs.getInt(5));
			  vo.setReserve_no(rs.getString(6));
			  vo.setRegdate(rs.getDate(7));
			  vo.getFvo().setName(rs.getString(8));
			  String poster=rs.getString(9);
			  poster=poster.substring(0,poster.indexOf("^")).replace("#", "&");
			  vo.getFvo().setPoster(poster);
			  vo.getFvo().setTel(rs.getString(10));
			  vo.getFvo().setType(rs.getString(11));
			  vo.getFvo().setMenu(rs.getString(12));
			  vo.getFvo().setParking(rs.getString(13));
			  String address=rs.getString(14);
			  address=address.substring(0,address.lastIndexOf("지")).trim();
			  vo.getFvo().setAddress(address);
			  vo.setOk(rs.getString(15));
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
  public List<ReserveVO> reserveAdminPageData()
  {
	  List<ReserveVO> list=new ArrayList<ReserveVO>();
	  try
	  {
		  conn=CreateConnection.getConnection();
		  String sql="SELECT rno,r.fno,rdate,rtime,inwon,reserve_no,regdate,name,poster,tel,type,menu,parking,address,id,ok "
				    +"FROM project_reserve r,food_location f "
				    +"WHERE r.fno=f.fno "
				    +"ORDER BY rno DESC";
		  ps=conn.prepareStatement(sql);
		  ResultSet rs=ps.executeQuery();
		  while(rs.next())
		  {
			  ReserveVO vo=new ReserveVO();
			  vo.setRno(rs.getInt(1));
			  vo.setFno(rs.getInt(2));
			  vo.setRdate(rs.getString(3));
			  vo.setRtime(rs.getString(4));
			  vo.setInwon(rs.getInt(5));
			  vo.setReserve_no(rs.getString(6));
			  vo.setRegdate(rs.getDate(7));
			  vo.getFvo().setName(rs.getString(8));
			  String poster=rs.getString(9);
			  poster=poster.substring(0,poster.indexOf("^")).replace("#", "&");
			  vo.getFvo().setPoster(poster);
			  vo.getFvo().setTel(rs.getString(10));
			  vo.getFvo().setType(rs.getString(11));
			  vo.getFvo().setMenu(rs.getString(12));
			  vo.getFvo().setParking(rs.getString(13));
			  String address=rs.getString(14);
			  address=address.substring(0,address.lastIndexOf("지")).trim();
			  vo.getFvo().setAddress(address);
			  vo.setId(rs.getString(15));
			  vo.setOk(rs.getString(16));
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
  // 예약 승인 
  public void reserveAdminOk(int rno)
  {
	  try
	  {
		  conn=CreateConnection.getConnection();
		  String sql="UPDATE project_reserve SET "
				  +"ok='y' "
				  +"WHERE rno=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, rno);
		  ps.executeUpdate();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  CreateConnection.disConnection(conn, ps);
	  }
  }
  
  public ReserveVO mypageReserveInfo(int rno)
  {
	  ReserveVO vo=new ReserveVO();
	  try
	  {
		  conn=CreateConnection.getConnection();
		  String sql="SELECT rno,rdate,rtime,reserve_no,inwon,name,score,poster,address,tel,type,parking "
		  		+ "FROM project_reserve r,food_location f "
		  		+ "WHERE r.fno=f.fno "
		  		+ "AND rno=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, rno);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  vo.setRno(rs.getInt(1));
		  vo.setRdate(rs.getString(2));
		  vo.setRtime(rs.getString(3));
		  vo.setReserve_no(rs.getString(4));
		  vo.setInwon(rs.getInt(5));
		  vo.getFvo().setName(rs.getString(6));
		  vo.getFvo().setScore(rs.getDouble(7));
		  String poster=rs.getString(8);
		  poster=poster.substring(0,poster.indexOf("^")).replace("#", "&");
		  vo.getFvo().setPoster(poster);
		  String address=rs.getString(9);
		  address=address.substring(0,address.lastIndexOf("지")).trim();
		  vo.getFvo().setAddress(address);
		  vo.getFvo().setTel(rs.getString(10));
		  vo.getFvo().setType(rs.getString(11));
	      vo.getFvo().setParking(rs.getString(12));

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
  //삭제
  public void reserveDelete(int rno)
  {
	  try
	  {
		  conn=CreateConnection.getConnection();
		  String sql="DELETE FROM project_reserve "
		  		+ "WHERE rno=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, rno);
		  ps.executeUpdate();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  CreateConnection.disConnection(conn, ps);
	  }
  }
}
