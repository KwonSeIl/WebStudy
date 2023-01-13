<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>
<%
     String cate=request.getParameter("cate");
     
     ArrayList<CategoryVO> list=dao.categoryListData(Integer.parseInt(cate));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%
        for(CategoryVO vo:list)
        {
     %>
           <div class="col-md-4">
		    <div class="thumbnail">
		      <a href="#">
		        <img src="<%=vo.getPoster() %>" title="<%=vo.getSubject() %>" style="width:100%">
		        <div class="caption">
		          <p><%=vo.getTitle() %></p>
		        </div>
		      </a>
		    </div>
		  </div>
     <%
        }
     %>
</body>
</html>