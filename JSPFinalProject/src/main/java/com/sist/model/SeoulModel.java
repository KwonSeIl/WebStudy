package com.sist.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.AllReplyDAO;
import com.sist.dao.SeoulDAO;
import com.sist.vo.AllReplyVO;
import com.sist.vo.FoodVO;
import com.sist.vo.SeoulVO;

@Controller
public class SeoulModel {
	@RequestMapping("seoul/seoul_list.do")
	public String seoul_list(HttpServletRequest request,HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		String type=request.getParameter("type");
		
		SeoulDAO dao=new SeoulDAO();
		ArrayList<SeoulVO> list=dao.seoulListData(Integer.parseInt(page), Integer.parseInt(type));
		if(type.equals("5"))
		{
			for(SeoulVO vo:list)
			{
				vo.setPoster("https://korean.visitseoul.net"+vo.getPoster());
			}
		}
		int totalpage=dao.seoulTotalPage(Integer.parseInt(type));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("type", type);
		request.setAttribute("list", list);
		String[] title= {"","서울 명소","서울 자연 & 관광","서울 쇼핑","서울 호텔","서울 게스트하우스"};
		request.setAttribute("title", title[Integer.parseInt(type)]);
		
		
		request.setAttribute("main_jsp", "../seoul/seoul_list.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("seoul/seoul_detail.do")
	public String seoul_detail(HttpServletRequest request,HttpServletResponse response)
	{
		// 요청값 
		String no=request.getParameter("no");
		//데이터베이스 연동
		SeoulDAO dao=new SeoulDAO();
		SeoulVO vo=dao.seoulDetailData(Integer.parseInt(no));
		//결과값 전송
		/*
		 * 	04084 서울 마포구 양화진길 46 (합정동, 양화진홍보관)
		 */
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../seoul/seoul_detail.jsp");
		/*String address=vo.getAddress();
		String addr1=address.substring(address.indexOf(" ")+1);
		addr1=addr1.trim();
		String addr2=addr1.substring(addr1.indexOf(" ")+1);
		addr2=addr2.trim();
		String addr3=addr2.substring(0,addr2.indexOf(" "));
		System.out.println(addr3);*/
		String address=vo.getAddress();
		String[] addr=address.split(" ");
		/*for(String s:addr)
		{
			System.out.println(s);
		}*/
		request.setAttribute("addr", addr[2]+" 맛집");
		List<FoodVO> list=dao.seoulFoodFindData(addr[2]);
		request.setAttribute("list", list);
		CommonsModel.footerData(request);
		
		AllReplyDAO adao=new AllReplyDAO();
		List<AllReplyVO> rList=adao.allReplyListData(Integer.parseInt(no), 1);
		request.setAttribute("rList", rList);
		request.setAttribute("count", rList.size());
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	@RequestMapping("seoul/seoul_weather.do")
	public String seoul_weather(HttpServletRequest request,HttpServletResponse response)
	{
		String html="";
		try
		{
			Document doc=Jsoup.connect("https://korean.visitseoul.net/weather").get();
			Element elem=doc.selectFirst("section#content");
			String data=elem.html();
			data=data.replace("src=\"", "src=\"https://korean.visitseoul.net/weather");
			System.out.println(data);
			html=data;
		}catch(Exception ex) {}
		request.setAttribute("html", html);
		request.setAttribute("main_jsp", "../seoul/seoul_weather.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
}
