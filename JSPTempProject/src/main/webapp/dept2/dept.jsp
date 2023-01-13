<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	DeptModel dm=new DeptModel();
	dm.deptListData(request);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <ul>
    <c:forEach var="vo" items="${list }">
      <li>${vo.deptno }-${vo.dname }-${vo.loc }</li>
    </c:forEach>
  </ul>
</body>
</html>