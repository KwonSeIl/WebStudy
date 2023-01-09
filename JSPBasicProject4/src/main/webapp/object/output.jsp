<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 사용자가 보내준 데이터를 받는다
	//2. DB 연동
	//3. 화면 이동 / 출력
	/*
		화면 이동: 회원가입, 회원 수정 => main.jsp로 이동
				글쓰기 => 목록으로 이동
				수정 => 상세보기로 이동
	*/
	// name, sex, loc, hobby, content
	// --------------		  -------
	//name(키)=홍길동(값)
	//한글처리
	request.setCharacterEncoding("UTF-8");
	String name=request.getParameter("name");
	String sex=request.getParameter("sex");
	String loc=request.getParameter("loc");
	String content=request.getParameter("content");
	// <input type=text,password ...>, <textarea>, <select> = request.getParameter()
	// <input type=checkbox> = request.getParameterValues()
	String[] hobby=request.getParameterValues("hobby"); //Cookie(내장 객체가 아님)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>전송받은 값 출력</h1>
  이름:<%=name %><br>
  성별:<%=sex %><br>
  지역:<%=loc %><br>
  소개:<%=content %><br>
  취미:
      <ul>
        <%
        	
            //if(hobby!=null)
            try
            {
        		for(String h:hobby)
        		{
        %>
        			<li><%=h %></li>
        <%		
        		}
           	}
           	catch(Exception ex)
           	//else
           	{
        %>
        			<span style="color: red">취미가 없습니다</span>
        <%
           	}
        %>
      </ul>
</body>
</html>