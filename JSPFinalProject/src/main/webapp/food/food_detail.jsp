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
let u=0;
$(function(){
	$('.ups').click(function(){
		$('.rupdate').hide();
		let rno=$(this).attr("data-no");
		if(u==0)
		{
			$(this).text("취소");
			$('#u'+rno).show();
			u=1;
		}
		else
		{
			$(this).text("수정");
			$('#u'+rno).hide();
			u=0;
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

<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <table class="table">
      <tr>
        <c:forTokens items="${vo.poster }" delims="^" var="image">
          <td>
            <img src="${image }" style="width: 100%" class="img-rounded">
          </td>
        </c:forTokens>
      </tr>
    </table>
    <div class="content three_quarter first"> 
      <div>
        <%-- 상세 --%>
        <table class="table">
          <tr>
            <td colspan="2"><h4>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></h4></td>
          </tr>
          <tr>
            <th width=20% style="color:gray">주소</th>
            <td width="80%">${addr1 }<br>
             <sub style="color:gray">지번:${addr2 }</sub>
            </td>
          </tr>
          <tr>
            <th width=20% style="color:gray">전화번호</th>
            <td width="80%">${vo.tel }</td>
          </tr>
          <tr>
            <th width=20% style="color:gray">음식종류</th>
            <td width="80%">${vo.type }</td>
          </tr>
          <tr>
            <th width=20% style="color:gray">가격대</th>
            <td width="80%">${vo.price }</td>
          </tr>
          <tr>
            <th width=20% style="color:gray">주차</th>
            <td width="80%">${vo.parking }</td>
          </tr>
          <tr>
            <th width=20% style="color:gray">영업시간</th>
            <td width="80%">${vo.time }</td>
          </tr>
          <c:if test="${vo.menu!='no' }">
	          <tr>
	            <th width=20% style="color:gray">메뉴</th>
	            <td width="80%">
	              <ul>
	                <c:forTokens items="${vo.menu }" delims="원" var="m">
	                  <li>${m }원</li>
	                </c:forTokens>
	              </ul>
	            </td>
	          </tr>
          </c:if>
          <tr>
            <td colspan="2" class="text-right">
              <c:if test="${sessionScope.id!=null }">
              <c:if test="${like_count==0 }">
              <a href="../like/like_insert.do?fno=${vo.fno }" class="btn btn-xs btn-info">좋아요(${like_total })</a>
              </c:if>
              <c:if test="${like_count!=0 }">
              <span class="btn btn-xs btn-default">좋아요(${like_total })</span>
              </c:if>
              
              <c:if test="${jjim_count==0 }">
               <a href="../jjim/jjim_insert.do?fno=${vo.fno }" class="btn btn-xs btn-success">찜하기</a>
               </c:if>
              <c:if test="${jjim_count!=0 }">
                <span class="btn btn-xs btn-default">찜하기</span>
              </c:if>
              <a href="#" class="btn btn-xs btn-warning">예약하기</a>
              </c:if>
              <a href="javascript:history.back()" class="btn btn-xs btn-primary">목록</a>
            </td>
          </tr>
        </table>
      </div>
      <div style="height: 20px"></div>
      <h2 class="sectiontitle">댓글</h2>
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
                     <c:forEach var="rvo" items="${rList }">
                       <table class="table">
                         <tr>
                           <td class="text-left" width="85%">◑<span style="color:orange">${rvo.name }</span>&nbsp;(${rvo.dbday })</td>
                           <td class="text-right" width="15%">
                             <c:if test="${sessionScope.id!=null }">
                               <c:if test="${sessionScope.id==rvo.id }">
                                 <span href="#" class="btn btn-xs btn-danger ups" data-no="${rvo.rno }">수정</span>
                                 <a href="../all_reply/all_reply_delete.do?rno=${rvo.rno }&no=${vo.fno}&cate_no=2" class="btn btn-xs btn-primary">삭제</a>
                               </c:if>
                             </c:if>
                           </td>
                         </tr>
                         <tr>
                           <td colspan="2"><pre style="white-space: pre-wrap;background-color: white;border: none">${rvo.msg }</pre></td>
                         </tr>
                         <tr id="u${rvo.rno }" class="rupdate" style="display: none">
                             <td colspan="2">
                               <form method="post" action="../all_reply/all_reply_update.do">
                                  <input type="hidden" name="no" value="${vo.fno }"> 
                                  <input type="hidden" name="rno" value="${rvo.rno }">
                                  <input type="hidden" name="cate_no" value="2">
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
        <table class="table">       
        <c:if test="${sessionScope.id!=null }">
          <table class="table">
           <tr>
            <td>
             <form method="post" action="../all_reply/all_reply_insert.do">
               <input type="hidden" name="no" value="${vo.fno }">
               <input type="hidden" name="cate_no" value="2">
               <%--
               	1. seoul_locateion
               	2. food
               	3. goods
                --%>
               <textarea rows="3" cols="90" name="msg" style="float: left"></textarea>&nbsp;
               <input type=submit value="댓글쓰기" class="btn btn-sm btn-danger" style="height: 65px">
             </form>
            </td>
           </tr>
          </table>
        </c:if>
      </table> 
    </div>
   
    <div class="sidebar one_quarter"> 
      <div class="sdb_holder">
        <%-- 지도 --%>
        <div id="map" style="width:100%;height:350px;"></div>
		<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch('${addr1}', function(result, status) {
		
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">${vo.name}</div>'
		        });
		        infowindow.open(map, marker);
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
		</script>
      </div>
      <div class="sdb_holder">
        <%-- 관련 레시피 ... --%>
        <h2 class="sectiontitle">관련 레시피</h2>
        <table class="table">
          <tr>
            <td>
              <c:forEach var="kvo" items="${nList1 }">
                <table class="table">
                  <tr>
                    <td>
                      <img src="${kvo.poster }" style="width: 100%">
                    </td>
                  </tr>
                  <tr>
                    <td>${kvo.title }</td>
                  </tr>
                </table>
              </c:forEach>
            </td>
          </tr>
        </table>
      </div>  
    </div>
   
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>