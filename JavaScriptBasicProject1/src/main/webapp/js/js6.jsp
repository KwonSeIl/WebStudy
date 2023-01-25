<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	제어문
	  조건문 ===> 삼항 연산자
	    = 단일 조건문
	      if(조건문)
	      {
	      	조건이 true일 때 처리
	      }
	    = 선택 조건문
	      if(조건문)
	      {
	      	조건이 true일 때 처리
	      }
	      else
	      {
	      	조건이 false일 때 처리
	      }
	    = 다중 조건문
	      if(조건문)
	      {
	      }
	      else if(조건문)
	      {
	      }
	      ...
	      else
	      {
	      }
	    선택문: switch~case
	    반복문: while, do~while, *****for문의 종류가 많다
	    반복제어문: break, continue
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	/*let a=10;
	if(a===11)
	{
		alert("a는 10입니다!!") //조건이 true일 때 처리되는 문장
	}
	alert("a는 10이 아닙니다!!")*/
	/*let a=11;
	if(a%2==0)
	{
		alert("a는 짝수입니다")
	}
	else
	{
		alert("a는 홀수입니다")
	}*/
	let a="A";
	if(a%2==0)
	{
		alert("a는 짝수입니다")
	}
	else if(a%2!==0)
	{
		alert("a는 홀수입니다")
	}
	else
	{
		alert("a는 숫자가 아닙니다")
	}
}
</script>
</head>
<body>

</body>
</html>