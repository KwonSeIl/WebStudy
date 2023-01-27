<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//316page 함수
/*
 * 	1. 함수: 기능 처리(이벤트: 사용자가 행위를 했을 때 -> 사용자 요청에 대한 처리 => 브라우저 안에서만 작동)
 * 	   버튼 클릭, 마우스 오버, 마우스 아웃, 변경, 키보드를 누를 경우, 키보드를 종료..
 	   호출시에 이벤트
 	   onclick,onmouseover,onmouseout,onchange,onkeydown,donkeyup...
 	2. 함수 생성 방법
 	   익명의 함수: function(){} => callback(시스템에 의해 자동으로 호출)
 	   			map(function(m){})
 	   
 	   선언적 함수: function 함수명(){}
 	   			let func=function(){}
 	   			let func=()=>{}
 	3. 함수의 속성
 		------- 리턴형 / 매개변수
 		-----------------------------------------------------
 			리턴형		매개변수
 		-----------------------------------------------------
 			O		O
 						예) function func_name(id,pwd) => 리턴형을 서술하지 않는다, 매개변수는 변수명만 사용한다
 							{
 								return 값;
 							}
 		-----------------------------------------------------
 			O		X
 						예) function func_name()
 							{
 								return 값;
 							}
 		-----------------------------------------------------
 			X		O
 						예) function func_name(name) => void
 							{
 							
 							}
 		-----------------------------------------------------
 			X		X
 						예) function func_name() => void
 							{
 							
 							}
 		-----------------------------------------------------
 		구성요소
 		function func_name() //선언부
 		{
 			//구현부
 		}
 		
 		==> ES6버전
 		let func_name=function(){}
 		let func_name=()=>{}	==> 권장사항
 		
 		let func_name=function(매개변수){}
 		let func_name=(매개변수)=>{}	==> 권장사항
 		---------------------------------------- NodeJS,VueJS,ReactJS
 		=> 람다식(function,return을 생략한다)
 		
 		
 */
 window.onload=function(){
 		func1();	
 		func2("홍길동");
 		let msg=func3();
 		document.write(msg+"<br>")
 		let name=func4("이순신");
 		document.write(name+"님 로그아웃입니다")
}
let func1=function(){
	document.write("func1 Call..<br>");
}
let func2=(name)=>{
	document.write(name+"님 환영합니다!!<br>")	
}
let func3=()=>{
	return "Hello JavaScript";
}
let func4=(name)=>{
	return name;
}
/*
function func1(){
	document.write("func1 Call..<br>"); // 매개변수, 리턴형이 없는 상태의 함수
}
function func2(name)
{
	document.write(name+"님 환영합니다!!<br>")	
}
function func3()
{
	return "Hello JavaScript";
}
function func4(name)
{
	return name;
}*/
</script>
</head>
<body>

</body>
</html>