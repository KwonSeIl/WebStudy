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
<div class="wrapper row4">
  <footer id="footer" class="clear"> 
    <!-- ################################################################################################ -->
    <div class="one_half first">
      <h6 class="title">공지사항</h6>
        <ul class="nospace linklist">
        <c:forEach var="vo" items="${nList }" varStatus="s">
        <li><a href="#">${s.index+1 }.${vo.subject }(${vo.dbday })</a></li>
        </c:forEach>
      </ul>

    </div>
    <div class="one_half">
      <h6 class="title">오늘의 뉴스</h6>
      <ul class="nospace linklist">
        <li><a href="#">Home Page</a></li>
      </ul>
    </div>
    
  </footer>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row5">
  <div id="copyright" class="clear"> 
    <!-- ################################################################################################ -->
    <p class="fl_left">2023 - SIST 강남쌍용 - <a href="#">3강의장</a></p>
    <p class="fl_right">Project by <a target="_blank" href="http://github.com/chaijewon" title="GitHub">홍길동</a></p>
    <!-- ################################################################################################ --> 
  </div>
</div>
</body>
</html>