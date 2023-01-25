<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/*
 *   대입연산자(=,+=,-=)
 	 +=
 	 let a=10;
 	 a+=20 =========> a=a+20 => 30
 	 -+
 	 let a=10;
 	 a-10  =========> a=a-10 => 0
 */
 window.onload=function(){
 		 let i=10;
 		 console.log("i="+i) //10
 		 i+=20
 		 console.log("i="+i) //30
 		 i-=20
 		 console.log("i="+i) //10
 		  
 		 // 변수 => 무조건 초기화 ==> 선언과 동시에 초기화 let a=10;
 		 let a;
 		 console.log("a="+a)
 		 a=10;
 		 console.log("a="+a)

 	 }
</script>
</head>
<body>

</body>
</html>