package com.sist.dao;
/*
 * 	데이터베이스: JDBC
 *  JDBC
 *    순서
 *     1. 데이터베이스 연결 드라이버 설정(thin:연결만 하는 역할) => 드라이버는 데이터베이스 각 업체에서 지원 ojdbc8.jar
 *     	  Class.forName("드라이버명")
 *     					---------- oracle.jdbc.driver.OracleDriver
 *     								com.mysql.cj.jdbc.Driver(MYSQL) => MariaDB
 *     2. 실제 오라클 연결
 *     	  Connection conn=Drivermanager.getConnectionn(URL,username,password)
 *     3. SQL 문장 전송
 *     	  PreparedStatement ps=conn.preparedStatement("SELECT ~")
 *     4. ?에 값을 채운다
 *        = ps.setInt()
 *        = ps.setString()
 *        = ps.setDouble()
 *        = ps.setDate() ==> SYSDATE, 'YY/MM/DD'
 *     5. ps.executeQuery() => SELECT(데이터값을 가지고 온다): 실행
 *     	  ps.executeUpdate() => INSERT, UPDATE, DELETE ==> COMMIT
 *     6. 결과값을 받는 경우
 *     	  ResultSet rs=ps.executeQuery();
 *     	  = ROW가 1개일 경우
 *     		rs.next(); 메모리에 출력된 데이터 첫번째 줄에 커서 위치 변경 
 *     	  = ROW가 여러개일 경우
 *     		while(rs.next())
 *     7. 처리 종료
 *     	  rs.close()
 *     	  ps.close()
 *     	  conn.close()
 *     	  --------------------------------- 일반 JDBC
 *     	  DBCP => Connection을 미리 생성
 *     			  연결에 소요되는 시간을 줄일 수 있다(속도가 빠르다), Connection의 누수 현상을 방지할 수 있다
 *     			  재사용이 가능(사용 => 반환)
 *     	  => Setting: server.xml
 *     			<Resource
 *     				auth="Container" => 관리자(톰캣)
 *                  driverClassName="" => 드라이버 등록
 *                  -------------
 *                  url=""
 *                  username=""
 *                  password=""
 *                  ------------- Connection 생성
 *                  name="jdbc/oracle" => Connection 객체를 찾기 위한 구분자 => lookup(): 이름으로 객체 주소 얻기
 *                  type="javax.sql.DataSource" => 얻어온 주소값을 어떤 클래스로 받을지 설정 
 *                  ----------
 *                  maxActibe="" : 실제 Connection 생성 개수 (초과)
 *                  maxIdle="" : 실제 POOL에 저장된 개수
 *                  axWait="-1" : Connection을 전부 사용시에는 사용자가 반환할 때까지 대기 (대기시간)
 *                  ---------- 초, 음수면 무한정
 *              />
 *              => XML: 대소문자 구분, 속성값 지정 "", 열고 닫기 ==> 오류 발생시에는 run on server가 없어진다
 *              => web.xml, properties
 *              
 *       = 코딩 순서
 *        1) 미리 생성(톰캣)된 Connection의 주소값을 얻어온다
 *        	1. Connection 객체 주소가 저장된 위치로 접근
 *        		java://comp/env => 저장되는 위치
 *        		jdbc/oracle
 *        
 *              Context init=new InitalContext(); => 접근(탐색기를 연다)
 *              Context c=(Context)init.lookup("java://comp/env");
 *              DataSource ds=(DataSource)c.lookup("jdbc/oracle")
 *              conn=ds.getConnection()
 *        --------------
 *        2) SQL문장 전송
 *        3) ?에 값을 채운다
 *        4) 결과값 읽기
 *        ---------------> 2,3,4 => JDBC와 동일
 *        5) 반환    
 *        	ps.close()
 *        	conn.close()  
 *        
 *        JDBC / DBCP ==> Connection: 매번 생성 / 미리 생성 
 */
import java.util.*; // ArrayList
import java.sql.*;  // Connection, PrepareStatement, ResultSet
import javax.sql.*; // DataSource (DB에 대한 모든 정보 가짐)

import com.sist.vo.DataBoardVO;

