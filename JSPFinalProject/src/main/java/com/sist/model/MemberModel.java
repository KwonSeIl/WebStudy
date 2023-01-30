package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;
import com.sist.vo.ZipcodeVO;

import java.util.*;
@Controller
public class MemberModel {
	@RequestMapping("member/join.do")
	  public String member_join(HttpServletRequest request,HttpServletResponse response)
	  {
		  request.setAttribute("main_jsp", "../member/join.jsp");
		  CommonsModel.footerData(request);
		  return "../main/main.jsp";
	  }
	// => javascript => Ajax, Vue, React는 단독수행으로 띄워야 한다
	@RequestMapping("member/idcheck.do")
	public String member_idCheck(HttpServletRequest request,HttpServletResponse response)
	{
		return "../member/idcheck.jsp";
	}
	@RequestMapping("member/idcheck_result.do")
	public String member_idcheck_result(HttpServletRequest request,HttpServletResponse response)
	{
		String id=request.getParameter("id");
		MemberDAO dao=new MemberDAO();
		int count=dao.memberIdCheck(id);
		request.setAttribute("count", count);
		return "../member/idcheck_result.jsp";
	}
	@RequestMapping("member/postfind.do")
	public String member_postfind(HttpServletRequest request,HttpServletResponse response)
	{
		return "../member/postfind.jsp";
	}
	@RequestMapping("member/postfind_result.do")
	public String member_postfind_result(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		//data:{"dong":dong} => ?dong=역삼
		String dong=request.getParameter("dong");
		//dao연동
		MemberDAO dao=new MemberDAO();
		int count=dao.memberPostCount(dong);
		List<ZipcodeVO> list=dao.memberPostFind(dong);
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		return "../member/postfind_result.jsp";
	}
	@RequestMapping("member/email_check.do")
	public String member_email_check(HttpServletRequest request,HttpServletResponse response)
	{
		String email=request.getParameter("email");
		MemberDAO dao=new MemberDAO();
		int count=dao.memberEmailCheck(email);
		request.setAttribute("count", count);
		return "../member/email_check.jsp";
	}
	@RequestMapping("member/tel_check.do")
	public String member_tel_check(HttpServletRequest request,HttpServletResponse response)
	{
		String phone=request.getParameter("phone");
		MemberDAO dao=new MemberDAO();
		int count=dao.memberPhoneCheck(phone);
		request.setAttribute("count", count);
		return "../member/tel_check.jsp";
	}
	@RequestMapping("member/join_ok.do")
	public String member_insert(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String post=request.getParameter("post");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String email=request.getParameter("email");
		String tel1=request.getParameter("tel1");
		String tel2=request.getParameter("tel2");
		String content=request.getParameter("content");
		
		MemberVO vo=new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setSex(sex);
		vo.setBirthday(birthday);
		vo.setPost(post);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setEmail(email);
		vo.setContent(content);
		vo.setPhone(tel1+"-"+tel2);
		
		MemberDAO dao=new MemberDAO();
		dao.memberInsert(vo);
		
		return "redirect:../main/main.do";
	}
	@RequestMapping("member/login.do")
	public String member_login(HttpServletRequest request,HttpServletResponse response)
	{
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		MemberDAO dao=new MemberDAO();
		//결과값 받기
		MemberVO vo=dao.memberLogin(id, pwd);
		if(vo.getMsg().equals("OK"))//로그인 되었다면
		{
			//session에 저장 => 모든 jsp로 사용이 가능하게 만든다(전역변수) => 지속적인 유지 => id,name,admin
			//session생성
			HttpSession session=request.getSession();
			//session,cookie => request를 이용해서 생성한다
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			session.setAttribute("admin", vo.getAdmin());
		}
		request.setAttribute("result", vo.getMsg());
		return "../member/login.jsp";
	}
	@RequestMapping("member/logout.do")
	public String member_logout(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		session.invalidate(); //모든 정보 해제
		return "redirect:../main/main.do";
	}
}