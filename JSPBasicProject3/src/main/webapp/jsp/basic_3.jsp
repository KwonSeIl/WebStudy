<%--
	1. JSP
		자바: 데이터 관리(오라클 연동) 
		HTML: 브라우저에 출력할 용도(데이터 관리, 오라클 연동을 할 수 없다)
		--------------------------------------------------
		자바/HTML을 구분
		-------------
		=> JSP의 스크립트 요소가 등장
			1) 선언문: 메소드, 멤버변수 => 사용빈도는 거의 없다
					 <%! %>
			2) 스크립트릿: 메소드 안에 들어가는 소스코딩
					메소드 호출, 지역변수, 제어문, 연산자..
					<% %> 
			3) 표현식: 브라우저에 자바 관련 내용 출력, 변수값
					<%= %>
			4) 주석 <!-- -->(HTML 주석)
			
			<%! %>, <% %> => 자바 문법을 사용한다
			-------------- //, /**/ => 명령문이 종료되면 ;
			<%= %> => ;을 사용하지 않는다
			out.println(여기에 출력하는 내용만 기재)
			*** <html>
				out.println("<html>')
			*** <% %> => 중복되면 오류 발생
			*** <% <%= %>  %> => 오류 발생 ==> 열고 닫기를 명확하게 만든다
			*** <%@ page language="java" contentType="text/html; charset=UTF-8"
					pageEncoding="UTF-8" %>
				1. 없을 경우
				2. 지원하지 않는 속성을 사용시
				
			<%@  
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 선언식 --%>
<%!
	// 자바로 만들 때 => 확장성, 재사용
	// 메소드(로직) => 보안상의 문제 발생
	String name="홍길동"; //멤버변수
	//메소드 선언
	public int add(int a,int b)
	{
		return a+b;
	}
	public int minus(int a,int b)
	{
		return a-b;
	}
	// 자바 => .class, JSP => 통으로 전송 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%--
	<style>
		/* */
	</style>
	<script>
		// 한줄 주석
		/* 여러줄 주석 */
	</script>
	<!-- -->
 --%>
<body>
  <%--
  	선언식에 있는 메소드 호출 => 스크립트릿을 이용한다
   --%>
   <h1><%=name %></h1><!-- 출력 위치 (HTML => <태그>~~<태그>,<태그 속성="~~">) -->
   <%
   		int a=10;
   		int b=20;
   		int c=add(a,b);
   		int d=minus(a,b);
   		// 주석 문법사항은 일반 자바와 동일 ==>_jspService()
   		/* 주석 */
   %>
   <h3><%=c %></h3> <%-- ;을 사용할 수 없다 out.println(c) --%>
   <h3>c</h3> <%--일반문자열(c) --%>
   <h3><%=d %></h3>
</body>
</html>