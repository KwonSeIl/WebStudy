package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class MainModel {
	// 모든 .do 에서 실행할 수 있게 만듦
	
	@RequestMapping("main/main.do")
	   public String main_page(HttpServletRequest request,HttpServletResponse response)
	   {
		   /*HttpSession session=request.getSession();
		   session.setAttribute("id", "hong");
		   session.setAttribute("admin", "y");*/
		   FoodDAO dao=new FoodDAO();
		   ArrayList<CategoryVO> list=dao.foodCategoryData();
		   request.setAttribute("list", list);//home.jsp
		   
		   CommonsModel.footerData(request);// Spring => AOP (인터셉트) 
		   // include할 파일명을 전송 
		   request.setAttribute("main_jsp","../main/home.jsp");//main.jsp
		   
		   // cookie 전송 
		   Cookie[] cookies=request.getCookies();
		   List<FoodVO> cList=new ArrayList<FoodVO>();
		   HttpSession session=request.getSession();
		   String id=(String)session.getAttribute("id");
		   if(cookies!=null)
		   {
			   if(id==null)
			   {
				   for(int i=cookies.length-1;i>=0;i--)
				   {
					   if(cookies[i].getName().startsWith("guest_food"))
					   {
						   String fno=cookies[i].getValue();
						   FoodVO vo=dao.food_detail(Integer.parseInt(fno));
						   cList.add(vo);
					   }
				   }
			   }
			   else
			   {
				   for(int i=cookies.length-1;i>=0;i--)
				   {
					   if(cookies[i].getName().startsWith(id+"_food"))
					   {
						   String fno=cookies[i].getValue();
						   FoodVO vo=dao.food_detail(Integer.parseInt(fno));
						   cList.add(vo);
					   }
				   }
			   }
		   }
		   request.setAttribute("cList", cList);
		   return "../main/main.jsp";
	   }
	}
