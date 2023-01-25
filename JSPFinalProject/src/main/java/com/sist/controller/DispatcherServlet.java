package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.*;
import com.sist.model.*;
import java.net.*;



@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> clsList=new ArrayList<String>(); //application.xml에 등록된 클래스를 모아준다 => Model 클래스 인식
	//초기화 => 사용자에게 서비스(JSP로 request => 화면 브라우저에 출력)
	//Model 클래스를 가지고 있어야 한다
	
	public void init(ServletConfig config) throws ServletException {
		//Model 클래스를 읽어 온다 => 일반 Window 형식=> 경로1)git 2)AWS
		try
		{
			//1.xml 파일을 가지고 온다
			//1-1. XML의 파일 읽기 => 호환성 => 실제 톰캣이 인식하는 폴더에서 읽기
			URL url=this.getClass().getClassLoader().getResource("."); //클래스를 인식한다
			File file=new File(url.toURI());
			System.out.println(file.getPath());
			//C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPFinalProject\WEB-INF\classes
			String path=file.getPath();
			path=path.replace("\\", File.separator);
			path=path.substring(0,path.lastIndexOf(File.separator)); //separator => 구분자 window: \\
			path=path+File.separator+"application.xml";
			//2. 클래스명을 읽는다
			//2-1. XML 파싱 => 파싱: xml 파일 안의 bean class값을 읽어온다
			//2-2.XML 파싱: 파싱 클래스 생성
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			// html, xml, wml, hdml.. Factory 패턴(DriverManager) => Spring(클래스 관리자)
			//2-3. 파싱기를 가지고 온다
			DocumentBuilder db=dbf.newDocumentBuilder();
			//2-4. XML을 파싱 => 메모리에 트리 형태로 저장
			Document doc=db.parse(new File(path));
			//System.out.println(doc.toString());
			//2-5. 최상위 태그 읽기(테이블명) => <beans>
			Element root=doc.getDocumentElement();
			//System.out.println(root.getTagName());
			//2-6. 같은 태그를 묶어서 데이터를 읽기 시작
			NodeList node=root.getElementsByTagName("bean");
			//System.out.println(node.toString());
			
			//3. ArrayList에 저장
			for(int i=0;i<node.getLength();i++)
			{
				Element bean=(Element)node.item(i);
				String cls=bean.getAttribute("class");
				System.out.println(cls);
				clsList.add(cls);
			}
		}catch(Exception ex) {}
	}
	
	//브라우저에 필요한 데이터를 전송 위치
	// Model이 가지고 있는 메소드 호출 -> 요청 처리 -> request를 해당 JSP로 전송
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 사용자 요청 -> 등록된 모델의 해당 메소드 찾음 -> 호출 -> request, session -> JSP로 전송
		try
		{
			//1. 사용자가 요청한 주소 확인 => ?뒤에는 자동으로 request
			String uri=request.getRequestURI();
			System.out.println(uri);
			// /JSPFinalProject/main/main.do
			//				   ------------- 구분자
			uri=uri.substring(request.getContextPath().length()+1);
			System.out.println("uri:"+uri);
			// uri에 해당되는 메소드 찾아서 호출 => 결과값을 jsp로 전송
			for(String cls:clsList) //등록된 클래스 안에서 해당 요청의 메소드를 호출
			{
				Class clsName=Class.forName(cls);
				//클래스 위에 @Controller가 없는 경우에는 제외 => 스프링
				if(clsName.isAnnotationPresent(Controller.class)==false)
					continue;
				// 1) 메모리 할당(클래스)
				Object obj=clsName.getDeclaredConstructor().newInstance();
				// 2) 메소드 찾아서 요청 처리
				Method[] methods=clsName.getDeclaredMethods();
				for(Method m:methods)
				{
					//어노테이션을 이용해서 메소드를 찾는다
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					if(rm.value().equals(uri))
					{
						//찾은 경우 => 메소드를 호출해라
						String jsp=(String)m.invoke(obj, request,response); //메소드명은 상관없이 어노테이션이 일치하는 메소드는 호출 가능
						//request => JSP로 전송
						//sendRedirect() => request를 초기화
						//request 전송 => forward
						if(jsp.startsWith("redirect:")) // _ok.jsp => Controller 직접 처리
						{
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
						}
						else
						{
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						
						return;
					}
				}
			}
		}catch(Exception ex) {}
	}

}
