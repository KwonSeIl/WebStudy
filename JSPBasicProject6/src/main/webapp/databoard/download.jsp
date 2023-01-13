<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*"%>
<%
	//업로드 (라이브러리 존재), 다운로드는 직접 제작 => out 사용처 => 복구: pageContext
	String fn=request.getParameter("fn");
	String path="c:\\download";
	try
	{
		//1. 파일정보
		File file=new File(path+"\\"+fn);
		//response.setContentLengthLong(file.length()); //먼저 보내준다(파일 다운로드창을 보여준다)
		// 최근 => 사용하지 않는다
		response.setHeader("Content-Disposition", "attachment;filename="
								+URLEncoder.encode(fn,"UTF-8"));
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file)); //서버에서 파일 읽기
		BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream()); //클라이언트 => 파일 복사
		int i=0;
		byte[] buffer=new byte[1024];
		// 한 번 전송 => 1024byte씩 전송
		while((i=bis.read(buffer, 0, 1024))!=-1) //-1은 EOF(파일 끝)
		{
			// i = 읽은 바이트 수
			bos.write(buffer, 0, i);
		}
		out.clear();
		out=pageContext.pushBody(); //out (파일을 복사X, HTML을 작성)
		bis.close();
		bos.close();
	}catch(Exception ex){}
%>