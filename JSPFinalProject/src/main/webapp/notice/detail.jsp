<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
       <h2 class="sectiontitle">내용보기</h2>
       <div style="height: 5px"></div>
       <table class="table">
         <tr>
           <th width="20%" class="text-center">구분</th>
           <td width="30%" class="text-center">${vo.prefix }</td>
           <th width="20%" class="text-center">작성일</th>
           <td width="30%" class="text-center">${vo.dbday }</td>
         </tr>
         <tr>
           <th width="20%" class="text-center">이름</th>
           <td width="30%" class="text-center">${vo.name }</td>
           <th width="20%" class="text-center">조회수</th>
           <td width="30%" class="text-center">${vo.hit }</td>
         </tr>
         <tr>
           <th width="20%" class="text-center">제목</th>
           <td colspan="3">${vo.subject }</td>
         </tr>
         <tr>
           <td class="text-left" valign="top" colspan="4" height="200"><pre style="white-space: pre-wrap;background: white;border: none;">${vo.content }</pre></td>
         </tr>
         <tr>
           <td class="text-right" colspan="4">
             <a href="../notice/list.do" class="btn btn-sm btn-info">목록</a>
           </td>
         </tr>
       </table>
       </main>
     </div>
</body>
</html>