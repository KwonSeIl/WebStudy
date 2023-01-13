<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String id=request.getParameter("id");
    String pwd=pageContext.getRequest().getParameter("pwd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
ID:<%=id %><br>
Password:<%=pwd %>
</body>
</html>