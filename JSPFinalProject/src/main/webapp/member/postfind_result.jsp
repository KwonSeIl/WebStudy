<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
function ok(zip,addr)
{
	//join.jsp에 값을 첨부
	parent.join_frm.post.value=zip
	parent.join_frm.addr1.value=addr
	parent.Shadowbox.close()
}
</script>
</head>
<body>
  <c:if test="${count==0 }">
    <table class="table">
      <tr>
        <td class="text-center">검색 결과가 없습니다</td>
      </tr>
    </table>
  </c:if>
  <c:if test="${count!=0 }">
    <table class="table">
      <tr class="success">
        <th class="text-center" width="15%">우편번호</th>
        <th class="text-center" width="85%">주소</th>
      </tr>
      <c:forEach var="vo" items="${list }">
        <tr>
          <td class="text-center" width="15%">${vo.zipcode }</td>
          <td width=85%><a href="javascript:ok('${vo.zipcode }','${vo.address }')">${vo.address }</a></td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
</body>
</html>