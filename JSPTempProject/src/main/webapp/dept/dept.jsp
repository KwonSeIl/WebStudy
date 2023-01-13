<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
	DeptDAO dao=new DeptDAO();
	ArrayList<DeptVO> list=dao.deptListData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <ul>
    <%
    	for(DeptVO vo:list)
    	{
    %>
    		<li><%=vo.getDeptno() %>-<%=vo.getDname() %>-<%=vo.getLoc() %></li>
    <%	
    	}
    %>
  </ul>
</body>
</html>