package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.*;
import com.sist.dao.*;
// Model => 여기서 요청 처리 메소드를 찾는다
/*
 * food/food_location.do ==> DispatcherServlet
 * 							==> service() 호출 => @RequestMapping() => 밑에 있는 메소드를 호출한다
 * 																		method.invoke()
 * 																			|
 * 																		Model에 존재 <====> DAO
 * JSP ===> Model ===> DAO
 * 	   <===		  <===
 */
@Controller
public class FoodModel {
	@RequestMapping("food/food_location.do")
	public String food_location(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../food/food_location.jsp");
		return "../main/main.jsp";
	}
}
