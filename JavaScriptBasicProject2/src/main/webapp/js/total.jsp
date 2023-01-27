<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	태그를 제어, CSS 추가, 사용자 입력에 대한 유효성 => 자바스크립트
	----------------------------------- 브라우저에서 제어
	=> 라이브러리화(표준화): Jqery(Ajax), Vue, React, angularjs(구글 => 제임스 고슬링)
												--------- 기반 (vuejs)
	자바스크립트 위치
	  <head>
	    <script> 내부 스크립트(파일 한개 안에서만 사용 가능)
	    </script>
	  </head>
	  
	  <a href="javascript:history.back()"> => 인라인 스크립트
	  
	  <script src="파일읽기">: 외부 스크립트
	자바스크립트의 변수선언
	let, var, const => 초기값이나 값을 설정시마다 데이터형으로 자동으로 지정 
	---       ------ (상수형 변수)
	= 자바스크립트에서 사용되는 데이터형
	  number(int,double)
	  string(string,char)
	  boolean
	  array,object
	  undefined
	  function
	  ------------------- 확인 시: typeof 변수
	= ;은 필수 사항이 아니다
	
	= 연산자
	  산술연산자: +,-,*,/(정수/정수=실수),%
	  		  -- 문자열 결합이 가능 ==> 나머지 연산자는 자동으로 숫자형으로 변경
	  단항연산자: ++, --, !
	  비교연산자: ===, !==, <,>,<=,>=
	  논리연산자: &&, ||
	  대입연산자: =, +=, -=
	= 형변환
	  Number("10.5") => 10.5
	  Number("10") => 10 ===> parseInt("10")
	  String(10) => "10"
	  Boolean(1) => true,Boolean(0) => false
	  
	= 제어문
	  if, if~else, if~else if~else
	  while, do~while, switch
	  for문
	    일반 for(let i=0;i<=10;i++)
	      => 배열/객체
	      for(let a of 배열명)
	      배열명.map(function(m){})
	      배열명.forEah(function(m){})
	= 배열 => []
	  객체 => {키:값,키:값..} ==> JSON => 객체 표현 방법
	= 함수
	  function func_name(매개변수)
	  {
	  	return 값
	  }
	  
	  let func_name=function(){}
	  let func_name()=>{}
	  
	=> 함수 안에 함수를 매개변수로 전ㄴ달할 수 있다: callback => 호출은 보통 태그안에서 한다
	
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>