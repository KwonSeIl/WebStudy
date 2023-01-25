<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	이항 연산자
	= 산술 연산자(+,-,*,/,%)
		/ : 나누기
		--------
		  자바 (정수/정수=정수)
		  자바스크립트 (정수/정수=실수)
	= 비교 연산자(===,!==,<,>,<=,>=): 결과값은 boolean => 조건문에서 사용 
		==,!=(혼용) => 경고가 출력됨
		숫자, 문자열 비교시에도 사용을 한다
	= 논리 연산자(&&,||): 결과값은 boolean
	= 대입 연산자(=,+=,-=)
	
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let a=5;
	let b=2;
	console.log("a+b="+(a+b)); //7
	console.log("a-b="+(a-b)); //3
	console.log("a*b="+(a*b)); //10
	console.log("a/b="+(a/b)); //2.5
	console.log("a%b="+(a%b)); //1
	/*
		a%b	==> 양수
		-a%b ==> 음수
		a%-b ==> 양수
		-a%-b ==> 음수
	*/
	// 문자열일 경우: 산술 처리(문자열 결합) => +는 문자열 결합, 나머지 연산자는 자동으로 숫자 변환 후에 연산 처리
	
	// + => 문자열 결합, 산술 처리
	let c="10";
	let d='20';
	// string형
	console.log("c+d="+(c+d))//1020
	// 자바와 다르다
	console.log("c-d="+(c-d)) // 문자형 -> 숫자형으로 변환 -10
	console.log("c*d="+(c*d)) // 200
	console.log("c/d="+(c/d)) // 0.5
	console.log("c%d="+(c%d)) // 10
	
	let e="10"
	let f=20;
	console.log("e+f="+(e+f)) //1020 => 문자열+숫자 => 문자열 (산술연산자 => +(문자열,산술), *,/,%,/ => 숫자 변환 후에 처리)
	
	// 주의점
	let m='A';
	let n=10;
	console.log("m+n="+(m+n)) //A10
	console.log("m-n="+(m-n)) //연산 결과값이 없다 ==> NaN
	
	let k=10;
	let p=0;
	console.log("k/p="+(k/p)) //Infinity => 0으로 나눌 수 없다 (293page)
	/*
		undefined => 변수의 초기화가 안된 상태, 변수 선언이 없는 경우
					 let a
					 console.log(j)
		NaN		  => 문자열+숫자 (연산처리가 안된 상태)
		Infinity  => 0으로 나눌 경우
		
		숫자형으로 문자열일 경우
		"10", '100' => + 연산자는 문자열 결합, 나머지 산술연산자는 숫자로 변환후에 처리가 된다
		숫자형이 아닌 경우 'AB'+1 => NaN
		10/0 => Infinity
		
		=> 자바스크립트 자체에서 데이터를 읽지 못한다 -> 자바를 이용해서 전송
		   자바 ==> 자바스크립트로 전송
		   		   => VO, ArrayList => {},[] ==> JSON
		   		      ------------- 존재하지 않는다
	*/
	
}
</script>
</head>
<body>

</body>
</html>