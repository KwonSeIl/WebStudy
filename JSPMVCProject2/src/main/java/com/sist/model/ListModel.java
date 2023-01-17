package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
public class ListModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		ArrayList<String> list=new ArrayList<String>();
		list.add("홍길동");
		list.add("심청이");
		list.add("이순신");
		list.add("박문수");
		list.add("강감찬");
		request.setAttribute("list", list);
		return "view/list.jsp";
	}

}
