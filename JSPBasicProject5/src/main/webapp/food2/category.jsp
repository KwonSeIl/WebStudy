<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.aaa').click(function(){
		let no=$(this).attr("data-no");
		$.ajax({
			type:'get',
			url:'../food2/category_result.jsp',
			data:{"cate":no},
			success:function(result)
			{
				$('.view').html(result);
			}
		})
	})
})
</script>
</head>
<body>
  <div style="height: 10px"></div>
  <div class="row">
   <div class="text-center">
    <span class="btn btn-sm btn-danger aaa" data-no="1">믿고보는 맛집 리스트</span>
    <span class="btn btn-sm btn-info aaa" data-no="2">지역별 맛집 리스트</span>
    <span class="btn btn-sm btn-success aaa" data-no="3">메뉴별 인기 맛집 리스트</span>
   </div>
   <div style="height: 10px"></div>
   <div class="row view">
     
   </div>
  </div>
</body>
</html>
