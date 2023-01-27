<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.text.*"%>
<%
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
	Date date=new Date();
	String today=sdf.format(date);
	StringTokenizer st=new StringTokenizer(today,"-");
	String year=st.nextToken();
	String month=st.nextToken();
	String day=st.nextToken();
%>    
{"year":<%=year %>,"month":<%=month %>,"day":<%=day %>}
