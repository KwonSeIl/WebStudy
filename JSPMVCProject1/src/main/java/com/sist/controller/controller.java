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
 *    M(Model): DAO, VO, Service, Manager => ����� ��û�� �޾Ƽ� ó���ϰ� ������� �Ѱ��ִ� ����(�Ϲ� �ڹٷ� ������� ����)
 *    V(View): JSP�� ���� => ���۹��� �����͸� ��¸� �ϴ� ���
 *    C(Controller): ��û�� �ް� Model�� �̿��� ó�� -> ó�� ����� View���� ����
 *    *** ��û �ޱ�: ���������� (JSP / Servlet)���� ������ ����
 *    						|		|
 *    					ȭ�� ���	 ��� ó��(���� �پ) => �������� �̹� ����(Serlet)
 *    
 *    ���� ����
 *      JSP
 *        ��û�ϴ� �κ�				====> Controller�� ����	====> Model�� �޼ҵ� ã��
 *        												<====
 *        												 ��û -> �����
 *        									JSP�� ����
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
	// doGet() / doPost() => ����: service()
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
		
		// �ش� JSP�� �� ����(request�� ����)
		RequestDispatcher rd=request.getRequestDispatcher("view/"+cmd+".jsp");
		rd.forward(request, response);
	}

}
