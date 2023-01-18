<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
   <div class="wrapper row1">
  <header id="header" class="clear"> 
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">서울 맛집 & 서울 여행</a></h1>
    </div>
    <div class="fl_right">
      <ul class="inline">
        <li>ID<input type=text name=id size=10 class="input-sm" placeholder="ID"></li>
        <li>PW<input type=password name=pwd size=10 class="input-sm" placeholder="Password"></li>
         <li><input type=button class="btn btn-lg btn-danger" value="로그인"></li>
      </ul>
    </div>
  </header>
</div>
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    
    <ul class="clear">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li><a class="drop" href="#">회원</a>
        <ul>
          <li><a href="pages/gallery.html">회원가입</a></li>
          <li><a href="pages/full-width.html">아이디찾기</a></li>
          <li><a href="pages/sidebar-left.html">비밀번호찾기</a></li>
        </ul>
      </li>
   <li><a class="drop" href="#">맛집</a>
        <ul>
          <li><a href="../food/food_location.do">지역별맛집검색</a></li><%-- .do => DispatcherServlet 호출 --%>
          <li><a href="pages/full-width.html">맛집추천</a></li>
          <li><a href="pages/sidebar-left.html">맛집예약</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">여행</a>
        <ul>
          <li><a href="pages/gallery.html">명소</a></li>
          <li><a href="pages/full-width.html">자연&관광</a></li>
          <li><a href="pages/sidebar-left.html">쇼핑</a></li>
          <li><a href="pages/sidebar-left.html">호텔</a></li>
          <li><a href="pages/sidebar-left.html">게스트하우스</a></li>
          <li><a href="pages/sidebar-left.html">여행코스</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">레시피</a>
        <ul>
          <li><a href="pages/gallery.html">레시피</a></li>
          <li><a href="pages/full-width.html">쉐프</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">스토어</a>
        <ul>
          <li><a href="../goods/goods_all.do">전체</a></li>
          <%--
          	goods_all.do: DispatcherServlet => Model => 데이터를 받아서 출력한 jsp를 보여준다
          					.do 모델을 찾아서 데이터를 읽어온다
          	goods_all.jsp: 파일 자체를 찾음
           --%>
          <li><a href="../goods/goods_new.do">신상품</a></li>
          <li><a href="../goods/goods_sp.do">특가</a></li>
          <li><a href="../goods/goods_best.do">베스트</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">커뮤니티</a>
        <ul>
          <li><a href="pages/gallery.html">공지사항</a></li>
          <li><a href="pages/full-width.html">자유게시판</a></li>
          <li><a href="pages/sidebar-left.html">후기게시판</a></li>
        </ul>
      </li>
      <li><a href="#">마이페이지</a></li>
    </ul>
  </nav>
</div>
</body>
</html>