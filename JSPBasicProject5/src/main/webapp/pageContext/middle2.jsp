<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	System.out.println("middle:id="+id);
	System.out.println("middle:pwd="+pwd);
	
	pageContext.forward("output.jsp"); //MVC=> forward(request를 계속 유지)
%>