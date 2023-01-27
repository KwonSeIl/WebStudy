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
	width: 500px;
	margin: 0px auto;
}
</style>
<script type="text/javascript">
function calc(){
	//국어 영어 수학 점수 읽기
	let kor=document.querySelector("#kor").value
	if(kor.trim()=="") //입력 여부 확인 => 유효성 검사 => 회원가입, 회원 수정 => not null => spring(validation)
	{
		document.querySelector("#kor").focus();
		return 
	}
	let eng=document.querySelector("#eng").value
	if(eng.trim()=="") //입력 여부 확인 => 유효성 검사 => 회원가입, 회원 수정 => not null => spring(validation)
	{
		document.querySelector("#eng").focus();
		return 
	}
	let math=document.querySelector("#math").value
	if math.trim()=="") //입력 여부 확인 => 유효성 검사 => 회원가입, 회원 수정 => not null => spring(validation)
	{
		document.querySelector("#math").focus();
		return 
	}
	
	let total=Number(kor)+Number(eng)+Number(math);
	document.querySelector('#total').value=total;
	
	let avg=total/3
	document.querySelector("#avg").value=Math.round(avg,2);
}
/*
 * 	vue
 	let calc=function(){
	
	}
	react,node
	let calc=()=>{
		
	}
 */
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <h1 class="text-center">성적 계산</h1>
      <table class="table">
      <tr>
        <td class="text-center" width=30%>국어</td> 
        <td width="70%">
          <input type="text" id=kor size=15 class="input-sm">
        </td>
      </tr>
      <tr>
        <td class="text-center" width=30%>영어</td> 
        <td width="70%">
          <input type="text" id=eng size=15 class="input-sm">
        </td>
      </tr>
      <tr>
        <td class="text-center" width=30%>수학</td> 
        <td width="70%">
          <input type="text" id=math size=15 class="input-sm">
        </td>
      </tr>
      <tr>
        <td class="text-center" width=30%>총점</td> 
        <td width="70%">
          <input type="text" id=total size=15 class="input-sm" readonly>
        </td>
      </tr>
      <tr>
        <td class="text-center" width=30%>평균</td> 
        <td width="70%">
          <input type="text" id=avg size=15 class="input-sm" readonly>
        </td>
      </tr>
      <tr>
        <td colspan="2" class="text-center">
          <input type="button" value="계산" class="btn btn-sm btn-primary" onclick="calc()">
        </td>
        <%--
        	이벤트: 태그에 행위를 했을 때 기능을 처리
        	 1) <select>
        	 2) 버튼 종류
        	   <input type=button>
        	   <input type=image>
        	   <input type=submit>
        	   <button> <a> ==> click
        	   => onclikc="처리할 함수 호출"
        	 3) 모든 태그에 적용: onmouseover, onmouseout
        	 4) 입력창: onkeyup, onkeydown
         --%>
      </tr>
      </table>
    </div>
  </div>
</body>
</html>