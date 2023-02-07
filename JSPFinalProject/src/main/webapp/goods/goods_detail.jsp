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
$(function(){
	$('#account_select').change(function(){
		let price=$(this).attr("data-price");
		let temp=price.replace("원","");
		temp=temp.replace(",","")
		console.log(temp)
		let count=$(this).val();
		let total=Number(count)*Number(temp);
		console.log("가격:"+price);
		console.log("수량:"+count)
		$('#total_price').text(total+"원");
		$('#goods_account').val(count)
		$("#goods_total").val(total)
		
	})
})
</script>
<!-- 
    VueJS
      <script>
       new Vue({
       })
      </script>
     ReactJS
      <script>
        function aaa()
        {
        }
      </script>
 -->
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
<!-- 
   private int no,goods_discount,hit,account;
    private String goods_name,goods_sub,goods_price,goods_first_price,goods_delivery,goods_poster;
 -->
<div class="wrapper row3">
  <main class="container clear">
   <h2 class="sectiontitle">상품 상세보기</h2>
   <div style="height: 5px"></div>
   <table class="table">
     <tr>
       <td width=30% class="text-center" rowspan="6">
         <img src="${vo.goods_poster }" style="width:100%">
       </td>
       <td colspan="2">
         <h3>${vo.goods_name }</h3>
         <sub style="color:gray">${vo.goods_sub }</sub>
       </td>
     </tr>
     <tr>
       <td colspan="2"><h3><span style="color:magenta">${vo.goods_discount }%</span>&nbsp;${vo.goods_price }원</h3></td>
     </tr>
     <tr>
       <td colspan="2" class="inline"><span style="color:green">첫구매할인가</span>&nbsp;<h3>${vo.goods_first_price }</h3></td>
     </tr>
     <tr>
       <td width=20%>배송</td>
       <td width=80%>${vo.goods_delivery }</td>
     </tr>
     <tr>
       <td colspan="2" class="text-center inline">
         수량:
         <select name="account" class="input-sm" data-price="${vo.goods_price }" id="account_select">
           <c:forEach var="i" begin="1" end="${vo.account }">
             <option value="${i }">${i }개</option> 
           </c:forEach>
         </select>
         &nbsp;
         <h3 style="color:blue" id="total_price"></h3>
       </td>
     </tr>
     <c:if test="${sessionScope.id!=null }">
     <tr>
       <td colspan="2" class="text-center">
        <form method="post" action="../goods/cart_insert.do">
         <input type="hidden" name=no id="goods_no" value="${vo.no }">
         <input type="hidden" name=account id="goods_account">
         <input type="hidden" name=total id="goods_total">
         <input type=submit value="장바구니" class="btn btn-lg btn-success">
        </form>
         <input type=button value="구매하기" class="btn btn-lg btn-danger">
       </td>
     </tr>
     </c:if>
   </table>
  </main>
</div>
</body>
</html>
