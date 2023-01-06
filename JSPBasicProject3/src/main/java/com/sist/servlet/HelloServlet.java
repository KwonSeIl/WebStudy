package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 	MVC ==> Controller(Servlet) ==> Spring
 * 			-------------------
 * 			 요청		==========> 요청 처리(Model): Java
 * 			 응답		==========> 화면 출력(View): JSP
 * 			 -----------------
 * 				Servlet
 * 
 */
@WebServlet("/hello.do") // a.do
// 클라이언트(브라우저) <=======> 서버
//					주소창
// 사용자가 URL 주소 마지막에 /HelloServlet 작성 ==> 톰캣이 HelloServlet를 호출
/*
 * 	클라이언트에서 어떤 값을 전송할지.. (Front-End)
 *  요청값을 받아서 어떤 화면을 브라우저로 전송할지..(Back-End)
 *  C/S ==> 주고 받기
 *  		-------
 *  예)
 *  	로그인 => id, pwd
 *  	화면 => main
 *  
 *  	명소 => 상세보기
 *  	--
 *  	 Client => 명소 번호 전송
 *  	 Server => 번호에 해당되는 모든 데이터를 읽어서 화면 변경
 *  
 *  	게시판 ==> 글쓰기
 *  	 Client => 글쓴 내용을 전송
 *  	 Server => 오라클에 저장 -> 화면 목록으로 이동
 *  	---------------------------------------------- 흐름(클릭 => URL 확인 => 화면)
 *  
 *  서블릿 호출
 *  1) http://localhost/JSPBasicProject3/HelloServlet 브라우저에서 요청
 *  	---------------------------------------------
 *  2) 서버에서 ==> WAS 에서 서블릿 클래스를 찾는다(HelloServlet)
 *  			 --- Web Application Server (톰캣, 레진) ==> WAS, 형상관리(깃), Container(Spring), Component(자바 클래스)
 *  3) int()메소드를 호출
 *  4) service() 메소드를 호출 ==> HTML을 브라우저로 전송
 *  	 |
 *  	doGet(), doPost()
 *  5) 브라우저, 화면 이동 ==> destroy()
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ==> 톰캣에 의해 호출되는 Callback
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//멤버 변수의 초기화, 자동 로그인 시 파일 읽기(Cookie) 담당 ==> 생성자 역할
		System.out.println("서블릿 초기화 메소드 호출...");
	}

	
	public void destroy() {
		// TODO Auto-generated method stub
		// 브라우저 종료, 파일 이동, 새로고침 => 자동 메모리 해제
	}
	/*
	 * 	HTML
	 * 	----
	 * 	  |
	 * 	  데이터를 전송 ==> 서버가 받아서 처리
	 * 	  				------------- 자바로 받아서 처리
	 * 			|
	 * 		GET / POST / PUT / DELETE	===> 다른 프로그램과 연동해서 처리(RestFul)
	 * 		----------    |       |
	 * 			|		Update 	Delete
	 * 		  -----
	 * 			GET: 데이터 전송 방식 ==> http://localhost/main/main.jsp?no=10; ==> URL 뒤에 데이터를 묶어서 전송
	 * 													  -----------------
	 * 														main.jsp로 no값을 전송 => 메소드가 없기 때문에 매개변수를 이용할 수 없다
	 * 				==> 단순한 검색어, 페이지 번호 전송...(보안과 관련이 없는 데이터)
	 * 			POST: no=10 ==> 감춰서 보낸다(URL 뒤에 데이터가 안보인다)
	 * 				==> 보안(ID, Password, 데이터가 많은 경우(회원가입,글쓰기,상품입력..)
	 * 
	 * 			GET ==> doGet()
	 * 			POST ==> doPost()
	 * 					--------------- service(), JSP는 구분이 없다 => 자체 조건문으로 (GET, POST)
	 */
	// 브라우저 화면을 출력하는 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. GET / POST가 지정되지 않는 경우에는 GET이 호출된다(default)
		/*
		 * 	서버로 전송 (GET/POST)
		 *  -------
		 *  HTML
		 *  	<a>	=> GET만 사용가능 ==> 데이터 전송( ?변수=값)
		 *  	***<form>
		 *  JavaScript
		 *  	location.href=""
		 *  	***Ajax
		 *  		type=POST,GET...
		 *  	***VueJS
		 *  		axios.get(), axios.post()
		 *  	***ReactJS
		 *  		axios.get(), axios.post()
		 *  Java
		 *  	sendRedirect()
		 */
		// 1. 브라우저에 보내는 형식을 지정 ==> text/html, text/xml, text/plain
		//								HTML		XML			JSON
		response.setContentType("text/html;charset=UTF-8"); //한글포함
		// 2. 어떤 브라우저로 전송할지 설정
		PrintWriter out=response.getWriter();
		//				요청한 브라우저의 정보(ip)
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlet</h1>");
		out.println("</body>");
		out.println("</html>");
	}
	// 브라우저 화면을 출력하는 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
