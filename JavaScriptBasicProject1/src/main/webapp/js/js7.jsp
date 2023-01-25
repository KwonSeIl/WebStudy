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
   margin-top:100px;
}
.row{
   width: 350px;
   margin: 0px auto;
}
</style>
<script type="text/javascript">
function login(){
   let id=document.querySelector("#id"); //태그를 읽어올 때
   if(id.value==="")
	{
	   let div=document.querySelector(".id");
	   div.innerHTML="<font color=red>아이디를  입력하세요</font>"
	   id.focus();
	   return
	}
   else
	{
	   let div=document.querySelector(".id");
	   div.innerHTML=""
	   
	}
   
   let pwd=document.querySelector("#pwd"); //태그를 읽어올 때
   if(pwd.value==="")
	{
	   let div=document.querySelector(".pwd");
	   div.innerHTML="<font color=red>비밀번호를  입력하세요</font>"
	   pwd.focus();
	   return
	}
   else
	{
	   let div=document.querySelector(".pwd");
	   div.innerHTML=""
	   
	}
   
   alert("로그인 되었습니다!!")
}
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <table class="table">
        <tr>
          <td width="20%" class="text-center">ID</td>
          <td width="80%">
            <input type="text" id=id size=15 class="input-sm">
            <div class="id"></div>
          </td>
        </tr>
        <tr>
          <td width="20%" class="text-center">PW</td>
          <td width="80%">
            <input type="password" id=pwd size=15 class="input-sm">
            <div class="pwd"></div>
          </td>
        </tr>
        <tr>
          <td colspan="2" class="text-center"> 
            <input type=button value="로그인" class="btn btn-sm btn-danger" onclick="login()">
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>