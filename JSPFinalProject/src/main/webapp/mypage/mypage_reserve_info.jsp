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
  <h2 class="sectiontitle">예약 정보</h2>
  <table class="table">
   <tr>
     <td class="text-center" width=30% rowspan="9">
       <img src="${vo.fvo.poster }" style="width: 100%">
     </td>
     <td colspan="2"><h3>${vo.fvo.name }&nbsp;<span style="color:orange">${vo.fvo.score }</span></h3></td>
   </tr>
   <tr>
     <th width=20%>예약번호</th>
     <td width=50%>${vo.reserve_no }</td>
   </tr>
   <tr>
     <th width=20%>주소</th>
     <td width=50%>${vo.fvo.address }</td>
   </tr>
   <tr>
     <th width=20%>전화</th>
     <td width=50%>${vo.fvo.tel }</td>
   </tr>
   <tr>
     <th width=20%>음식종류</th>
     <td width=50%>${vo.fvo.type }</td>
   </tr>
   <tr>
     <th width=20%>주차</th>
     <td width=50%>${vo.fvo.parking }</td>
   </tr>
   <tr>
     <th width=20%>예약일</th>
     <td width=50%>${vo.rdate }</td>
   </tr>
   <tr>
     <th width=20%>예약시간</th>
     <td width=50%>${vo.rtime }</td>
   </tr>
   <tr>
     <th width=20%>인원</th>
     <td width=50%>${vo.inwon }</td>
   </tr>
  </table>
</body>
</html>