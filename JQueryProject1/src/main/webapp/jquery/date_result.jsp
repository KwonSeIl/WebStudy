<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String strYear=request.getParameter("year");
	String strMonth=request.getParameter("month");
	System.out.println("Year:"+strYear);
	System.out.println("Year:"+strMonth);
	int year=Integer.parseInt(strYear);
	int month=Integer.parseInt(strMonth);
	
	//일반 로직
	//1. 1년도 1월 1일부터 총 날 수 (전년도+전달+1)%7 (요일 구하기)
	//2. Calendar
	Calendar cal=Calendar.getInstance(); //객체 생성
	// 날짜 설정
	cal.set(Calendar.YEAR,year);
	cal.set(Calendar.MONTH,month-1);
	cal.set(Calendar.DATE,1);
	
	//요일 구하기
	int week=cal.get(Calendar.DAY_OF_WEEK);
	week=week-1;
	//마지막 날짜 구하기
	int lastday=cal.getActualMaximum(Calendar.DATE);
	String[] strWeek={"일","월","화","수","목","금","토"};
%>
<c:set var="year" value="<%=year %>"/>
<c:set var="month" value="<%=month %>"/>
<c:set var="week" value="<%=week %>"/>
<c:set var="strWeek" value="<%=strWeek %>"/>
<c:set var="lastday" value="<%=lastday %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <table class="table">
    <tr class="danger">
     <c:set var="color" value="black"/>
      <c:forEach var="ss" items="${strWeek }" varStatus="s">
        <td class="text-center">
          <c:choose>
            <c:when test="${s.index==0 }">
              <c:set var="color" value="red"/>
            </c:when>
            <c:when test="${s.index==6 }">
              <c:set var="color" value="blue"/>
            </c:when>
            <c:otherwise>
              <c:set var="color" value="black"/>
            </c:otherwise>
          </c:choose>
          <b style="color:${color}">${ss }</b>
        </td>
      </c:forEach>
    </tr>
  </table>
  <table class="table">
    <c:forEach var="i" begin="1" end="${lastday }">
      <c:choose>
            <c:when test="${week==0 }">
              <c:set var="color" value="red"/>
            </c:when>
            <c:when test="${week==6 }">
              <c:set var="color" value="blue"/>
            </c:when>
            <c:otherwise>
              <c:set var="color" value="black"/>
            </c:otherwise>
          </c:choose>
      <c:if test="${i==1 }">
        <tr>
          <c:forEach var="j" begin="1" end="${week }">
            <td class="text-center">&nbsp;</td>
          </c:forEach>
      </c:if>
      <td class="text-center"><span style="color:${color}">${i }</span></td>
      <c:set var="week" value="${week+1 }"/>
      <c:if test="${week>6 }">
        <c:set var="week" value="0"/>
        </tr>
        <tr>
      </c:if>
    </c:forEach>
    </tr>
  </table>
</body>
</html>