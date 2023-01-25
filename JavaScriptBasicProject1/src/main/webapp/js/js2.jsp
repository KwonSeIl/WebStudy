<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	연산자
	1. 단항연산자(++,--,!)
	2. 형변환 연산자
	  Number(), parseInt(), String, Boolean
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// 292page => 연산자
window.onload=function(){
	//단항연산자(++,--,!)
	let a=10;
	let b=a++; // 후치 연산자 => 대입, 증가
	console.log("a="+a+",b="+b); //a=10, b=a, a=a+1 => b=10, a=11
	
	a=10;
	b=++a; // 전치 연산자 => 증가, 대입
	console.log("a="+a+",b="+b); //a=10, a=a+1, b=a => a=11, b=11
	
	let c=false;
	c=!c
	console.log("c="+c); // true => 부정 연산자
	
	// 주의점
	let d=1;
	d=!d;
	console.log('d='+d) //false => 모든 숫자가 0, 0.0이 아닌 수는 true
	
	// 형변환 연산자 => 자바에서 전송(string)
	let e="10";
	console.log("e="+typeof e)//string
	console.log("e="+typeof Number(e)) //숫자형으로 변환
	console.log("e="+typeof parseInt(e)) //숫자형 변환
	
	console.log("e="+typeof Boolean(e)+","+Boolean(e)); // boolean, true
	// 자바와 다른 점
	/*
		숫자 => 문자열, Boolean으로 변경이 가능
		변수에는 데이터형을 사용하지 않는다 (let,const) => 확인 시 typeof 사용
		모든 숫자는 true/false로 변경이 가능하다
	*/
}
</script>
</head>
<body>

</body>
</html>