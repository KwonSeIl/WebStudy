<%--
	JSP 동작 과정
	----------
	1. 사용자 브라우저 주소창에서 요청 (사용자 요청은 주소창에서 한다) => 주소창은 반드시 파일명을 마지막에 첨부한다 
		http://localhost/JSPBasicProject3/jsp/basic_2.jsp
		------------------------------------------------- basic_2.jsp을 브라우저에서 보여달라
		
		*** 주소창 ==> 서버가 받아서 처리
	2. Web Server에서 파일을 받아서 (.html, .xml)이 아닌 경우 톰캣으로 전송
	3. 톰캣
		1) basic_2.jsp ==> 자바로 변경(basic_2_jsp.java)
			| 확인(존재여부 -> 없는 경우 생성, 있는경우 수정 => CREATE OR REPLACE)
		2) basic_2_jsp.java ==> 컴파일 ==> basic_2_jsp.class 파일 생성
		3) JVM
			basic_2_jsp.class파일을 로딩
			인터프리터 통해서 번역
			==> out.write("<html>") => 메모리 안에 <html>만 출력한다
	4. 메모리에 저장된 HTML을 브라우저가 읽어서 출력
	=================================================================================
	  _jspInit() ==> web.xml(설정)
	  _jspService() ==> 사용자 요청에 의한 HTML 작성 =====> 메소드 안에 들어가는 소스코딩(JSP파일)
	  _jspDestroy() ==> 메모리 해제
	
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>