import javax.naming.*; // Context => 메모리에 저장된 데이터 
public class DataBoardDAO {
	private Connection conn; //오라클 연결 객체
	private PreparedStatement ps; //오랔클로 SQL 문장 전송 -> 값을 가지고 온다
	//1. 미리 생성된 Connection 객체 주소를 읽어온다
	public void getConnection()
	{
		try
		{
			Context init=new InitialContext(); //탐색기 형식으로 저장 => JNDI => DORBA, RMI
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		}catch(Exception ex) {}
	}
	//2. 반환 ==> Connection을 관리하는 영역(POOL)
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	//3. 기능 => JDBC와 동일
	//3-1. 목록 출력 ==> 페이징(인라인뷰)
	/*
	 *  메소드
	 *  	1) 사용자로부터 어떤 값을 받을 지 => 매개변수
	 *      2) 결과값을 어떤값 => 리턴형 
	 *      	= 목록(ArrayList)
	 *      	= 상세(~VO) => 게시물 한개에 대한 모든 데이터변수를 가지고 있다
	 *      	= 일반(총페이지, 검색개수, 로그인 처리)
	 *      		경우의 수
	 *      		------- 여러개 (String,int) => ID, PWD, OK
	 *      		------- 한개: 해당 데이터 사용
	 *      3) 메소드를 구현
	 *      
	 *      ==> 언어: 자바, 자바스크립트(Jquery, Ajax, VueJS, ReactJS)
	 *      		1. 변수
	 *      		2. 처리(메소드)
	 *      		-----------
	 *      4) 웹
	 *      	사용자 전송 => 매개변수
	 *      		request
	 *      			|
	 *      		서버에서 요청 처리(데이터,크롤링,통계처리) =========> 결과값을 사용자에게 전송
	 *      									  response
	 *      5) 네트워크 / 웹 / AI
	 *      		   -- 가장 쉬운 프로그램(인원이 가장 많이 몰린다) => 경쟁력 
	 *      			| 인맥 => 개발 발전 전망이 없음
	 */
	public ArrayList<DataBoardVO> databoardListData(int page)
	{
		ArrayList<DataBoardVO> list=new ArrayList<DataBoardVO>();
		try
		{
			//1. Connection 주소 얻기
			getConnection();
			//2. SQL문장 ==> index => UNIQUE,PRIMARY KEY => 자동으로 인덱스가 만들어진다
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,filesize,num "
					+"FROM (SELECT no,subject,name,regdate,hit,filesize,rownum as num "
					+"FROM (SELECT /*+ INDEX_DESC(databoard db_no_pk)*/ no,subject,name,regdate,hit,filesize "
					+"FROM databoard)) "
					+"WHERE num BETWEEN ? AND ?";
					//***rownum은 1번부터 시작 
			//3. 전송
			ps=conn.prepareStatement(sql);
			//4. ?에 값 채우기(없는 경우도 존재)
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1); //1,11,21...
			int end=rowSize*page; //10,20,30...
			ps.setInt(1, start);
			ps.setInt(2, end);
			//5. 실행 요청
			ResultSet rs=ps.executeQuery();
			//6. 읽어온 데이터 -> ArrayList에 담기
			while(rs.next())
			{
				DataBoardVO vo=new DataBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4)); //TO_CHAR일 때 
				vo.setHit(rs.getInt(5));
				vo.setFilesize(rs.getInt(6));
				//모아서 전송할 준비
				list.add(vo);
			}
			rs.close();
			//==================================> 라이브러리화 -> 한개의 메소드로 통일이 가능: selectList(SQL) => MyBatis
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
	//3-2. 새글 => 업로드
	public void databoardInsert(DataBoardVO vo)
	{
		try
		{
			getConnection();
			//SQL문장 
			String sql="INSERT INTO databoard(no,name,subject,content,pwd,filename,filesize) "
					+ "VALUES(db_no_seq.nextval,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			//?값 채우기
			ps.setString(1, vo.getName());
    		ps.setString(2, vo.getSubject());
    		ps.setString(3, vo.getContent());
    		ps.setString(4, vo.getPwd());
    		ps.setString(5, vo.getFilename());
    		ps.setInt(6, vo.getFilesize());
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
	//3-3. 상세보기 => 조회수 증가, 다운로드 => response.setHeader()
	public DataBoardVO databoardDetailData(int no)
	{
		DataBoardVO vo=new DataBoardVO();
		try
		{
			//1. Connection 주소값 얻기
			getConnection();
			//2.SQL 문장
			//2-1. 조회수 증가
			String sql="UPDATE databoard SET "
					+"hit=hit+1 "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			////////////////////////// 조회수 증가
			//상세볼 데이터 읽기
			sql="SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD'),hit,filename,filesize "
					+"FROM databoard "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			/*
			 *   1) 회원가입 => 로그인, 회원가입, 회원수정, 회원탈퇴, 아이디 찾기, 비밀번호 찾기
			 *   		     ----------------------------------------------
			 *   게시판 => 계층형 댓글, 묻고 답하기
			 *   예약, 결제, 추천
			 */
			ps.setInt(1, no);
			//결과값 읽기
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDbday(rs.getString(5));
			vo.setHit(rs.getInt(6));
			vo.setFilename(rs.getString(7));
			vo.setFilesize(rs.getInt(8));
			rs.close();
			/*
			 * 	getInt(), getString(), getDouble(), getDate()
			 */
		}catch(Exception ex)
		{
			ex.printStackTrace(); //에러처리
		}
		finally
		{
			disConnection();
			//반환
		}
		return vo;
	}
	//총페이지
	public int databoardRowCount() 
	{
		int count=0;
		try
		{
			//1. 주소 읽기
			getConnection();
			//2.SQL문장 
			String sql="SELECT COUNT(*) FROM databoard";
			//3. SQL문장 전송
			ps=conn.prepareStatement(sql);
			//4. 실행 결과 받기
			ResultSet rs=ps.executeQuery();
			//5. 변수에 저장
			rs.next(); //데이터가 출력된 위치로 커서 이동
			count=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return count;
	}
	//3-4. 수정 => 파일명이 변경 됐을 때 기존 것 제거 -> 새로운 파일 추가 
	//3-5. 삭제 => 파일과 동시에 삭제 
	//데이터 삭제 전에 파일 삭제를 먼저 한다 => 데이터베이스 삭제
	public DataBoardVO databoardInfoData(int no)
	{
		DataBoardVO vo=new DataBoardVO();
		try
		{
			getConnection();
			String sql="SELECT filename,filesize FROM databoard "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setFilename(rs.getString(1));
			vo.setFilesize(rs.getInt(2));
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
	public boolean databoardDelete(int no,String pwd)
	{
		boolean bCheck=false;
		try
		{
			getConnection();
			String sql="SELECT pwd FROM databoard "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(pwd)) //삭제 (본인여부) ==> 자동으로 생성
			{
				bCheck=true;
				//실제 데이터베이스에서 삭제
				sql="DELETE FROM databoard "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
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
	//수정 데이터
	public DataBoardVO databoardUpdateData(int no)
	{
		DataBoardVO vo=new DataBoardVO();
		try
		{
			//1. Connection 주소값 얻기
			getConnection();
			//2.SQL 문장
			//2-1. 조회수 증가
			String sql="SELECT no,name,subject,content "
					+"FROM databoard "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			/*
			 *   1) 회원가입 => 로그인, 회원가입, 회원수정, 회원탈퇴, 아이디 찾기, 비밀번호 찾기
			 *   		     ----------------------------------------------
			 *   게시판 => 계층형 댓글, 묻고 답하기
			 *   예약, 결제, 추천
			 */
			ps.setInt(1, no);
			//결과값 읽기
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			rs.close();
			/*
			 * 	getInt(), getString(), getDouble(), getDate()
			 */
		}catch(Exception ex)
		{
			ex.printStackTrace(); //에러처리
		}
		finally
		{
			disConnection();
			//반환
		}
		return vo;
	}
	//실제 수정
	public boolean databoardUpdate(DataBoardVO vo)
	{
		boolean bCheck=false;
		try
		{
			getConnection();
			//비밀번호 검색
			String sql="SELECT pwd FROM databoard "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(vo.getPwd()))
			{
				bCheck=true;
				sql="UPDATE databoard SET "
						+"name=?,subject=?,content=? "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
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
}
