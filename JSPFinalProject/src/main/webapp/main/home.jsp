<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<div class="wrapper row3">
  <div id="slider" class="clear"> 
    <div class="flexslider basicslider">
      <ul class="slides">
        <li><a href="#"><img class="radius-10" src="../images/demo/slides/back1.jpg" style="width:978px;height:400px"></a></li>
        <li><a href="#"><img class="radius-10" src="../images/demo/slides/back2.jpg" style="width:978px;height:400px"></a></li>
        <li><a href="#"><img class="radius-10" src="../images/demo/slides/back3.jpg" style="width:978px;height:400px"></a></li>
      </ul>
    </div>
  </div>
</div>
<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <ul class="nospace group btmspace-80">
      <li class="one_third first">
        <article class="service"><i class="icon fa fa-ambulance"></i>
          <h6 class="heading"><a href="#">맛집</a></h6>
          <p>혼자 식사를 즐길 수 있는 곳부터 특별한 손님을 접대할 수 있는 곳까지, 무엇을 먹어야 할지 고민되는 사람들을 위해 서울의 유명 맛집을 소개</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-h-square"></i>
          <h6 class="heading"><a href="#">여행</a></h6>
          <p>서울은 놀라운 음식, 아름다운 유적지, 그리고 경이로운 현대적인 명소로 알려져 있으며 모든 연령대의 매력적인 여행지다. 당신은 강남 구역 혹은 명동 에서 쇼핑을 하거나, 북촌 한옥 마을 을 방문하거나 혹은 화려한 길거리 음식을 먹어보고싶을 것이다. </p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-hospital-o"></i>
          <h6 class="heading"><a href="#">레시피</a></h6>
          <p>온라인으로 간단한 요리을 찾고 비교하세요. 온라인으로 검색하고 간단한 요리. 쉬운 접근. 검색 및 발견. 쉽게 찾기. 빠른 결과. 다양한 제품. 제품 비교. 서비스: 연중무휴 액세스 가능, 온라인 제품, 즉각적인 결과, 폭넓은 다양성.</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
    </ul>
    <!-- ################################################################################################ -->
    <h2 class="sectiontitle">믿고보는 맛집 리스트</h2>
    <!-- ################################################################################################ -->
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <c:forEach var="vo" items="${list }" varStatus="s"><%-- list의  index번호 --%>
        <c:if test="${s.index>=0 && s.index<12 }">
	        <li>
	          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" title="${vo.subject }">
	            <figcaption><a href="../food/food_list.do?cno=${vo.cno }">${vo.title }</a></figcaption>
	          </figure>
	        </li>
        </c:if>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">지역별 인기 맛집</h2>
    <!-- ################################################################################################ -->
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
        <c:forEach var="vo" items="${list }" varStatus="s"><%-- list의  index번호 --%>
        <c:if test="${s.index>=12 && s.index<18 }">
	        <li>
	          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" title="${vo.subject }">
	            <figcaption><a href="../food/food_list.do?cno=${vo.cno }">${vo.title }</a></figcaption>
	          </figure>
	        </li>
        </c:if>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">메뉴별 인기 맛집</h2>
    <!-- ################################################################################################ -->
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
        <c:forEach var="vo" items="${list }" varStatus="s"><%-- list의  index번호 --%>
        <c:if test="${s.index>=18 && s.index<30 }">
	        <li>
	          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" title="${vo.subject }">
	            <figcaption><a href="../food/food_list.do?cno=${vo.cno }">${vo.title }</a></figcaption>
	          </figure>
	        </li>
        </c:if>
       </c:forEach>
      </ul>
    </div>
    <!-- ################################################################################################ -->
    <h2 class="sectiontitle">최근 방문 맛집</h2>
    <!-- ################################################################################################ -->
      <div class="inline">
       <c:forEach var="cvo" items="${cList }" varStatus="s">
        <c:if test="${s.index<9 }">
         <img src="${cvo.poster }" style="width: 100px;height: 100px">
        </c:if>
       </c:forEach>
      </div>
    <!-- ################################################################################################ --> 
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>