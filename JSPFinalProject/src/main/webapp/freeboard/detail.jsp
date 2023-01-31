<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let i=0;
let u=0;
let r=0;
$(function(){
	$('#delete').click(function(){
		if(i===0)
		{
			$('#del').show();
			$('#delete').text("취소");
			i=1
		}
		else
		{
			$('#del').hide();
			$('#delete').text("수정");
			i=0
		}
	})
	$('#del_btn').on('click',function(){
		let pwd=$('#del_pwd').val()
		if(pwd.trim()==="")
		{
			$('#del_pwd').focus()
			return;
		}
		let no=$('#del').attr("data-no");
		$.ajax({
			type:'post',
			url:'../freeboard/delete.do',
			data:{"no":no,"pwd":pwd},
			success:function(result)
			{
				let res=result.trim();
				if(res==='yes')
				{
					location.href="../freeboard/list.do"
				}
				else
				{
					alert("비밀번호가 틀립니다!!\n다시 입력하세요")
					$('#del_pwd').val("")
					$('#del_pwd').focus()
				}
			}
		})
	})
	
	$('.ups').click(function(){
		$('.rinsert').hide();
		$('.rupdate').hide();
		let no=$(this).attr("data-no");
		if(u===0)
		{
			$('#u'+no).show();
			$(this).text("취소")
			u=1;
		}
		else
		{
			$('#u'+no).hide();
			$(this).text("수정")
			u=0;
		}
	})
	
	$('.replys').click(function(){
		$('.rinsert').hide();
		$('.rupdate').hide();
		
		let no=$(this).attr("data-no");
		if(r===0)
		{
			$('#r'+no).show();
			$(this).text("취소")
			r=1;
		}
		else
		{
			$('#r'+no).hide();
			$(this).text("댓글")
			r=0;
		}
	})
})
</script>
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
           <th width="20%" class="text-center">번호</th>
           <td width="30%" class="text-center">${vo.no }</td>
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
             <a href="../freeboard/update.do?no=${vo.no }" class="btn btn-sm btn-success">수정</a>
             <span class="btn btn-sm btn-warning" id="delete">삭제</span>
             <a href="../freeboard/list.do" class="btn btn-sm btn-info">목록</a>
           </td>
         </tr>
         <tr id="del" style="display:none" data-no="${vo.no }">
           <td colspan="4" class="text-right">
           <form id="del_frm" class="inline">
             비밀번호:<input type=password class="input-sm" size=15 id="del_pwd">
             <input type=button value="삭제" class="btn btn-sm btn-primary" id="del_btn">
           </form>s
           </td>
         </tr>
       </table>
       <div style="height: 5px"></div>
         <div class="content three_quarter first"> 
            <h2 class="sectiontitle">댓글(${count })</h2>
               <c:if test="${count==0 }">
                 <table class="table">
                   <tr>
                     <td class="text-center">댓글이 없습니다</td>
                   </tr>
                 </table>
               </c:if>
               <c:if test="${count>0 }">
                 <table class="table">
                   <tr>
                     <td>
                       <c:forEach var="rvo" items="${list }">
                         <table class="table">
                           <tr>
                             <td class="text-left">
                               <c:if test="${rvo.group_tab>0 }">
                                 <c:forEach var="i" begin="1" end="${rvo.group_tab }">
                                   &nbsp;&nbsp;
                                 </c:forEach>
                                 <img src="image/re_icon.png">
                               </c:if>
                             ◑<span style="color:orange">${rvo.name }</span>&nbsp;(${rvo.dbday })</td>
                             <td class="text-right">
                               <c:if test="${sessionScope.id!=null }">
                                 <c:if test="${sessionScope.id==rvo.id }">
                                   <span class="btn btn-xs btn-success ups" data-no="${rvo.rno }">수정</span>
                                   <a href="../freeboard/reply_delete.do?rno=${rvo.rno }&bno=${vo.no}" class="btn btn-xs btn-info">삭제</a>
                                 </c:if>
                                 <span class="btn btn-xs btn-warning replys" data-no="${rvo.rno }">댓글</span>
                               </c:if>
                             </td>
                           </tr>
                           <tr>
                             <td colspan="2">
                               <pre style="white-space: pre-wrap;background-color: white;border: none">${rvo.msg }</pre>
                             </td>
                           </tr>
                           <%-- 대댓글 --%>
                           <tr id="r${rvo.rno }" class="rinsert" style="display: none">
                             <td colspan="2">
                               <form method="post" action="../freeboard/reply_reply_insert.do">
                                  <input type="hidden" name="bno" value="${vo.no }">
                                  <input type="hidden" name="pno" value="${rvo.rno }">
                                  <textarea rows="3" cols="90" name="msg" style="float: left"></textarea>&nbsp;
                                  <input type=submit value="댓글" class="btn btn-sm btn-danger" style="height: 65px">
                               </form>
                             </td>
                           </tr>
                           <%-- 수정 --%>
                           <tr id="u${rvo.rno }" class="rupdate" style="display: none">
                             <td colspan="2">
                               <form method="post" action="../freeboard/reply_update.do">
                                  <input type="hidden" name="bno" value="${vo.no }"> 
                                  <input type="hidden" name="rno" value="${rvo.rno }">
                                  <textarea rows="3" cols="90" name="msg" style="float: left">${rvo.msg }</textarea>&nbsp;
                                  <input type=submit value="수정" class="btn btn-sm btn-danger" style="height: 65px">
                               </form>
                             </td>
                           </tr>
                         </table>
                       </c:forEach>
                     </td>
                   </tr>
                 </table>
               </c:if>
               <c:if test="${sessionScope.id!=null }">
                 <table class="table">
                   <tr>
                     <td>
                       <form method="post" action="../freeboard/reply_insert.do">
                         <input type="hidden" name="bno" value="${vo.no }">
                         <textarea rows="3" cols="90" name="msg" style="float: left"></textarea>&nbsp;
                         <input type=submit value="댓글쓰기" class="btn btn-sm btn-danger" style="height: 65px">
                       </form>
                     </td>
                   </tr>
                 </table>
               </c:if>
         </div>
         <div class="sidebar one_quarter"> 
           <h2 class="sectiontitle">인기게시물 Top5</h2>
         </div>
       </main>
     </div>
</body>
</html>