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
	$('.trs').hover(function(){
		$(this).css("cursor","pointer")
	},function(){
		$(this).css('cursor',"none")
	})
	$('.trs').click(function(){
		let img=$(this).attr("data-img")
		let name=$(this).attr("data-name");
		let fno=$(this).attr("data-fno")
		$('#food_img').attr("src",img)
		$('#food_name').text(name)
		$('#fno').val(fno)
		$.ajax({
			type:'post',
			url:'../reserve/reserve_date.do',
			data:{"fno":fno},
			success:function(response)
			{
				$('#select_date').html(response)
			}
		})
	})
})
</script>
</head>
<body>
  <table class="table">
   <tr class="success">
     <th class="text-center"></th>
     <th class="text-center">업체명</th>
   </tr>
   <c:forEach var="vo" items="${list }">
     <tr class="trs" data-img="${vo.poster }" data-name="${vo.name }" data-fno="${vo.fno }">
      <td class="text-center">
        <img src="${vo.poster }" style="width: 30px;height: 30px">
      </td>
      <td>${vo.name }</td>
     </tr>
   </c:forEach>
  </table>
</body>
</html>