<%@page import="com.sist.vo.DataBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.io.*"%>
<%--
	요청 처리(화면 UI, 요청 처리만 하는 JSP) _ok
	1. 사용자가 보내준 데이터 : no, pwd
	2. DAO를 연결해서 오라클과 연동
	3. 화면 UI OR 처리를 하고 이동
	------------------------------ model (Java)
--%>
<%
	String no=request.getParameter("no");
	String pwd=request.getParameter("pwd");
	// DAO처리
	DataBoardDAO dao=new DataBoardDAO();
	DataBoardVO vo=dao.databoardInfoData(Integer.parseInt(no));
	// 파일 삭제 
	boolean bCheck=dao.databoardDelete(Integer.parseInt(no), pwd);
	if(bCheck==true)
	{
		// 파일 삭제
		if(vo.getFilesize()>0) //파일이 존재하면 파일 삭제
		{
			try
			{
				File file=new File("c:\\download\\"+vo.getFilename());
				file.delete();
			}catch(Exception ex){}
		}
		// 이동
		response.sendRedirect("../main/main.jsp?mode=5"); //자료실 목록으로 이동
	}
	else
	{
%>
		<script>
		  alert("비밀번호가 틀립니다!!");
		  history.back();
		</script>
<%		
	}
	// 이동 => 비밀번호O / 비밀번호X
%>