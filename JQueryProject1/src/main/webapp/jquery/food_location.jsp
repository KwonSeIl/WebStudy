<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>
<%
	String strPage=request.getParameter("page");
	if(strPage==null)
		strPage="1";
	int curpage=Integer.parseInt(strPage);
	List<FoodVO> list=dao.foodListData(curpage);
%>
<c:set var="list" value="<%=list %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <c:forEach var="vo" items="${list }">
  <div class="col-md-4">
    <div class="thumbnail">
      <a href="#">
        <img src="${vo.poster }" alt="Lights" style="width:250px;height: 200px">
        <div class="caption">
          <p>${vo.name }</p>
        </div>
      </a>
    </div>
  </div>
  </c:forEach>
</body>
</html>