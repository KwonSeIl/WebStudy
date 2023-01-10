<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>
<%
	String fno=request.getParameter("fno");
	//FoodVO
	FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Work',     11],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);

        var options = {
          title: 'My Daily Activities'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
</head>
<body>
  <table class="table">
    <tr>
      <%
      	String poster=vo.getPoster();
      	poster=poster.replace("#","&");
      	StringTokenizer st=new StringTokenizer(poster,"^");
      	while(st.hasMoreTokens())
      	{
      %>
      		<td class="text-center">
      		  <img src="<%=st.nextToken() %>" style="width:100%">
      		</td>
      <%		
      	}
      %>
    </tr>
  </table>
  <div style="height: 10px"></div>
  <div class="row">
    <div class="col-sm-8">
      <table class="table">
        <tr>
          <td colspan="2">
            <h3><%=vo.getName() %>&nbsp;<span style="color:orange"><%=vo.getScore() %></span></h3>
          </td>
        </tr>
        <tr>
          <th class="text-right" width=15%><span style="color:gray">주소</span></th>
          <td width="85%"><%=vo.getAddress() %></td>
        </tr>
        <tr>
          <th class="text-right" width=15%><span style="color:gray">전화</span></th>
          <td width="85%"><%=vo.getTel() %></td>
        </tr>
        <tr>
          <th class="text-right" width=15%><span style="color:gray">음식종류</span></th>
          <td width="85%"><%=vo.getType() %></td>
        </tr>
        <tr>
          <th class="text-right" width=15%><span style="color:gray">가격대</span></th>
          <td width="85%"><%=vo.getPrice() %></td>
        </tr>
        <tr>
          <th class="text-right" width=15%><span style="color:gray">영업시간</span></th>
          <td width="85%"><%=vo.getTime() %></td>
        </tr>
        <tr>
          <th class="text-right" width=15%><span style="color:gray">주차</span></th>
          <td width="85%"><%=vo.getParking() %></td>
        </tr>
        <%
        	if(!vo.getMenu().equals("no"))
        	{
        %>
        <tr>
          <th class="text-right" width=15%><span style="color:gray">메뉴</span></th>
          <td width="85%">
            <ul>
              <%
              	st=new StringTokenizer(vo.getMenu(),"원");
              	while(st.hasMoreTokens())
              	{
              %>
              		<li><%=st.nextToken()%>원</li>
              <%
              	}
              %>
            </ul>
          </td>
        </tr>
        <%
        	}
        %>
      </table>
      <div id="piechart" style="width: 900px; height: 500px;"></div>
    </div>
    <div class="col-sm-4">
    
    </div>
  </div>
</body>
</html>