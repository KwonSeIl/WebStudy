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
		try
		{
			//한글 변환
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String ss=request.getParameter("ss");
		if(ss==null)
			ss="역삼";
		
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		
		// 현재 페이지 지정
		int curpage=Integer.parseInt(page);
		// DAO에서 해당 페이지의 데이터 읽기
		FoodDAO dao=new FoodDAO();
		ArrayList<FoodVO> list=dao.foodLocationFindData(curpage, ss);
		int totalpage=dao.foodLocationTotalPage(ss);
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		 request.setAttribute("ss", ss);
		//20개씩 나눠놨기 때문에 BLOCK줄 필요 없음
		request.setAttribute("main_jsp", "../food/food_location.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("food/food_list.do")
	   public String food_list(HttpServletRequest request,HttpServletResponse response)
	   {
	      //..food/food_list.do?cno=10
	      //1. 요청값을 받는다
	      String cno=request.getParameter("cno");
	      //데이터베이스에서 값을 읽어온다
	      FoodDAO dao=new FoodDAO();
	      //결과값을 얻어서 => request에 담아준다 => rd.forward(request,response) => rd(jsp)
	      ArrayList<FoodVO> list=dao.foodListData(Integer.parseInt(cno));
	      request.setAttribute("list", list);
	      CategoryVO vo=dao.categoryInfoData(Integer.parseInt(cno));
	      request.setAttribute("vo", vo);
	      //include 하는 파일 전송
	      request.setAttribute("main_jsp", "../food/food_list.jsp");
	      CommonsModel.footerData(request);
	      return "../main/main.jsp";
	   }
	   
	   @RequestMapping("food/food_detail.do")
	   public String food_detail(HttpServletRequest request,HttpServletResponse response)
	   {
		   // ../food/food_detail.do?fno=1
		   String fno=request.getParameter("fno");
		   // 데이터베이스 연결
		   FoodDAO dao=new FoodDAO();
		      FoodVO vo=dao.food_detail(Integer.parseInt(fno));
		      String address=vo.getAddress();
		      String addr1=address.substring(0,address.lastIndexOf("지"));
		      addr1=addr1.trim();
		      String addr2=address.substring(address.lastIndexOf("지")+3);
		      request.setAttribute("vo", vo);
		      request.setAttribute("addr1", addr1);
		      request.setAttribute("addr2", addr2);

		   
		   request.setAttribute("vo", vo);
		   // => 레시피, 재료, Goods, 인근 명소
		   //화면 출력
		  request.setAttribute("main_jsp", "../food/food_detail.jsp");
		  CommonsModel.footerData(request);
	      return "../main/main.jsp";
	   }
	   
	}