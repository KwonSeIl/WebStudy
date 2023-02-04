package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.NoticeDAO;
import com.sist.vo.NoticeVO;

@Controller
public class AdminModel {
	private String[] prefix= {"","일반공지","이벤트공지","맛집공지","여행공지","상품공지"};
	@RequestMapping("adminpage/admin_main.do")
	public String admin_main(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("admin_jsp", "../adminpage/admin_home.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("adminpage/notice_list.do")
	public String admin_notice_list(HttpServletRequest request,HttpServletResponse response)
	{
		//사용자가 보내준 데이터 받기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		NoticeDAO dao=new NoticeDAO();
				List<NoticeVO> list=dao.noticeListData(curpage);
				
		for(NoticeVO vo:list)
		{
			vo.setPrefix("["+prefix[vo.getType()]+"]");
		}
				
				int totalpage=dao.noticeTotalPage();
				
				request.setAttribute("list", list);
				request.setAttribute("curpage", curpage);
				request.setAttribute("totalpage", totalpage);
		request.setAttribute("admin_jsp", "../adminpage/notice_list.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("adminpage/notice_insert.do")
	public String admin_notice_insert(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("admin_jsp", "../adminpage/notice_insert.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("adminpage/notice_insert_ok.do")
	// 스프링에서 => (NoticeVO vo)
	public String admin_notice_insert_ok(HttpServletRequest request,HttpServletResponse response)
	{
		// 사용자가 전송한 데이터 받기
		try 
		{
			request.setCharacterEncoding("UTF-8");	
		} catch (Exception ex) {}
		String type=request.getParameter("type");
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		NoticeVO vo=new NoticeVO();
		vo.setType(Integer.parseInt(type));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		
		//dao연결 => 오라클로 전송
		NoticeDAO dao=new NoticeDAO();
		dao.noticeInsert(vo);
		return "redirect:notice_list.do";
	}
	@RequestMapping("adminpage/notice_delete.do")
	public String admin_notice_delete(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		//dao연동
		NoticeDAO dao=new NoticeDAO();
		dao.noticeDelete(Integer.parseInt(no));
		return "redirect:notice_list.do";
	}
	@RequestMapping("adminpage/notice_update.do")
	public String admin_notice_update(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		NoticeDAO dao=new NoticeDAO();
		NoticeVO vo=dao.noticeUpdateData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		//include => request를 공유한다
		request.setAttribute("admin_jsp", "../adminpage/notice_update.jsp"); //admin_jsp => main_jsp 안에 들어감
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp"); //main_jsp => main.jsp 안에 들ㅇ어감
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("adminpage/notice_update_ok.do")
	public String admin_notice_update_ok(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String type=request.getParameter("type");
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String no=request.getParameter("no");
		NoticeVO vo=new NoticeVO();
		vo.setType(Integer.parseInt(type));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setNo(Integer.parseInt(no));
		NoticeDAO dao=new NoticeDAO();
		//메소드 호출
		dao.noticeUpdate(vo);
		return "redirect:notice_list.do";
	}
}
