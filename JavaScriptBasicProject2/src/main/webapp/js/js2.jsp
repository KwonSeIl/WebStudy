<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/*
 * 	배열 = ["","",""] => 혼합이 가능
 	객체 = {}
 */
 window.onload=function(){
	 let arr=["홍길동",20,'A',30.5,"영업",3500]; //기능을 알 수 없다
	 //자바 Object[] obj={"홍길동",20,'A',30.5,"영업",3500}
	 for(let data of arr)
	 {
		 document.write(data+"<br>")
	 }
	 //객체 형식으로 변경 ==> JSON, JSONP {변수:값,변수:값...} => Ajax,Vue,React => if,map
	 let sawon={sabun:1,name:"홍길동",age:20,dept:"영업부",pay:3500}
	 /*
	 	class sawon
	 	{
		 int sabun;
		 String name;
		 int age;
		 String dept;
		 int pay
	 	}
	 
	 	sawon sa=new sawon();
	 	sa.sabun=1
	 	sa.name="홍길동"
	 	...
	 */
	 
	 document.write("<h1>====== 자바스크립트 객체 사용법 1(JSON)======</h1>")
	 document.write("사번:"+sawon.sabun+"<br>")
	 document.write("이름:"+sawon.name+"<br>")
	 document.write("나이:"+sawon.age+"<br>")
	 document.write("부서:"+sawon.dept+"<br>")
	 document.write("연봉:"+sawon.pay+"<br>")
	 
	 document.write("<h1>====== 자바스크립트 객체 사용법 2(JSON)======</h1>")
	 document.write("사번:"+sawon['sabun']+"<br>")
	 document.write("이름:"+sawon['name']+"<br>")
	 document.write("나이:"+sawon['age']+"<br>")
	 document.write("부서:"+sawon['dept']+"<br>")
	 document.write("연봉:"+sawon['pay']+"<br>")
	 
	 document.write("<h1>====== 자바스크립트 객체 사용법 3(JSON)======</h1>")
	 /*document.write("사번:"+sawon[0]+"<br>")
	 document.write("이름:"+sawon[1]+"<br>")
	 document.write("나이:"+sawon[2]+"<br>")
	 document.write("부서:"+sawon[3]+"<br>")
	 document.write("연봉:"+sawon[4]+"<br>")*/
	 for(let key in sawon)
	 {
		 document.write(key+":"+sawon[key]+"<br>")
	 }
 }
</script>
</head>
<body>

</body>
</html>