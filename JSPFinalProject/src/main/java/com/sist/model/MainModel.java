package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_page(HttpServletRequest request,HttpServletResponse response)
	{
		/*HttpSession session=request.getSession();
		session.setAttribute("id", "hong");
		session.setAttribute("admin", "y");*/
		FoodDAO dao=new FoodDAO();
		ArrayList<CategoryVO> list=dao.foodCategoryData();
		request.setAttribute("list", list);
		//include할 파일명을 전송
		request.setAttribute("main_jsp", "../main/home.jsp"); //main.jsp
		return "../main/main.jsp";
	}
}
