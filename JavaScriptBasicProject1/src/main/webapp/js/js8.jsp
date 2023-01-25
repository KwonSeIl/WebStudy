<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/*
 *   let div=document.querySelector("#id")
     => $('#id') => e.target.value => $ref
 */
window.onload=function(){ //$(function(){})
	let kor=90;
	let eng=85;
	let math=78;
	
	let total=kor+eng+math
	let avg=total/3.0;
	
	console.log("국어:"+kor)
	console.log("영어:"+eng)
	console.log("수학:"+math)
	console.log("총점:"+total)
	console.log("평균:"+Math.round(avg))
	//다중 조건문은 1개만 실행
	let score='A';
	if(avg>=90)
		score='A'
	else if(avg>=80)
		score='B'
	else if(avg>=70)
		score='C'
	else if(avg>=60)
		score='D'
	else
		score='F'
		
	console.log('score:'+score)
}
</script>
</head>
<body>

</body>
</html>