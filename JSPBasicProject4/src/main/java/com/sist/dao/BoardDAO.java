package com.sist.dao;
import java.util.*;
import java.sql.*;
// JDBC (원시 소스) -> DBCP -> 
public class BoardDAO {
	// 오라클 연결 객체
	private Connection conn;
	// 오라클 송수신 객체 (SQL 보내고 -> 데이터 값 받기)
	private PreparedStatement ps;
	// 오라클 연결 주소 설정
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 연결
	public BoardDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	// 오라클 연결
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	// 오라클 닫기
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	/////////////////////////// MyBatis / JPA ==> xml에 등록
	// 게시판 관련 (CURD)
	// 웹프로그램의 비중 => 50%(DB), 20%(자바), 20%(HTML/CSS), 10%(JavaScript)
	public ArrayList<BoardVO> boardListData(int page) //사용자가 데이터 전송 -> 처리 (매개변수)
	{
		/*
		 * 	인라인 뷰 => Top-N(1번부터)
		 * 	인덱스
		 */
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		try
		{
			getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,num "
					+"FROM (SELECT no,subject,name,regdate,hit, rownum as num "
					+"FROM (SELECT /*+ INDEX_DESC(jsp_board jb_no_pk)*/no,subject,name,regdate,hit "
					+"FROM jsp_board)) "
					+"WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=(page*rowSize)-(rowSize-1); // 1,11, 21
			int end=page*rowSize;
			// ?에 값을 채운다
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			//실행
			ResultSet rs=ps.executeQuery();
			// 값을 ArrayList에 저장
			while(rs.next())
			{
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				
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
	// 총 페이지를 가지고 온다
	// JSP(Moedl1) -> MV 패턴 -> MVC 패턴(프로젝트)
	// MV(Model View): 자바 / HTML 분리
	// MVC: 자바 / HTML 분리 -> 연결 
	public int boardTotalPage()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM jsp_board";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery(); //데이터 값 읽기
			rs.next();
			total=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return total;
	}
	// 목록: 페이징(인라인뷰), 출력 순서, 블록별(< [1][2][3]..>)
	// 상세보기: rownum (이전/다음)
	public BoardVO boardDetailData(int no)
	{
		BoardVO vo=new BoardVO();
		try
		{
			//1. 연결
			getConnection();
			//2. SQL 문장 제작
			String sql="UPDATE jsp_board SET "
					+"hit=hit+1 "
					+"WHERE no=?";
			// => 기능 수행 ==> 한 개의 기능에 여러개의 SQL 문장을 실행할 수 있다
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			//2-1. 사용자가 요청한 게시물 상세 받기
			sql="SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') "
					+"FROM jsp_board "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			//데이터값을 받아서 저장 => JSP에서 읽기
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setHit(rs.getInt(5));
			vo.setDbday(rs.getString(6));
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
	// 글쓰기: request
	public void boardInsert(BoardVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO jsp_board(no,name,subject,content,pwd) "
					+"VALUES(jb_no_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			//?에 값을 채운다
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			//실행 명령을 내린다
			ps.executeUpdate(); //COMMIT 포함 => INSERT, UPDATE, DELETE 사용 시 		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	// 수정: 비번 => javaScript
	// 수정데이터 읽기
	//1. Ajax, VueJs, ReactJS, Timeleaf
	public BoardVO boardUpdateData(int no)
	{
		BoardVO vo=new BoardVO();
		try
		{
			getConnection();
			String sql="SELECT no,name,subject,content "
					+"FROM jsp_board "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
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
	//수정
	public boolean boardUpdate(BoardVO vo) //수정할 데이터 여러개 => VO이용, 한개/두개 => 일반변수
	{
		//boolean => 비밀번호가 맞는 경우 / 틀린 경우 ==> 경우의 수가 여러개: int, String / 두개: boolean
		//수정 => 비밀번호(O): 수정하고 상세보기로 넘어감 / 비밀번호(X): 수정없이 이전화면으로 이동
		boolean bCheck=false;
		try
		{
			//1.연결
			getConnection();
			//2. SQL 두 번 수행
			//2-1. 비밀번호 확인
			String sql="SELECT pwd FROM jsp_board "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			System.out.println("db_pwd="+db_pwd+",pwd="+vo.getPwd()); //디버깅
			//비밀번호 체크
			if(db_pwd.equals(vo.getPwd()))
			{
				bCheck=true;
				//실제 수정
				sql="UPDATE jsp_board SET "
						+"name=?,subject=?,content=? " //regdate=SYSDATE
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				
				//실행 명령
				ps.executeUpdate();
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return bCheck;
	}
	
	// 삭제: 비번 => javaScript
	public boolean boardDelete(int no,String pwd)
	{
		boolean bCheck=false;
		try
		{
			getConnection();
			//비밀번호 체크
			String sql="SELECT pwd FROM jsp_board "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(pwd)) //삭제
			{
				sql="DELETE FROM jsp_board "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeUpdate();
				
				bCheck=true;
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return bCheck;
	}
	// 찾기: <select> <chectbox> ==> 파일 안에서 처리
}
