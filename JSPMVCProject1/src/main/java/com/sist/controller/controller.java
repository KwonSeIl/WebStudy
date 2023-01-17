package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.model.*;
/*
 * 	MVC
 *    M(Model): DAO, VO, Service, Manager => 사용자 요청을 받아서 처리하고 결과값을 넘겨주는 역할(일반 자바로 만들어져 있음)
 *    V(View): JSP로 만듦 => 전송받은 데이터를 출력만 하는 기능
 *    C(Controller): 요청을 받고 Model을 이용해 처리 -> 처리 결과를 View에게 전송
 *    *** 요청 받기: 브라우저에서 (JSP / Servlet)으로 만들지 결정
 *    						|		|
 *    					화면 출력	 기능 처리(보안 뛰어남) => 스프링은 이미 제작(Serlet)
 *    
 *    실행 순서
 *      JSP
 *        요청하는 부분				====> Controller가 받음	====> Model의 메소드 찾음
 *        												<====
 *        												 요청 -> 결과값
 *        									JSP로 전송
 *        <a href="">
 *        <form action="">
 *        ajax
 */
@WebServlet("/controller")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// /Controller?cmd=list
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
	// doGet() / doPost() => 통합: service()
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd=request.getParameter("cmd");
		if(cmd.equals("list"))
		{
			ListModel model=new ListModel();
			model.execute(request);
		}
		else if(cmd.equals("update"))
		{
			UpdateModel model=new UpdateModel();
			model.execute(request);
		}
		else if(cmd.equals("delete"))
		{
			DeleteModel model=new DeleteModel();
			model.execute(request);
		}
		else if(cmd.equals("insert"))
		{
			InsertModel model=new InsertModel();
			model.execute(request);
		}
		
		// 해당 JSP로 값 전송(request를 보냄)
		RequestDispatcher rd=request.getRequestDispatcher("view/"+cmd+".jsp");
		rd.forward(request, response);
	}

}
