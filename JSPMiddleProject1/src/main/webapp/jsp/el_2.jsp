<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	EL의 연산자 (EL은 자바가 아님 -> 처리 과정 잘 봐야 함)
	1) 산술 연산자
		+: 순수하게 산술 연산만 가능 (문자열 결합 사용하지 않음) -> 문자열 결합 시: += 사용 
		-
		*
		/: 정수/정수=실수	div 이용
			${10/5} => 2
			${10 div 5} => 2
			${10 div 3} => 3.3333...
		%: 나머지			mod 이용
			${10%3} => 1
			${10 mod 3} => 1
			
		${10+10} => 20
		${"10"+10} => 20 (자동 변환이 된다)
		${"10 "+10} => 오류 발생
		${null+10} => 10 (null값 -> 0으로 인식)
	2) 비교 연산자
	3) 논리 연산자
	4) 삼항 연산자
	5) Empty 연산자
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EL 산술 연산자(+,-,*,/(div),%(mod)): 문자열 결합은 +=, null은 자동으로 0으로 인식</h1>
<%-- &#36;{10+10}=${10+10 }<br>
&#36;{"10"+10}=${"10"+10 }<br>
&#36;{null+100}=${null+100 } null값은 0으로 취급<br>
&#36;{"Hello"+="EL"}=${"Hello"+="EL" } 문자열 결합은 += 사용 <br>
&#36;{10-5}=${10-5 }<br>
&#36;{"10"-5}=${"10"-5 }<br>
&#36;{"10"*5}=${"10"*5 }<br>
&#36;{10/3}=${10/3 }<br>
&#36;{10 div 3}=${10 div 3 }<br>
&#36;{10%3}=${10%3 }<br>
&#36;{10 mod 3}=${10 mod 3 }<br> --%>
<h1>비교 연산자(결과: true/false => 조건문 사용 시)</h1>
<h1>비교 연산자 종류(==(eq),!=(ne),<(lt),>(gt),<=(le),>=(ge))</h1>
<h1>비교 연산자의 주의점: 문자열,날짜,숫자가 동일하다</h1>
<h1>"hong"=="hong" "hong"!="hong"</h1>
<%-- &#36;{10==10}=${10==10}<br>
&#36;{10 eq 10}=${10 eq 10}<br>
&#36;{"hong"=="hong"}=${"hong"=="hong"}<br>
&#36;{"hong" eq "hong"}=${"hong" eq "hong"}<br>
&#36;{"hong"!="hong"}=${"hong"!="hong"}<br>
&#36;{"hong" ne "hong"}=${"hong" ne "hong"}<br>
&#36;{"hong" < "hong"}=${"hong" lt "hong"}<br>
&#36;{"hong" <= "hong"}=${"hong" le "hong"}<br>
&#36;{"hong" > "hong"}=${"hong" gt "hong"}<br>
&#36;{"hong" >= "hong"}=${"hong" ge "hong"}<br> --%>
<h1>논리 연산자(&&(and:직렬),||(or:병렬))</h1>
<%-- &#36;{(10==10)&&(10!=10)}=${(10==10) and (10!=10) }<br>
&#36;{(10==10)||(10!=10)}=${(10==10) or (10!=10) }<br>
&#36;{(10==10)&& not(10!=10)}=${(10==10) and not (10!=10) }<br> --%>
<h1>삼항 연산자 (조건?값1:값2) => if~else 대신 사용. page 변경 시 많이 사용</h1>
<%-- &#36;{(10==10)?'A':'B'}=${(10==10)?'A':'B'}
page=${curpage>1?curpage-1:curpage}, style 적용 --%>
<h1>Empty 연산자: ture/false</h1>
<%
	request.setAttribute("name","");
%>
&#36;{empty name}=${empty name}<br>
${name=="" }
</body>
</html>