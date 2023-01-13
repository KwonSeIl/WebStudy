<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <center>
    <%--
        multipart/form-data:upload 프로토콜 
     --%>
    <form method="post" action="upload_ok.jsp" enctype="multipart/form-data">
      <input type=file size=20 name=upload>
      <input type=submit value="전송">
    </form>
  </center>
</body>
</html>