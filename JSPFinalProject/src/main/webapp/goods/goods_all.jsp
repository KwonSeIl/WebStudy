<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <!-- ################################################################################################ -->
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">Lorem</a></li>
      <li><a href="#">Ipsum</a></li>
      <li><a href="#">Dolor</a></li>
    </ul>
    <!-- ################################################################################################ --> 
  </div>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <!-- ################################################################################################ -->
    <div class="content"> 
      <!-- ################################################################################################ -->
      <div id="gallery">
        <figure>
          <header class="heading">
            <h2 class="sectiontitle">전체 상품 목록</h2>
          </header>
          <ul class="nospace clear">
            <c:forEach var="vo" items="${list }" varStatus="s">
              <li class="one_quarter ${s.index%4==0?'first':'' }"><a href="#"><img src="${vo.goods_poster }" title="${vo.goods_name }"></a></li>
            </c:forEach>
          </ul>
          <figcaption></figcaption>
        </figure>
      </div>
      <!-- ################################################################################################ --> 
      <!-- ################################################################################################ -->
      <!--  class="current" -->
      <nav class="pagination">
        <ul>
          <c:if test="${startPage>1 }">
          <li><a href="../goods/goods_all.do?page=${startPage-1 }">&laquo; Previous</a></li>
          </c:if>
           <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <li ${i==curpage?"class==current":"" }><a href="../goods/goods_all.do?page=${i }">${i}</a></li>
          </c:forEach>
          <c:if test="${endPage<totalpage }">
          <li><a href="../goods/goods_all.do?page=${endPage+1 }">Next &raquo;</a></li>
          </c:if>
        </ul>
      </nav>
      <!-- ################################################################################################ --> 
    </div>
    <!-- ################################################################################################ --> 
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>