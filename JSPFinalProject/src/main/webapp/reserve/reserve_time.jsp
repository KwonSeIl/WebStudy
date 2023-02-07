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
	$('.times').click(function(){
		let time=$(this).text();
		$('#r_time').text(time);
		$('#reservetime').val(time);
		$.ajax({
			type:'post',
			url:'../reserve/reserve_inwon.do',
			success:function(response)
			{
				$('#select_inwon').html(response)
			}
		})
	})
})
</script>
</head>
<body>
  <c:forEach var="time" items="${rtime }">
    <span class="btn btn-sm btn-success times">${time}</span>&nbsp;
  </c:forEach>
</body>
</html>