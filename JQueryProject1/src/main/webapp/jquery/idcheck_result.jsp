<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	String id=request.getParameter("id");
	FoodDAO dao=new FoodDAO();
	int count=dao.idCheck(id);
%>
<%=count %>
