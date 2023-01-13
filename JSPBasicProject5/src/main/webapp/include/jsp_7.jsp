<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--
		JSP(웹=> 서버 프로그램: _jspService()안에 소스 코딩을 한다)
		------------------------------------------------
		 JSP 구동
		  1) WebServer가 URL주소를 받는다 ==> 마지막에 파일명이 존재(필요시에 의해 변경 가능)
		  	 ------------------------
		  	 1-1) .html, xml일 경우 -> 브라우저로 바로 전송
			 1-2) .jsp일 경우 -> WAS(Web Application Server)로 전송
						|	   ------------------------- 톰캣,레진.., 제우스
					a.jsp ==> a._jsp.java =====> a_jsp.class ===> 메모리에 HTML만 출력(출력된 내용만 브라우저에서 읽어서 화면에 보여준다)
						 변환				   컴파일				 실행  ----------------
						 												| = 출력 버퍼 (관리 클래스: out) => buffer="8kb"
		 2) JSP에 대한 정보 제공
					| = 지시자
				  <%@ page 변환코드  에러 시 출력할 파일 지정  라이브러리  출력버퍼크기> => JSP의 시작점
		 					----   ----------------   -----   -------
		 					|		|					|		|
		 														buffer="16kb"
		 											import=",,"
		 							errorPage="파일명"
		 					contentType="text/html;charset="UTF-8", "text/xml;charset=UTF-8", "text/pain;charset=UTF-8"
		 			** 지시자 안에 모든 속성은 지정 되어 있음
		 				속성="값" 속성="값" ==> 한 번만 사용이 가능
		 				단 import는 여러번 사용 가능
		 				예) <%@ page import="java.util.*,java.io.*"%>
		 						page import="java.util.*"%>
		 								page java.io.*"%>
		 3) JSP => 자바+HTML(CSS,JavaScript)
		 			자바와 HTML을 구분
		 			--
		 			<% 스크립트릿: 일반 자바(메소드 안에) => 지역변수, 메소드 호출, 제어문, 연산자 가능%>
		 			<%= 표현식: 화면에 출력: 변수, 메소드 리턴값%> => out.println()
		 			<%! 선언식: 메소드 선언, 전역변수 선언%>
		 			------------------------------------------------------------ 이번주까지만
		 			=> 출력문: ${}
		 			=> 제어문: 태그로 만들어져 있다 => 모든 프로그램은 태그형으로 변경된다
		 				<c:forEach> => for()
		 				<c:if test=""> => if()
		 4) 내장객체
		 	***= request
		 		클래스 확인: HttpServletRequest
		 		= 역할
		 			1. 브라우저 / 서버 정보를 가지고 올 수 있다
		 				http://localhost/JSPBasicProject5/include/jsp_7.jsp
		 				----------------  ----------------------------------
		 					|						|
		 					서버정보		+		사용자 요청 정보(URI) ==> URL
		 					= getServerInfo(), getServerPort()
		 					  --------------   --------------
		 					  localhost				80
		 					= getRequestURI() => /JSPBasicProject5/include/jsp_7.jsp
		 					= getContextPath() => /JSPBasicProject5
		 					= getRemoteAddr() => 사용자의 IP를 읽어 온다
		 			2. 사용자 요청 데이터관리
		 				= getParameter()
		 				= getParameterValues()
		 				= setCharacterEncoding()
		 			3. 추가 정보를 가지고 있다
		 				= setAttribute(): 추가
		 				= getAttribute() : 읽기
		 		= 주요메소드
		 	***= response
		 	***= session
		 	= out
		 	= pageContext
		 	= application
		 5) JSP액션태그
		 	= <jsp:include>: tiles
		 	= <jsp:useBean>
		 	= <jsp:setProperty>
		 			
	
--%>