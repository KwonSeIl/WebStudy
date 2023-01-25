<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	자바스크립트 역할
	 : 브라우저에서 이벤트 기능 처리(동적). HTML(정적) => HTML을 동적으로 변경
	 --------------------------------------------------------
	 1. 브라우저에서 처리: 자바스크립트
	 2. 브라우저에서 출력: HTML / CSS
	 3. 데이터 관리: 자바
	    자바스크립트에서 오라클 연결 기능 없음 => 서버사이드(NodeJS <==> 스프링)
	 => 브라우저 (HTML, JavaScript) <============> 프로그램 언어(자바, C#, 파이썬)
	 													  ---
	 	자바 => 오라클과 연결이 가능 
	 	=> 자바: 자바스크립트 매칭
	 	  -----------------
	 	  ~VO : {} => Objet
	 	  ~List : [{},{},{}...] => Array
	 	  		 -------------- JSON(자바스크립트 객체 표현법)
	 => 브라우저 안에서 자바에서 전송된 데이터를 받아서 동적으로 변환
	 				--------------
	 사용법
	  1) 내부 스크립트 (한개의 파일에서만 사용이 가능)
	  	<head>
	  	  <script>
	  	    처리
	  	  </script>
	  	</head>
	  2) 외부 스크립트 (여러개 파일에서 사용이 가능) => 라이브러리
	  	.js(확장자)
	  	원격 <script src="원격(URL)"> (O) => import
	  	내부 <script src="경로명">
	  3) 인라인 스크립트 (한개의 태그에서만 제어)
	  	<a href="javascript:함수호출">
	 버전 (ES6)
	   ES5: var(변수 => 자동 지정 변수) var i=10; ==> i:Number, var i="aaa" ==> i:String
	   		var 단점: 스코프가 명확하지 않다
	   				 ---- 사용범위
	   				 function a()
	   				 {
	   				 	if()
	   				 	{
	   				 		var i=10;
	   				 	}
	   				 	사용이 가능
	   				 }
	   ES6: let => 블록이 종료되면 사라진다
	   		const => 상수형 변수 (final)
	   		람다식(화살표 함수)
	   		function display(){}
	   		let display=function(){}
	   		let display=()=>{} ====> =>(화살표 함수는 function을 제거, return을 제거)
	   		------------------
	   		같다(==,===) 같지 않다(!=,!==)
	   		생략이 가능 ; 
	   자바 스크립트 출력(System.out.println())
	   -----------------------------------
	   1. alert() => Modal
	   2. consol.log() : 디버깅
	   3. document.wirte() : 브라우저 화면에 출력
	   4. 태그.innerHTML="" : Ajax,VueJS,ReactJS에서 많이 사용
	   -------------------------------------------------- 문자열(비교연산자)
	   
	   자바스크립트 문법
	     1) 변수 선언 (변수의 데이터형이 없다) => 값에 따라 자동으로 데이터형 변경
	       let i=10;
	           -- 변수
	            => 식별자
	               키워드는 사용 금지(if,let..)
	               특수문자 사용 가능(_,$)
	               숫자로 시작할 수 없다
	               공백이 있으면 안된다 
	               대소문자를 구분한다
	               문자 길이는 상관없다(보통 3~10자 사이)
	            => 규칙 (문법 사항은 아니다) 288page
	               생성자는 대문자
	               변수, 메소드, 함수는 소문자
	               단어가 여러개인 경우: 시작 대문자, _
	     2) 데이터형
	       let i=10 ==> 자동으로 i변수:int
	       let i=10.5 ==> i:double
	       --------------------------- 기본적으로 i:Number ==> '10' => Number('10')
	       let i='A' ==> i:char
	       let i="A" ==> i:string
	       --------------------------- i:string ==> String(10) => "10"
	       let i=true ==> i:boolean
	       ------------------------ Boolean(1) => true, Boolean(0) => false (C언어): 연산처리가 되면 숫자로 가능
	       let i={} ==> 객체 => i:object
	       		let i={name:'홍길동',age:30}
	       			   ----        ---
	       			    |           |
	       			    -------------
	       			          |
	       			        멤버변수
	       			      i.name, i.age
	       let i=[] ==> 배열 => i:Array
	       let i=function(){} ==> 함수형 => i:function
	       ------------------------------------------- 연산자, 제어문 처리, 함수, 클래스(객체지향), 프로토타입 => 라이브러리
	     1. main함수 => 시작과 동시에 자동으로 호출되는 함수
	       = 일반 자바그크립트
	         window.onload=function()
	         {
	         
	         }
	       = Jquery
	         $(function(){
	         
	         })
	       ---------------------------------
	       = VueJS
	         mounted(){}
	       = ReactJS
	         componentDidMount(){} => 15이전
	         => Hooks => 18버전
	            Effect()
	       -------------------------------- MVC
	       
	       
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
// 한줄 주석 => 289page
/*
 *   여러줄 주석
 */
 window.onload=function(){
	// main 함수
	//1. 변수 선언
	let a=10;
	let b=10.5;
	let c='ABC';
	let d="ABC";
	let e=[1,2,3,4,5,"홍길동"]; // Array배열(Object형이기 때문에 다른 데이터형 첨부도 가능)
	let f={name:"홍길동",sex:"남자",age:20};
	// ;은 생략이 가능, 대소문자 구분: 스크립트(단순한 언어)
	// 출력
	console.log("a="+a);
	console.log("b="+b);
	console.log("c="+c);
	console.log("d="+d);
	console.log("e="+e);
	console.log("f="+f);
	// 출력 => 브라우저에 출력
	document.write("a="+a+"<br>");
	document.write("b="+b+"<br>");
	document.write("c="+c+"<br>");
	document.write("d="+d+"<br>");
	document.write("e="+e+"<br>");
	document.write("f="+f+"<br>");
	document.write("<hr>");
	/*
		자바스크립트에서 지원하는 데이터형
		1. number: 정수, 실수(숫자형)
		2. string: 문자(''), 문자열("")
				   ------- 여러문자 취급이 가능 'ABC'
		3. object: 배열([]), 객체({})
		4. boolean: true/false ==> Boolean(1):true,Boolean(0):false
		5. null: 값이 없는 경우
		6. undefined: 선언이 안된 경우
		==> 데이터 확인: typeof		
	*/
	document.write("a="+typeof a+"<br>")
	document.write("b="+typeof b+"<br>")
	document.write("c="+typeof c+"<br>")
	document.write("d="+typeof d+"<br>")
	document.write("e="+typeof e+"<br>")
	document.write("f="+typeof f+"<br>")
	let i
	document.write("i="+typeof i+"<br>") //undefined
	document.write("j="+typeof j+"<br>") //undefined
	let k='';
	document.write("k="+typeof k+"<br>") //string
	//데이터형이 달라질 수 있다
	let o=10
	document.write("o="+typeof o+"<br>")
	o='ABC';
	document.write("o="+typeof o+"<br>")
	o={name:"홍길동"}
	document.write("o="+typeof o+"<br>")
	/*
		문자열 => '', "" ==> 오류 발생
	*/
}
</script>
<title>Insert title here</title>
</head>
<body>

</body>
</html>