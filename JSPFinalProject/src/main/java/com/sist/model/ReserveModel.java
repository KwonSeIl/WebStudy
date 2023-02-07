package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.ReserveDAO;
import java.util.*;
import com.sist.vo.*;
import java.text.*;
@Controller
public class ReserveModel {
  @RequestMapping("reserve/reserve_main.do")
  public String reserve_main(HttpServletRequest request,HttpServletResponse response)
  {
	  request.setAttribute("main_jsp", "../reserve/reserve_main.jsp");
	  CommonsModel.footerData(request);
	  return "../main/main.jsp";
  }
  
  @RequestMapping("reserve/food_list.do")
  public String reserve_food_list(HttpServletRequest request,HttpServletResponse response)
  {
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  String fd=request.getParameter("fd");
	  ReserveDAO dao=new ReserveDAO();
	  List<FoodVO> list=dao.reserveFoodListData(fd);
	  request.setAttribute("list", list);
	  return "../reserve/reserve_food.jsp";
  }
  @RequestMapping("reserve/reserve_date.do")
  public String reserve_date(HttpServletRequest request,HttpServletResponse response)
  {
	  Date date=new Date();
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
	  String today=sdf.format(date);
	  StringTokenizer st=new StringTokenizer(today,"-");
	  
	  String strYear=st.nextToken();
	  String strMonth=st.nextToken();
	  String strDay=st.nextToken();
	  
	  
	  String syear=request.getParameter("year");
	  String smonth=request.getParameter("month");
	  String fno=request.getParameter("fno");
	  
	  if(syear==null)
	  {
		  syear=strYear;
	  }
	  if(smonth==null)
	  {
		  smonth=strMonth;
	  }
	  
	  
	  int year=Integer.parseInt(syear);
	  int month=Integer.parseInt(smonth);
	  int day=Integer.parseInt(strDay);
	  
	  // 요일 
	  Calendar cal=Calendar.getInstance();
	  cal.set(Calendar.YEAR, year);
	  cal.set(Calendar.MONTH, month-1);
	  cal.set(Calendar.DATE, 1);
	  int week=cal.get(Calendar.DAY_OF_WEEK);
	  // 마지막 날 
	  int lastday=cal.getActualMaximum(Calendar.DATE);
	  
	  // 요일 
	  String[] strWeek={"일","월","화","수","목","금","토"};
	  
	  // 전송 
	  request.setAttribute("year", year);
	  request.setAttribute("month", month);
	  request.setAttribute("day", day);
	  request.setAttribute("week", week-1);
	  request.setAttribute("lastday", lastday);
	  request.setAttribute("strWeek", strWeek);
	  //request.setAttribute("year", year); => 예약 가능한 날 (food_location) => 예약일 => 시간 시간 => 인원
	  ReserveDAO dao=new ReserveDAO();
	  String rdate=dao.reserveDayData(Integer.parseInt(fno));
	  int[] rdays=new int[32];
	  String[] temp=rdate.split(",");
	  for(String ss:temp)
	  {
		  if(Integer.parseInt(ss)>=day)
		  {
		     rdays[Integer.parseInt(ss)]=1;
		  }
	  }
	  request.setAttribute("rdays", rdays);
	  return "../reserve/reserve_date.jsp";
  }
  @RequestMapping("reserve/reserve_time.do")
  public String reserve_time(HttpServletRequest request,HttpServletResponse response)
  {
	  String day=request.getParameter("day");
	  
	  List<String> reserve_time=new ArrayList<String>();
	  //DAO 연결 
	  ReserveDAO dao=new ReserveDAO();
	  String dd=dao.reserveTimeData(Integer.parseInt(day));
	  //1,2,3
	  StringTokenizer st=new StringTokenizer(dd,",");
	  while(st.hasMoreTokens())
	  {
		  String ss=dao.reserveTimeRealData(Integer.parseInt(st.nextToken()));
		  reserve_time.add(ss);
	  }
	  
	  request.setAttribute("rtime", reserve_time);
	  return "../reserve/reserve_time.jsp";
  }
  @RequestMapping("reserve/reserve_inwon.do")
  public String reserve_inwon(HttpServletRequest request,HttpServletResponse response)
  {
	  return "../reserve/reserve_inwon.jsp";
  }
  @RequestMapping("reserve/reserve_ok.do")
  public String reserve_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  /*
	   *         <input type=hidden name="fno" id="fno">
                 <input type=hidden name="reserveday" id="reserveday">
                 <input type=hidden name="reservetime" id="reservetime">
                 <input type=hidden name="reserveinwon" id="reserveinwon">
                 
                RNO        NOT NULL NUMBER       
				FNO                 NUMBER  O    
				ID                  VARCHAR2(20)  O
				RDATE      NOT NULL VARCHAR2(20)  O
				RTIME      NOT NULL VARCHAR2(20)  O
				INWON               NUMBER       
				RESERVE_NO NOT NULL VARCHAR2(20)  O
				OK                  CHAR(1)       'n'  
				REGDATE             DATE     SYSDATE
	   */ 
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  String fno=request.getParameter("fno");
	  String rdate=request.getParameter("reserveday");
	  String rtime=request.getParameter("reservetime");
	  String inwon=request.getParameter("reserveinwon");
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  
	  Date date=new Date();
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
	  String reserve_no=sdf.format(date)+fno;
	  
	  ReserveVO vo=new ReserveVO();
	  vo.setFno(Integer.parseInt(fno));
	  vo.setRdate(rdate);
	  vo.setRtime(rtime);
	  vo.setInwon(Integer.parseInt(inwon));
	  vo.setId(id);
	  vo.setReserve_no(reserve_no);
	  
	  //DAO연동 
	  ReserveDAO dao=new ReserveDAO();
	  dao.reserveOk(vo);
	  return "redirect:../mypage/reserve.do";
  }
  @RequestMapping("mypage/reserve.do")
  public String mypage_reserve(HttpServletRequest request,HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  ReserveDAO dao=new ReserveDAO();
	  List<ReserveVO> list=dao.reserveMyPageData(id);
	  request.setAttribute("list", list);
	  request.setAttribute("mypage_jsp", "../mypage/mypage_reserve.jsp");
	  request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	  CommonsModel.footerData(request);
	  return "../main/main.jsp";
  }
  @RequestMapping("adminpage/admin_reserve.do")
  public String admin_reserve(HttpServletRequest request,HttpServletResponse response)
  {
	  System.out.println(11111);
	  ReserveDAO dao=new ReserveDAO();
	  List<ReserveVO> list=dao.reserveAdminPageData();
	  request.setAttribute("list", list);
	  request.setAttribute("admin_jsp", "../adminpage/admin_reserve.jsp");
	  request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
	  CommonsModel.footerData(request);
	  return "../main/main.jsp";
  }
  @RequestMapping("reserve/reserve_delete.do")
  public String reserve_delete(HttpServletRequest request,HttpServletResponse response)
  {
	  String rno=request.getParameter("rno");
	  //db연동
	  ReserveDAO dao=new ReserveDAO();
	  dao.reserveDelete(Integer.parseInt(rno));
	  return "redirect:../mypage/reserve.do";
  }
}
