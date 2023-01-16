package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class FoodModel {
	public void categoryListData(HttpServletRequest request,HttpServletResponse response)
	{
		//DAO ����
		FoodDAO dao=new FoodDAO();
		ArrayList<CategoryVO> list=dao.categoryListData();
		//JSP�� ����
		request.setAttribute("list", list);
	}
	public void foodListData(HttpServletRequest request,HttpServletResponse response)
	{
		//JSP => <% %> -> �ڹ� �޼ҵ�� ����(JSP���� �ڹٸ� �ּ�ȭ)  
		String cno=request.getParameter("cno");
		FoodDAO dao=new FoodDAO();
		CategoryVO vo=dao.categoryInfoData(Integer.parseInt(cno));
		ArrayList<FoodVO> list=dao.foodListData(Integer.parseInt(cno));
		
		//�� ���� ���ÿ� JSP�� ����(food_list.jsp��)
		request.setAttribute("vo", vo);
		request.setAttribute("list", list);
		//request�� �����ؼ� ������ �����ʹ� ������ ������ ����(ȭ�� ��¿� ���õ� ������ -> ���� ��Ƽ� ����)
		
	}
	public void foodDetailData(HttpServletRequest request, HttpServletResponse response)
	   {
	      String fno=request.getParameter("fno");
	      FoodDAO dao=new FoodDAO();
	      FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
	       request.setAttribute("vo", vo);
	   }

}
