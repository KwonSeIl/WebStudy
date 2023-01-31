package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
/*
 *   MVC
 *   ---
 *    Model: 사용자 요청을 처리해서 request에 결과값을 저장하는 역할
 *    		 사용자 요청값 출력, 데이터베이스 연동 역할
 *    Controller: (.do: 요청) 요청을 받는 역할
 *    			   Model 찾고 메소드 호출 => request
 *    			   request를 해당 JSP에 전송
 *    View: Controller가 보내준 request만 출력
 *    												 request			request	
 *    JSP(요청 => button,a,form,menu 태그 이용-> 화면 넘어감) =====> Controller =====> Model =====> DAO
 *    												 request			request,session
 *    	=> Controller에 요청(.do)
 *    	=> 게시판 목록: freeboard/list.do
 *    	=> 메인페이지: main/main.do
 *    기억해야 할 것
 *    ==> Controller가 Model의 종류를 저장
 */
import com.sist.controller.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class FreeBoardModel {
	// 메소드
	@RequestMapping("freeboard/list.do")
	public String freeboard_list(HttpServletRequest request,HttpServletResponse response)
	{
		/*
		 *   list.do?
		 *   --------
		 *   DispatcherServlet (Controller) => 스프링
		 *   main.do?
		 *   --------
		 *   DispatcherServlet
		 */
		// 1. 사용자 요청값 받기
		String page=request.getParameter("page"); //사용자가 요청
		if(page==null)
			page="1";
		
		// 현재페이지 지정 => 사용자가 보는 페이지 지정
		int curpage=Integer.parseInt(page);
		// 2. 데이터베이스 연동
		FreeBoardDAO dao=new FreeBoardDAO();
		List<FreeBoardVO> list=dao.boardListData(curpage);
		int totalpage=dao.boardTotalPage();	
		// 3. 결과값을 request에 담아준다
		////////////////////////////
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		//////////////////////////// => JSP 출력을 위해 전송하는 데이터 
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("freeboard/insert.do") //조건문(URL=> freeboard/insert.do라면)
	public String freeboard_insert(HttpServletRequest request,HttpServletResponse response)
	{
		// 중간에 들어가는 파일을 지정
		request.setAttribute("main_jsp", "../freeboard/insert.jsp"); //화면 출력
		return "../main/main.jsp"; // request => main.jsp.forward(request)
	}
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		// 사용자가 보내준 데이터를 받는다
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd"); //<input type=password name=pwd>
		// FreeBoardVO에 묶어서 오라클로 전송
		FreeBoardVO vo=new FreeBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		FreeBoardDAO dao=new FreeBoardDAO();
		dao.boardInsert(vo);
		
		
		// 화면 이동 ==> list.do
		return "redirect:list.do"; //sendRedirect("../freeboard/list.do") => 기존에 있는 파일 실행(재실행)
		/*
		 * 	insert
		 *  update
		 *  delete
		 */
	}
	@RequestMapping("freeboard/detail.do")
	public String freeboard_detail(HttpServletRequest request,HttpServletResponse response)
	{
		// 출력에 필요한 데이터 전송
		// 사용자가 요청한 데이터를 받아서 처리 => 게시물 변호
		String no=request.getParameter("no"); // 상세보기: 1개만 출력 ==> primary key
		// DAO로 전송 -> 오라클에서 데이터 읽기 
		FreeBoardDAO dao=new FreeBoardDAO();
		FreeBoardVO vo=dao.boardDetailData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		
		request.setAttribute("main_jsp", "../freeboard/detail.jsp"); //클릭시마다 데이터가 틀린 경우
		CommonsModel.footerData(request);
		// 댓글 보내기
		List<BoardReplyVO> list=dao.replyListData(Integer.parseInt(no));
		request.setAttribute("list", list);
		request.setAttribute("count", list.size());
		return "../main/main.jsp";
	}
	@RequestMapping("freeboard/update.do")
	public String freeboard_update(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		FreeBoardDAO dao=new FreeBoardDAO();
		FreeBoardVO vo=dao.boardUpdateData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp","../freeboard/update.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("freeboard/update_ok.do")
	public String freeboard_update_ok(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		FreeBoardVO vo=new FreeBoardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		//데이터베이스 연동
		FreeBoardDAO dao=new FreeBoardDAO();
		boolean bCheck=dao.boardUpdate(vo);
		if(bCheck==true)
		{
			request.setAttribute("res", "yes");
		}
		else
		{
			request.setAttribute("res", "no");
		}
		return "../freeboard/update_ok.jsp"; //_ok나오면 같이 쓸 수 있음
	}
	@RequestMapping("freeboard/delete.do")
	public String freeboard_delete(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		FreeBoardDAO dao=new FreeBoardDAO();
		boolean bCheck=dao.boardDelete(Integer.parseInt(no),pwd);
		if(bCheck==true)
		{
			request.setAttribute("res", "yes");
		}
		else
		{
			request.setAttribute("res", "no");
		}
		return "../freeboard/update_ok.jsp";
	}
	/*
	 * 	RNO       NOT NULL NUMBER    => 자동증가   
		BNO                NUMBER    => 전송 받음
		---------------------------------------  
		ID                 VARCHAR2(20) 
		NAME      NOT NULL VARCHAR2(34) 
		--------------------------------------- session에 존재
		MSG       NOT NULL CLOB      => 전송 받음
		REGDATE            DATE      => SYSDATE  
		GROUP_ID  NOT NULL NUMBER    => SubQuery 지정
		--------------------------------------  
		GROUP_STEP          NUMBER      
		GROUP_TAB          NUMBER      
		ROOT               NUMBER      
		DEPTH              NUMBER 
		-------------------------------------- => 0
	 */
	@RequestMapping("freeboard/reply_insert.do")
	public String reply_insert(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String bno=request.getParameter("bno");
		String msg=request.getParameter("msg");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		BoardReplyVO vo=new BoardReplyVO();
		vo.setBno(Integer.parseInt(bno));
		vo.setMsg(msg);
		vo.setId(id);
		vo.setName(name);
		FreeBoardDAO dao=new FreeBoardDAO();
		// insert 요청
		dao.replyInsert(vo);
		return "redirect:detail.do?no="+bno;
	}
	@RequestMapping("freeboard/reply_update.do")
	public String reply_update(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String bno=request.getParameter("bno");
		String rno=request.getParameter("rno");
		String msg=request.getParameter("msg");
		// DAO 연결
		FreeBoardDAO dao=new FreeBoardDAO();
		dao.replyUpdate(Integer.parseInt(rno), msg);
		return "redirect:detail.do?no="+bno;
	}
	/*
	 * 	RNO       NOT NULL NUMBER    => 자동증가   
		BNO                NUMBER    => 전송 받음
		---------------------------------------  
		ID                 VARCHAR2(20) 
		NAME      NOT NULL VARCHAR2(34) 
		--------------------------------------- session에 존재
		MSG       NOT NULL CLOB      => 전송 받음
		REGDATE            DATE      => SYSDATE  
		GROUP_ID  NOT NULL NUMBER    => SubQuery 지정
		--------------------------------------  
		GROUP_STEP          NUMBER      
		GROUP_TAB          NUMBER      
		ROOT               NUMBER      
		DEPTH              NUMBER 
		-------------------------------------- => 0
	 */
	
	
	@RequestMapping("freeboard/reply_reply_insert.do")
	public String reply_reply_insert(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String bno=request.getParameter("bno");
		String pno=request.getParameter("pno"); //댓글번호X => 상위 번호
		String msg=request.getParameter("msg");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		
		BoardReplyVO vo=new BoardReplyVO();
		vo.setBno(Integer.parseInt(bno));
		vo.setId(id);
		vo.setName(name);
		vo.setMsg(msg);
		FreeBoardDAO dao=new FreeBoardDAO();
		//답변 메소드 호출 
		dao.replyReplyInsert(Integer.parseInt(pno), vo);
		return "redirect:detail.do?no="+bno;
	}
	@RequestMapping("freeboard/reply_delete.do")
	public String reply_delete(HttpServletRequest request,HttpServletResponse response)
	{
		String rno=request.getParameter("rno");
		String bno=request.getParameter("bno");
		FreeBoardDAO dao=new FreeBoardDAO();
		//삭제 처리
		dao.replyDelete(Integer.parseInt(rno));
		return "redirect:detail.do?no="+bno;
	}
}
