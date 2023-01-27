<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container-fluid{
	margin-top: 30px;
}
.row{
	width: 600px;
	margin: 0px auto;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let year=0;
let month=0;
let day=0;
$(function(){

	$.ajax({
		type:'get',
		url:'ym.jsp',
		success:function(result)
		{
			//alert(result) => let json={}
			let json=JSON.parse(result.trim());
			year=parseInt(json.year)
			month=parseInt(json.month)
			day=parseInt(json.day)
			
			$('#year').text(year)
			$('#month').text(month)
			
			$.ajax({
				type:'post',
				url:'date_result.jsp',
				data:{"year":$('#year').text(),"month":$('#month').text()},
				success:function(result)
				{
					$('.datePrint').html(result)
				}
			})
		}
	})
	$('.year').change(function(){
		let year=$('.year').val();
		let month=$('.month').val();
		$('#year').text(year);
		$('#month').text(month);
		$.ajax({
			type:'post',
			url:'date_result.jsp',
			data:{"year":year,"month":month},
			success:function(result)
			{
				$('.datePrint').html(result)
			}
		})
	})
	$('.month').change(function(){
		let year=$('.year').val();
		let month=$('.month').val();
		$('#year').text(year);
		$('#month').text(month);
		$.ajax({
			type:'post',
			url:'date_result.jsp',
			data:{"year":year,"month":month},
			success:function(result)
			{
				$('.datePrint').html(result)
			}
		})
	})
	
	
})
</script>
</head>
<body>
  <div class="container">
    <h1 class="text-center"><span id="year"></span>년도&nbsp;&nbsp;<span id="month"></span>월</h1>
    <div class="row">
      <table class="table">
        <tr>
          <td>
            <select class="input-sm year">
              <c:forEach var="i" begin="2023" end="2030">
                <option>${i }</option>
              </c:forEach>
            </select>년도
            <select class="input-sm month">
              <c:forEach var="i" begin="1" end="12">
                <option>${i }</option>
              </c:forEach>
            </select>월
          </td>
        </tr>
      </table>
    </div>
    <div style="height: 10px"></div>
    <div class="row datePrint">
      
    </div>
  </div>
</body>
</html>