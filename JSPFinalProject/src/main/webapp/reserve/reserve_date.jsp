<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.days_true').hover(function(){
		$(this).css("cursor","pointer")
	})
	$('.days_true').click(function(){
		let year=$(this).attr("data-year");
		let month=$(this).attr("data-month");
		let day=$(this).text();
		$('#r_day').text(year+"년도 "+month+"월 "+day+"일")
		$('#reserveday').val(year+"년도 "+month+"월 "+day+"일")
		$.ajax({
			type:'post',
			url:'../reserve/reserve_time.do',
			data:{"day":day},
			success:function(response)
			{
				$('#select_time').html(response);
			}
		})
	})
})
</script>
</head>
<body>
   <table class="table">
     <tr>
       <td class="text-center"><h3>${year }년도  ${month }월</h3></td>
     </tr>
   </table>
   <div style="height: 5px"></div>
   <c:set var="week" value="${week }"/> 
   <%-- int week=3 --%>
   <table class="table">
     <tr>
      <c:forEach var="w" items="${strWeek }">
        <th class="text-center" height="35">${w }</th>
      </c:forEach>
     </tr>
     <c:forEach var="i" begin="1" end="${lastday }" varStatus="s">
       <c:if test="${i==1 }">
         <tr>
         <c:forEach var="j" begin="1" end="${week }">
           <td class="text-center" height="35"></td>
         </c:forEach>
       </c:if>
       <c:if test="${rdays[s.index]==1}">
        <td class="days_true text-center danger" height="35" data-year="${year }" data-month="${month }"><b>${i }</b></td>
       </c:if>
        <c:if test="${rdays[s.index]!=1}">
        <td class="days_false text-center" height="35" style="color:gray">${i }</td>
       </c:if>
       <c:set var="week" value="${week+1 }"/> <%-- week++ --%>
       <c:if test="${week>6 }">
         <c:set var="week" value="0"/><%-- week=0 --%>
         </tr>
         <tr>
       </c:if>
     </c:forEach>
     </tr>
   </table>
</body>
</html>