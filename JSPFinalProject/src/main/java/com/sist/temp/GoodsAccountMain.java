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
		
		/*List<Integer> list=gm.foodNumberData();
		int i=1;
		for(int fno:list)
		{
			gm.foodReserveDayInsert(fno, gm.reserve_day());
			System.out.println("i="+i);
		}
		System.out.println("저장 완료");*/
		/*for(int i=1;i<=6184;i++)
		{
			int rand=(int)(Math.random()*40)+10;
			gm.goodsAccountInsert(i, rand);
			try
			{
				Thread.sleep(30); //for문 많아서 열고 닫고 할 시간 없음 => 열고 닫을 시간 0.3초 설정
			}catch(Exception ex) {}
			System.out.println("i="+i);
		}
		System.out.println("수량 추가 종료!!");*/
		//String s=gm.reserve_day();
		//System.out.println(s);
		//String s=gm.reserve_time();
		//System.out.println(s);
		for(int i=1;i<=31;i++)
		{
			gm.foodReserveTimeInsert(i, gm.reserve_time());
		}
		System.out.println("저장 완료");
	}
	public void foodReserveTimeInsert(int rdate,String time)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO project_reserve_date VALUES("
					+"(SELECT NVL(MAX(dno)+1,1) FROM project_reserve_date),?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, rdate);
			ps.setString(2, time);
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
	//예약가능한 날 저장
	public void foodReserveDayInsert(int fno,String reserve_day)
	{
		try
		{
			getConnection();
			String sql="UPDATE food_location SET "
					+"reserve_day=? "
					+ "WHERE fno=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, reserve_day);
			ps.setInt(2, fno);
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
	public List<Integer> foodNumberData()
	{
		List<Integer> list=new ArrayList<Integer>();
		try
		{
			getConnection();
			String sql="SELECT fno FROM food_location";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				list.add(rs.getInt(1));
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
	public String reserve_day()
	{
		String s="";
		int[] com=new int[(int)(Math.random()*10)+7]; //예약가능한 날짜 7개~17개
		int su=0;
		boolean bCheck=false;
		for(int i=0;i<com.length;i++)
		{
			bCheck=true;
			while(bCheck)
			{
				su=(int)(Math.random()*30)+1;
				bCheck=false;
				for(int j=0;j<i;j++)
				{
					if(com[j]==su) //날짜 중복 방지
					{
						bCheck=true;
						break;
					}
				}
			}
			com[i]=su;
		}
		
		for(int i:com)
		{
			s+=i+",";
		}
		s=s.substring(0,s.lastIndexOf(","));
		return s;
	}
	
	public String reserve_time()
	{
		String s="";
		int[] com=new int[(int)(Math.random()*5)+5]; //예약가능한 날짜 7개~17개
		int su=0;
		boolean bCheck=false;
		for(int i=0;i<com.length;i++)
		{
			bCheck=true;
			while(bCheck)
			{
				su=(int)(Math.random()*16)+1;
				bCheck=false;
				for(int j=0;j<i;j++)
				{
					if(com[j]==su) //날짜 중복 방지
					{
						bCheck=true;
						break;
					}
				}
			}
			com[i]=su;
		}
		
		Arrays.sort(com);
		
		for(int i:com)
		{
			s+=i+",";
		}
		s=s.substring(0,s.lastIndexOf(","));
		return s;
	}
}
