<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	// const => 상수형 변수 ==> 값을 변경할 수 없다(자바에서 값을 가지고 오는 경우 이용)
	let a=10;
	console.log("a="+a)
	a="AAA"
	console.log("a="+a)
	a=[1,2,3,4,5,"홍길동"]
	console.log("a="+a)
	a={name:"홍길동",age:20} //Object 객체(name.age => 멤버변수다)
	console.log("이름="+a.name+",나이="+a.age)
}
</script>
</head>
<body>

</body>
</html>