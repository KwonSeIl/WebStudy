package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private ArrayList<String> list=new ArrayList<String>();
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
      //1. ����� ������ URI�� �޴´�
      String uri=request.getRequestURI();
      System.out.println(uri);
      uri=uri.substring(request.getContextPath().length()+1);
      System.out.println(uri);
      //2. ��û ó���� �´� �޼ҵ带 ã�´�
      for(String cls:list)
      {
    	  try
    	  {
    		  //Ŭ���� �޸� �Ҵ�
    		  Class clsName=Class.forName(cls);
    		  Object obj=clsName.getDeclaredConstructor().newInstance();//���÷���
    		  //Ŭ������ ������ ��� �޼ҵ� �о����
    		  Method[] methods=clsName.getDeclaredMethods();
    		  
    		  for(Method m:methods)
    		  {
    			  //Method ���� Annotation�� �д´�
    			  RequestMapping rm=m.getAnnotation(RequestMapping.class);
    			  if(rm.value().equals(uri))
    			  {
    				  String jsp=(String)m.invoke(obj, request,response);
    				  
    				  if(jsp.startsWith("redirect:"))
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
    	  }catch(Exception ex) {}
      }
      //3. �ش� JSP�� request�� �����Ѵ�
   }
   @Override
   public void init(ServletConfig config) throws ServletException{
      list.add("com.sist.model.FoodModel");
      list.add("com.sist.model.BoardModel");
      //XML => Ŭ���� ��� => �Ľ� (��θ� : ���� ��θ�)
   }
}

	
