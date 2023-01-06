package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 	MVC ==> Controller(Servlet) ==> Spring
 * 			-------------------
 * 			 ��û		==========> ��û ó��(Model): Java
 * 			 ����		==========> ȭ�� ���(View): JSP
 * 			 -----------------
 * 				Servlet
 * 
 */
@WebServlet("/hello.do") // a.do
// Ŭ���̾�Ʈ(������) <=======> ����
//					�ּ�â
// ����ڰ� URL �ּ� �������� /HelloServlet �ۼ� ==> ��Ĺ�� HelloServlet�� ȣ��
/*
 * 	Ŭ���̾�Ʈ���� � ���� ��������.. (Front-End)
 *  ��û���� �޾Ƽ� � ȭ���� �������� ��������..(Back-End)
 *  C/S ==> �ְ� �ޱ�
 *  		-------
 *  ��)
 *  	�α��� => id, pwd
 *  	ȭ�� => main
 *  
 *  	��� => �󼼺���
 *  	--
 *  	 Client => ��� ��ȣ ����
 *  	 Server => ��ȣ�� �ش�Ǵ� ��� �����͸� �о ȭ�� ����
 *  
 *  	�Խ��� ==> �۾���
 *  	 Client => �۾� ������ ����
 *  	 Server => ����Ŭ�� ���� -> ȭ�� ������� �̵�
 *  	---------------------------------------------- �帧(Ŭ�� => URL Ȯ�� => ȭ��)
 *  
 *  ���� ȣ��
 *  1) http://localhost/JSPBasicProject3/HelloServlet ���������� ��û
 *  	---------------------------------------------
 *  2) �������� ==> WAS ���� ���� Ŭ������ ã�´�(HelloServlet)
 *  			 --- Web Application Server (��Ĺ, ����) ==> WAS, �������(��), Container(Spring), Component(�ڹ� Ŭ����)
 *  3) int()�޼ҵ带 ȣ��
 *  4) service() �޼ҵ带 ȣ�� ==> HTML�� �������� ����
 *  	 |
 *  	doGet(), doPost()
 *  5) ������, ȭ�� �̵� ==> destroy()
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ==> ��Ĺ�� ���� ȣ��Ǵ� Callback
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//��� ������ �ʱ�ȭ, �ڵ� �α��� �� ���� �б�(Cookie) ��� ==> ������ ����
		System.out.println("���� �ʱ�ȭ �޼ҵ� ȣ��...");
	}

	
	public void destroy() {
		// TODO Auto-generated method stub
		// ������ ����, ���� �̵�, ���ΰ�ħ => �ڵ� �޸� ����
	}
	/*
	 * 	HTML
	 * 	----
	 * 	  |
	 * 	  �����͸� ���� ==> ������ �޾Ƽ� ó��
	 * 	  				------------- �ڹٷ� �޾Ƽ� ó��
	 * 			|
	 * 		GET / POST / PUT / DELETE	===> �ٸ� ���α׷��� �����ؼ� ó��(RestFul)
	 * 		----------    |       |
	 * 			|		Update 	Delete
	 * 		  -----
	 * 			GET: ������ ���� ��� ==> http://localhost/main/main.jsp?no=10; ==> URL �ڿ� �����͸� ��� ����
	 * 													  -----------------
	 * 														main.jsp�� no���� ���� => �޼ҵ尡 ���� ������ �Ű������� �̿��� �� ����
	 * 				==> �ܼ��� �˻���, ������ ��ȣ ����...(���Ȱ� ������ ���� ������)
	 * 			POST: no=10 ==> ���缭 ������(URL �ڿ� �����Ͱ� �Ⱥ��δ�)
	 * 				==> ����(ID, Password, �����Ͱ� ���� ���(ȸ������,�۾���,��ǰ�Է�..)
	 * 
	 * 			GET ==> doGet()
	 * 			POST ==> doPost()
	 * 					--------------- service(), JSP�� ������ ���� => ��ü ���ǹ����� (GET, POST)
	 */
	// ������ ȭ���� ����ϴ� �޼ҵ�
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. GET / POST�� �������� �ʴ� ��쿡�� GET�� ȣ��ȴ�(default)
		/*
		 * 	������ ���� (GET/POST)
		 *  -------
		 *  HTML
		 *  	<a>	=> GET�� ��밡�� ==> ������ ����( ?����=��)
		 *  	***<form>
		 *  JavaScript
		 *  	location.href=""
		 *  	***Ajax
		 *  		type=POST,GET...
		 *  	***VueJS
		 *  		axios.get(), axios.post()
		 *  	***ReactJS
		 *  		axios.get(), axios.post()
		 *  Java
		 *  	sendRedirect()
		 */
		// 1. �������� ������ ������ ���� ==> text/html, text/xml, text/plain
		//								HTML		XML			JSON
		response.setContentType("text/html;charset=UTF-8"); //�ѱ�����
		// 2. � �������� �������� ����
		PrintWriter out=response.getWriter();
		//				��û�� �������� ����(ip)
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlet</h1>");
		out.println("</body>");
		out.println("</html>");
	}
	// ������ ȭ���� ����ϴ� �޼ҵ�
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
