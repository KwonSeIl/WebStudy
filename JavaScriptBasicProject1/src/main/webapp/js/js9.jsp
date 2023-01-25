<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/*
 * 	do~while: 사용빈도가 거의 없다 = 조건 후조건 => 무조건 한 번 이상 수행
 		초기값
 		do
 		{
 			반복수행문장
 			증가식
 		}
 	while: 무한루프(반복횟수가 없는 경우) = 조건 선조건 => 수행을 못할 수 있다
 		초기값
 		while(조건문)
 		{
 			반복수행문장
 			증가식
 		}
 	for: 반복횟수가 있다(가장 많이 사용) = 출력
 		for(초기값;조건식;증가식)
 		{
 			반복수행문장
 		}
 	=> for in, for of, forEach, map
 					   ------------
 */
 window.onload=function(){
 		let i=1;
 		document.write("<h1>do~while</h1>")
 		do{
 			document.write("i="+i+"<br>")
 			i++;
 		}while(i<=10)
 		i=1;
 	 	document.write("<hr>")
 	 	document.write("<h1>while</h1>")
 	 	while(i<=10)
 	 	{
 	 		document.write("i="+i+"<br>")
 			i++;
 	 	}
 	 	i=1;
 	 	document.write("<hr>")
 	 	document.write("<h1>for</h1>")
 	 	for(i=1;i<=10;i++)
 	 	{
 	 		document.write("i="+i+"<br>")
 	 	}
 	 	document.write("<hr>")
 	 	document.write("<h1>for==break</h1>")
 	 	for(i=1;i<=10;i++)
 	 	{
 	 		if(i==5) break; //for 종료
 	 		document.write("i="+i+"<br>")
 	 	}
 	 	document.write("<hr>")
 	 	document.write("<h1>for==continue</h1>")
 	 	for(i=1;i<=10;i++)
 	 	{
 	 		if(i==5) continue; //for에서 제외(5를 제외해라)
 	 		document.write("i="+i+"<br>")
 	 	}
 	}
 	
</script>
</head>
<body>

</body>
</html>