<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*,com.sist.vo.*"%>
<jsp:useBean id="dao" class="com.sist.dao.SeoulDAO"/>
<%
	// 자바에서 오라클에 있는 데이터를 읽어 온다
	//1. 사용자가 보내준 값을 받는다: page
	String strPage=request.getParameter("page");
	if(strPage==null) //첫 페이지
		strPage="1";
	int curpage=Integer.parseInt(strPage);
	//해당 페이지의 값을 가지고 온다
	ArrayList<SeoulVO> list=dao.seoulListData(curpage, "location");
	//총 페이지 
	int totalpage=dao.seoulTotalPage("location");
	//블록별 페이지 
	
	 final int BLOCK=5;
    int startPage=((curpage-1)/BLOCK*BLOCK)+1;
    /*
         [1][2][3][4][5]  <== [6][7][8][9][10]
        		 (curpage-1)/BLOCK*BLOCK)+1
                 ------------------------- 1~5
                   (5-1)/5*5 => 0  ==> 1
         curpage = 1  2  3  4  5
                   startPage = 1 1 1 1 1
                   6 7 8 9 10
                   (10-1)/5*5
                   --------
                      1*5 => 5+1 => 6
                   startPage = 6 6 6 6 6
                   
         startPage ==> 1 , 6 , 11 , 16....
    */
    int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
    /*
         [1][2][3][4][5] ==> [6][7][8][9][10]
         ---         ---     ---         ----
         sp           ep      sp          ep
         
         endPage = 5 , 10 , 15 ....
    */
    if(endPage>totalpage)
    	endPage=totalpage;
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="row">
    <%
        for(SeoulVO vo:list)
        {
    %>
             <div class="col-md-3">
			    <div class="thumbnail">
			      <a href="#">
			        <img src="<%=vo.getPoster() %>" style="width:260px;height: 260px">
			        <div class="caption">
			          <p style="font-size: 9px;font-weight: bold"><%=vo.getTitle() %></p>
			        </div>
			      </a>
			    </div>
			  </div>
    <%
        }
    %>
  </div>
  <div class="row">
    <div class="text-center">
      <ul class="pagination">
          <%
              if(startPage>1)
              {
          %>
                 <li><a href="../main/main.jsp?mode=1&page=<%=startPage-1%>">&lt;</a></li>
          <%
              }
          %>
          <%
            for(int i=startPage;i<=endPage;i++)
            {
               
          %>
		       <li <%= i==curpage?"class=active":"" %>><a href="../main/main.jsp?mode=1&page=<%=i%>"><%=i %></a></li>
		  <%
            }
		  %>
		  <%
		      if(endPage<totalpage)
		      {
		  %>
		       <li><a href="../main/main.jsp?mode=1&page=<%=endPage+1%>">&gt;</a></li>
		  <%
		      }
		  %>
		</ul>
    </div>
  </div>
</body>
</html>
