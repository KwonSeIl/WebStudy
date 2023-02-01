<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">Lorem</a></li>
      <li><a href="#">Ipsum</a></li>
      <li><a href="#">Dolor</a></li>
    </ul>
  </div>
</div>
<div class="wrapper row3">
  <main class="container clear"> 
    <div class="sidebar one_quarter first"> 
      <h6>${sessionScope.name } 님의 관리페이지</h6>
      <%-- 메뉴 --%>
      <jsp:include page="../adminpage/admin_menu.jsp"></jsp:include>
    </div>
    <div class="content three_quarter"> 
       <jsp:include page="${admin_jsp }"></jsp:include>
    </div>
    <!-- / main body -->
    <div class="clear"></div>
  </main>
  </div>
</body>
</html>