<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	EL => <%= %> 를 대체 (화면에 데이터 출력) : 표현식
	--- Spring, 실무
	자바 제어문 -> JSTL로 변경
	---------------------> 목적: 자바를 최소화시킴
	최소화시키는 이유
	1) 복잡: HTML/자바 동시에 실행
	2) Front / Back End가 공동 작업이 가능하게 만든다
	공부해야 할 것
	1. 내장 객체 (525page)
		1) requestScope: request.getAttribute()와 동일 
		2) sessionScope: session.getAttribute()와 동일
		3) param: request.getParameter()와 동일
		사용 방식
			<%= %> ==> ${}
			<%
				String name="홍길동";
				request.setAttribute("name",name)
									 ------ key
			%>
			${rquestScope.name}
						  ---- key (위의 key와 동일해야 함)
			  ----------- 생략 가능 => ${name} 가능
			%{name} ==> 출력하지 않음
			  ---- 변수가 아님
			<%=name%>
	2. 연산자
 --%>  
 <%
 	String name="홍길동";
 	//${}를 이용해서 출력 -> request, session 추가적으로 담아서 출력 => setAttribute()
 	request.setAttribute("name", "홍길동");
 	session.setAttribute("name1", "박문수");
 %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>일반 변수일 경우 출력</h1>
이름: <%=name %><br>
<h1>EL을 이용할 경우 출력</h1>
이름: ${requestScope.name} <%-- <%=request.getAttribute("name") %> --%>
<br>
이름: ${name} <%--requestScope를 생략할 수 있다 --%>
<br>
<h1>Session에 저장된 데이터 읽기</h1>
이름: ${sessionScope.name } <br>
이름: ${name } <%-- request에 저장된 데이터 => request.getAttribute("name") --%> 
<br>
이름: ${name1 } <%-- 이름 다르면 생략 가능 --%>
</body>
</html>