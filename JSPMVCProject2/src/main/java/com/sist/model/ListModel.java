package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
public class ListModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		ArrayList<String> list=new ArrayList<String>();
		list.add("ȫ�浿");
		list.add("��û��");
		list.add("�̼���");
		list.add("�ڹ���");
		list.add("������");
		request.setAttribute("list", list);
		return "view/list.jsp";
	}

}
