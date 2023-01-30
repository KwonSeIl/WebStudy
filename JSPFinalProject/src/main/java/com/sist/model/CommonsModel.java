package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.NoticeDAO;
import com.sist.vo.NoticeVO;

public class CommonsModel {
	public static void footerData(HttpServletRequest request)
	{
		//Footer에 출력
		NoticeDAO ndao=new NoticeDAO();
		List<NoticeVO> nList=ndao.noticeTop5();
		request.setAttribute("nList", nList);
	}
}
