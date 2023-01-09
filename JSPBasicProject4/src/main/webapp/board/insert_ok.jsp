<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%-- 화면 출력 부분 아님. 전송된 데이터 받아서 요청 처리만 해줌
	 화면 출력 부분으로 이동을 한다 sendRedirect("list.jsp")
	 요청 처리
	 	=> 목록 출력 ==> insert, delete
	 	=> 상세보기 ==> update
 --%>
<jsp:useBean id="dao" class="com.sist.dao.BoardDAO"/><%-- BoardVO vo=new BoardVO(); --%>
<%
 	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="vo" class="com.sist.dao.BoardVO">
  <jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<%
	dao.boardInsert(vo);
	response.sendRedirect("list.jsp");
%>
<%--
 	//1. 사용자가 보내준 데이터를 받는다
 	//1-1. 한글 변환
 	request.setCharacterEncoding("UTF-8");
	// <jsp:setProperty name="vo" property="*"/>
 	String name=request.getParameter("name");
 	String subject=request.getParameter("subject");
 	String content=request.getParameter("content");
 	String pwd=request.getParameter("pwd");
 	
 	//1-2. VO에 값을 채운다
 	BoardVO vo=new BoardVO();
 	vo.setName(name);
 	vo.setSubject(subject);
 	vo.setContent(content);
 	vo.setPwd(pwd);
 	//1-3.오라클로 전송한다
 	BoardDAO dao=new BoardDAO();
 	dao.boardInsert(vo);
 	//1-4. 화면을 이동한다
 	response.sendRedirect("list.jsp");
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