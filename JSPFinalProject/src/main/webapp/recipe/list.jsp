<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
     <!-- ################################################################################################ --> 
     <!-- ################################################################################################ --> 
     <!-- ################################################################################################ -->
     <div class="wrapper row3">
       <main class="container clear">
       <h2 class="sectiontitle">총 <span style="color:green;font-size: 35px"><fmt:formatNumber value="${count }" type="number"/></span>개의 맛있는 레시피가 있습니다.</h2>
       <div style="height: 5px"></div>
       <div class="content"> 
         <!-- ################################################################################################ -->
         <div id="gallery">
           <figure>
             <header class="heading">
             </header>
             <ul class="nospace clear">
              <c:forEach var ="vo" items="${list }" varStatus="s">
               <li class="one_quarter ${s.index%4==0?'first':'' }"><a href="#"><img src="${vo.poster }" title="${vo.title }"></a></li>
               </c:forEach>
               
             </ul>
             <figcaption></figcaption>
           </figure>
         </div>
         <!-- ################################################################################################ --> 
         <!-- ################################################################################################ -->
         <nav class="pagination">
           <ul>
            <c:if test="${startPage>1 }">
          <li><a href="../recipe/list.do?page=${startPage-1 }">&laquo; Previous</a></li>
          </c:if>
           <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <li ${i==curpage?"class==current":"" }><a href="../recipe/list.do?page=${i }">${i}</a></li>
          </c:forEach>
          <c:if test="${endPage<totalpage }">
          <li><a href="../recipe/list.do?page=${endPage+1 }">Next &raquo;</a></li>
          </c:if>
           </ul>
         </nav>
         <!-- ################################################################################################ --> 
       </div>
       </main>
     </div>

</body>
</html>