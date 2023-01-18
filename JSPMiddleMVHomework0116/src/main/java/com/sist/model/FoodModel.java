package com.sist.model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;

public class FoodModel {
	public void FoodListData(HttpServletRequest request,HttpServletResponse response)
	{		
		FoodDAO dao=new FoodDAO();
		ArrayList<FoodVO> list=dao.FoodListData();
		request.setAttribute("list", list);
		
	}
}
