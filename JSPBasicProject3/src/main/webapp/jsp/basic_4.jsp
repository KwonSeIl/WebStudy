<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<%
	try
	{
		// 드라이버 등록
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 오라클 연결
		final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		Connection conn=DriverManager.getConnection(URL,"hr","happy");
		// SQL 문장 전송
		String sql="SELECT hakbun,name,kor,eng,math,(kor+eng+math) total,ROUND((kor+eng+math)/3,2) avg "
					+"FROM student2 "
					+"ORDER BY hakbun ASC";
		// 오라클로 SQL문장 전송
		PreparedStatement ps=conn.prepareStatement(sql);
		// 실행 요청 -> 결과값 받기
		ResultSet rs=ps.executeQuery();
		// 메모리에 저장된 데이터 출력
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <center>
    <table width=700 border=1>
      <tr>
        <th>학번</th>
        <th>이름</th>
        <th>국어</th>
        <th>영어</th>
        <th>수학</th>
        <th>총점</th>
        <th>평균</th>
      </tr>
      <%
      	while(rs.next())
      	{
      		int h=rs.getInt(1);
      		if(h==3)
      		{
      %>
      		<tr>
      		  <td><%=rs.getInt(1) %></td>
      		  <td><%=rs.getString(2) %>
      		  <td><%=rs.getInt(3) %></td>
      		  <td><%=rs.getInt(4) %></td>
      		  <td><%=rs.getInt(5) %></td>
      		  <td><%=rs.getInt(6) %></td>
      		  <td><%=rs.getDouble(7) %></td>
      		</tr>
      <%
      		}
      		else if(h==5)
      		{
      %>
      			<tr bgcolor="yellow">
      		  <td><%=rs.getInt(1) %></td>
      		  <td><%=rs.getString(2) %>
      		  <td><%=rs.getInt(3) %></td>
      		  <td><%=rs.getInt(4) %></td>
      		  <td><%=rs.getInt(5) %></td>
      		  <td><%=rs.getInt(6) %></td>
      		  <td><%=rs.getDouble(7) %></td>
      		</tr>
      <%
      		}
      		else if(h==4)
      		{
      %>
      			<tr bgcolor="pink">
      		  <td><%=rs.getInt(1) %></td>
      		  <td><%=rs.getString(2) %>
      		  <td><%=rs.getInt(3) %></td>
      		  <td><%=rs.getInt(4) %></td>
      		  <td><%=rs.getInt(5) %></td>
      		  <td><%=rs.getInt(6) %></td>
      		  <td><%=rs.getDouble(7) %></td>
      		</tr>
      <%
      		}
      	}
      	rs.close();
      	ps.close();
      	conn.close();
      %>
    </table>
  </center>
</body>
</html>
<%
	}catch(Exception ex){}
%>