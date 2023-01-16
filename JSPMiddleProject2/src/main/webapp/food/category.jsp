<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<jsp:useBean id="model" class="com.sist.model.FoodModel"/>
<%
	// => Controller: Call By Reference 방식
	model.categoryListData(request, response);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=da4c62aa0d3b6477d367816bc345b1c9&libraries=services"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container{
	margin-top: 100px;
}
.row{
	width: 960px;
	margin: 0px auto;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <h1>믿고 보는 맛집 리스트</h1>
      <hr>
        <%--
        	for(CategoryVO vo:list)
         --%>
        <c:forEach var="vo" items="${list }" varStatus="s">
          <%-- varStatus: ArrayList의 index 번호 --%>
          <c:if test="${s.index>=0 && s.index<12 }">
            <div class="col-md-3">
				    <div class="thumbnail">
				      <a href="food_list.jsp?cno=${vo.cno }">
				        <img src="${vo.poster }" style="width:260px;height: 200px" class="img-rounded">
				        <div class="caption">
				          <p style="font-size: 9px;font-weight: bold">${vo.title }</p>
				        </div>
				      </a>
				    </div>
				  </div>
          </c:if>
        </c:forEach>
    </div>
    <div style="height: 10px"></div>
    <div class="row">
      <h1>지역별 인기 맛집</h1>
      <hr>
        <c:forEach var="vo" items="${list }" varStatus="s">
          <%-- varStatus: ArrayList의 index 번호 --%>
          <c:if test="${s.index>=12 && s.index<18 }">
            <div class="col-md-4">
				    <div class="thumbnail">
				      <a href="food_list.jsp?cno=${vo.cno }">
				        <img src="${vo.poster }" style="width:260px;height: 200px" class="img-rounded">
				        <div class="caption">
				          <p style="font-size: 9px;font-weight: bold">${vo.title }</p>
				        </div>
				      </a>
				    </div>
				  </div>
          </c:if>
        </c:forEach>
    </div>
    <div style="height: 10px"></div>
    <div class="row">
      <h1>메뉴별 인기 맛집</h1>
      <hr>
        <c:forEach var="vo" items="${list }" varStatus="s">
          <%-- varStatus: ArrayList의 index 번호 --%>
          <c:if test="${s.index>=18 && s.index<30 }">
            <div class="col-md-3">
				    <div class="thumbnail">
				      <a href="food_list.jsp?cno=${vo.cno }">
				        <img src="${vo.poster }" style="width:260px;height: 200px" class="img-rounded">
				        <div class="caption">
				          <p style="font-size: 9px;font-weight: bold">${vo.title }</p>
				        </div>
				      </a>
				    </div>
				  </div>
          </c:if>
        </c:forEach>
    </div>
  </div>
</body>
</html>