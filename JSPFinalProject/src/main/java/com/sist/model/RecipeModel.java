package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class RecipeModel {
	@RequestMapping("recipe/list.do")
	public String recipe_list(HttpServletRequest request,HttpServletResponse response)
	{
		// _ok일 때만 redirect
		// 사용자가 보내준 페이지를 받는다
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page); // 현재 페이지
		// 오라클 연결
		RecipeDAO dao=new RecipeDAO();
		// 데이터 읽기
		List<RecipeVO> list=dao.recipeListData(curpage);
		int count=dao.recipeRowCount();
		int totalpage=(int)(Math.ceil(count/20.0));
		
		// 지금의 경우 이전/다음 페이지 만들면 안됨 => 이유: 데이터 178000개. 20개씩 나눔 => 너무 많음
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1; //curpage=10 (9*10*10)+1
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		/*
		 * 	[1]	~~~~ [10]  ==========> curpage: 1~10페이지 => startPage:1유지 ednPage:10 유지
		 *  ---       ---
		 *  statrPage  endPage		   curpage: 11~20 => startPage:11 유지 endPage:20유지
		 */
		if(endPage>totalpage)
			endPage=totalpage;
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		// request에 담아서 JSP로 전송
		request.setAttribute("main_jsp", "../recipe/list.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}

}
