<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container{
	margin-top: 30px;
}
.row{
	width: 800px;
	margin: 0px auto;
}
</style>
<script type="text/javascript">
let calc=()=>{
	let price=document.querySelector("#price").getAttribute("value"); //$("#price").attr("value")
	//태그의 속성값 읽기
	//alert(price)
	let count=document.querySelector("#count").value; //$('#count').val()
	//alert("count="+count)
	let total=price*Number(count);
	document.querySelector("#total").innerText=total; //$('#total').text(total)
}
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <table class="table">
      <tr>
        <td width="30%" class="text-center" rowspan="7">
          <img src="https://recipe1.ezmember.co.kr/cache/data/goods/22/05/19/1000028009/1000028009_detail_04.jpg" style="width: 300px;height: 350px">
        </td>
        <td colspan="2"><h3>[만개특가] 아이닉 차이슨 무선청소기 new i20</h3><sub style="color:gray">3분에 1대씩 팔리는 청소기아이닉</sub></td>
      </tr>
      <tr>
        <td colspan="2">
          <h3><span style="color:magenta">44%</span>&nbsp;&nbsp;179,000원</h3>
          <sub style="color: gray">320,000원</sub>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <span style="color:green">첫구매할인가&nbsp;&nbsp;170,050원</span>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          배송 무료배송
        </td>
      </tr>
      <tr>
        <td colspan="2">
          적립 895원 적립 (모든 회원 구매액의 0.5% 적립)
        </td>
      </tr>
      <tr>
        <td width="30%">
          <span value="179000" id="price">179,000</span>
        </td>
        <td width="40%">
          <select id="count" class="input-sm" onchange="calc()">
            <option selected value="1">1개</option>
            <option value="2">2개</option>
            <option value="3">3개</option>
            <option value="4">4개</option>
            <option value="5">5개</option>
          </select>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="text-right">
          주문금액&nbsp;<span style="color:green;font-size: 20px" id="total">&nbsp;</span>원
        </td>
      </tr>
    </table>
    </div>
  </div>
</body>
</html>