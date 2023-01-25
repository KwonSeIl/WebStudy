<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <!-- ################################################################################################ -->
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">Lorem</a></li>
      <li><a href="#">Ipsum</a></li>
      <li><a href="#">Dolor</a></li>
    </ul>
    <!-- ################################################################################################ --> 
  </div>
</div>
<div class="wrapper row3">
  <main class="container clear"> 
     <div class="jumbotron">
        <h3 class="text-center">${vo.title }</h3>
        <h4 class="text-center">${vo.subject }</h4>
     </div>
     <table class="table">
        <tr>
           <td>
              <c:forEach var="fvo" items="${list }">
                 <table class="table">
                    <tr>
                       <td width="30%" class="text-center" rowspan="4">
                          <a href="../food/food_detail.do?fno=${fvo.fno }"><img src="${fvo.poster }" style="width: 300px;height:160px" class="img-rounded"></a>
                       </td>
                       <td width=70%><h4><a href="../food/food_detail.do?fno=${fvo.fno }">${fvo.name }</a>&nbsp;<span style="color:orange">${fvo.score }</span></h4></td>
                    </tr>
                    <tr>
                       <td width=70%>${fvo.address }</td>
                    </tr>
                    <tr>
                       <td width=70%>${fvo.tel }</td>
                    </tr>
                    <tr>
                       <td width=70%>${fvo.type }</td>
                    </tr>
                 </table>
              </c:forEach>
           </td>
        </tr>
     </table>
  </main>
</div>
</body>
</html>