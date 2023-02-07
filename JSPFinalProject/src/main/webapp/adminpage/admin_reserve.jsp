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
    <main class="container clear">
     <h2 class="sectiontitle">예약 목록</h2>
     <div style="height: 5px"></div>
     <table class="table">
       <tr>
        <th class="text-center">번호</th>
        <th class="text-center"></th>
        <th class="text-center">아이디</th>
        <th>업체명</th>
        <th class="text-center">예약일</th>
        <th class="text-center">예약시간</th>
        <th class="text-center">인원</th>
        <th class="text-center"></th>
       </tr>
       <c:forEach var="vo" items="${list }">
         <tr>
	        <td class="text-center">${vo.rno }</td>
	        <td class="text-center">${vo.id }</td>
	        <td class="text-center">
	         <img src="${vo.fvo.poster }" style="width:30px;height: 30px">
	        </td>
	        <td>${vo.fvo.name }</td>
	        <td>${vo.rdate}</td>
	        <td class="text-center">${vo.rtime }</td>
	        <td class="text-center">${vo.inwon }</td>
	        <td class="text-center">
	          <c:if test="${vo.ok=='n' }">
	          <a href="../adminpage/admin_reserve_ok.do?rno=${vo.rno }" class="btn btn-xs btn-success">승인 대기</a>
	          </c:if>
	          <c:if test="${vo.ok=='y' }">
	          <span class="btn btn-xs btn-default">승인 완료</span>
	          </c:if>
	        </td>
	       </tr>
       </c:forEach>
     </table>
    </main>
  </div>
</body>
</html>