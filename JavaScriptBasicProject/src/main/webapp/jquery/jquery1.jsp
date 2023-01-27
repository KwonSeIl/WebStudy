<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
<%-- 주의점: 버전 충돌 시 실행이 안된다: main.jsp에 가져다 놔야 한다 --%>
/*
 * 	jquery: 자바스크립트 라이브러리
 	------------------------ 태그 제어(CSS적용, 화면 출력, 자바연동(Ajax)) => 연산자, 제어문, 변수는 자바스크립트를 이용한다
 	jqery의 핵심: selector(태그 선택자)
 				document.querySelector("선택자") => $("선택자")
 				window.onload=function(){} => $(function(){}), $(document).ready(function(){})
 				jquery ui 존재 => 애니메이션
 	-------------------------------------------------------------------------------------------
 	태그 제어
 	  val() => <input>,<select>의 값을 가지고 올 때 (getter). setter: val(값)
 	  text() => <a href="">값</a> => innerText => text(값)
 	  attr() => <img src="값"> => attr("src") => attr("src","값")
 	  html() => JSON
 	  append() => 태그를 첨부할 때 사용
 	  
 	이벤트 처리
 	  click(function(){})
 	  on("click",function(){})
 	  change(function(){})
 	  keyup(function(){})
 	  hover(function(){})
 	  mousedown(function(){})
 	  
 	디자인
 	  css("속성","값")
 	  css({"속성":값,"속성":값..})
 */
 $(function(){ // = window.onload=function(){}:일반자바스크립트, mounted=function(){}:VueJS, componentDidMount():react class형, useEffect(()=>{}):react function형
	 //selector => 371page $("선택자").이벤트처리, $("선택자").값읽기/값설정, $("선택자").CSS설정 => $ => EL  <c:out/>
	 // $.ajax(), $.getJson(), $.eval(), $.JSON.parse()
	 $('.a').css("color","green")
	 //$('#b').css("color","red").css("backgroundColor","yellow")
	 $('#b').css({"color":"red","backgroundColor":"yellow"})
//	  ------ ------------------
	 //document.querySelector(".c").style.color="cyan"; //selcetor은 한개만 된다
 })
</script>
</head>
<body>
  <h1 class="c">Hello Jquery</h1>
  <h1 class="a">Hello Jquery</h1>
  <h1 class="c">Hello Jquery</h1>
  <h1 class="a">Hello Jquery</h1>
  <h1 class="c">Hello Jquery</h1>
  <h1 class="a">Hello Jquery</h1>
  <h1 id="b">Hello Jquery</h1>
</body>
</html>