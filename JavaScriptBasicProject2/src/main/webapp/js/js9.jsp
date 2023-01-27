<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container{
	margin-top: 30px;
}
.row{
	width: 100%;
	margin: 0px auto;
}
</style>
<script type="text/javascript">
let index=1;
function prev()
{
	index--;
	if(index<1)
		index=7;
	let img=document.querySelector('img');
	img.src=index+".jpg";
}
function next()
{
	index++;
	if(index>7)
		index=1;
	let img=document.querySelector('img');
	img.src=index+".jpg";
}
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <div class="text-center">
        <img src="1.jpg" style="width: 450px;height: 500px">
      </div>
    </div>
    <div style="height: 10px"></div>
    <div class="row">
      <div class="text-center">
        <input type="button" class="btn btn-lg btn-danger" value="이전" onclick="prev()">
        <input type="button" class="btn btn-lg btn-primary" value="다음" onclick="next()">
      </div>
    </div>
  </div>
</body>
</html>