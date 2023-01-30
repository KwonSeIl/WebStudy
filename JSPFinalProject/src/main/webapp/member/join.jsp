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
Shadowbox.init({
	players:['iframe']
})
$(function(){
	$('#checkBtn').click(function(){
		Shadowbox.open({
			content:'../member/idcheck.do',
			player:'iframe',
			width:420,
			height:180,
			title:'아이디 중복체크'
		})
	})
	$('#postBtn').click(function(){
		Shadowbox.open({
			content:'../member/postfind.do',
			player:'iframe',
			width:580,
			height:450,
			title:'우편번호 검색'
		})
	})
	// email 검색 => 후보키 (unique)
	$('#eBtn').click(function(){
		let email=$('#email').val();
		if(email.trim()==="")
		{
			$('#email').focus();
			return;
		}
		$.ajax({
			type:'post',
			url:'../member/email_check.do',
			data:{"email":email},
			success:function(result)
			{
				let count=Number(result.trim());
				if(count==0)
				{
					$('#ePrint').text(email+"는(은) 사용 가능한 이메일입니다")
					$('#email').prop('readonly',true)// <input readonly>
					// prop('disabled',true) , prop('checked',true)
				}
				else
				{
					$('#ePrint').text(email+"는(은) 이미 사용중인 이메일입니다")
					$('#email').val("")
					$('#email').focus()
				}
			}
		})
	})
	// 전화번호 검색
	$('#tBtn').click(function(){
		let tel1=$('#tel1').val();
		let tel2=$('#tel2').val();
		let phone=tel1+"-"+tel2
		if(tel2.trim()==="")
		{
			$('#tel2').focus();
			return;
		}
		$.ajax({
			type:'post',
			url:'../member/tel_check.do',
			data:{"phone":phone},
			success:function(result)
			{
				let count=Number(result.trim());
				if(count==0)
				{
					$('#tPrint').text(phone+"는(은) 사용 가능한 전화번호입니다")
					$('#tel1').prop('readonly',true)// <input readonly>
					// prop('disabled',true) , prop('checked',true)
				}
				else
				{
					$('#tPrint').text(phone+"는(은) 이미 사용중인 전화번호입니다")
					$('#tel2').val("")
					$('#tel2').focus()
				}
			}
		})
	})
	
	// 회원가입 => 유효성 (NOT NULL) => 오라클 제약조건 
	$('#joinBtn').click(function(){
		let id=$('#join_id').val();
		if(id.trim()==="")
		{
			alert("아이디 중복 체크버튼을 클릭하세요!!");
			$('#id').focus();
			return;
		}
		// 비밀번호 
		let pwd1=$('#join_pwd').val()
		if(pwd1.trim()==="")
		{
			alert("비밀번호는 필수 입력입니다")
			$('#join_pwd').focus();
			return;
		}
		let pwd2=$('#pwd1').val();
		if(pwd1.trim()!==pwd2.trim())
		{
			alert("비밀번호가 틀립니다\n다시입력하세요")
			$('#pwd1').val("")
			$('#pwd1').focus()
			return
		}
		let name=$('#name').val()
		if(name.trim()==="")
		{
			alert("이름은 필수 입력입니다")
			$('#name').focus()
			return 
		}
		let day=$('#day').val()
		if(day.trim()==="")
		{
			alert("생년월일은 필수 입력입니다")
			$('#day').focus()
			return;
		}
		
		let email=$('#email').val()
		if(email.trim()==="")
		{
			alert("Email은 필수 입력입니다")
			$('#email').focus()
			return;
		}
		
		let post=$('#post').val()
		if(post.trim()==="")
		{
			alert("우편번호 검색을 하세요")
			$('#post').focus()
			return;
		}
		
		let addr1=$('#addr1').val()
		if(addr1.trim()==="")
		{
			alert("우편번호 검색을 하세요")
			$('#addr1').focus()
			return;
		}
		
		let tel2=$('#tel2').val()
		if(tel2.trim()==="")
		{
			alert("전화번호를 입력하세요")
			$('#tel2').focus()
			return;
		}
		
		$('#join_frm').submit();
	})
})
</script>
</head>
<body>
<div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <!-- ################################################################################################ -->
    <ul>
      <li>회원 가입</li>
    </ul>
    <!-- ################################################################################################ --> 
   </div>
  </div>
  <div class="wrapper row3 row">
   <main class="container clear">
   <h2 class="sectiontitle">회원가입</h2>
   <form method="post" action="../member/join_ok.do" name="join_frm" id="join_frm">
    <table class="table">
      <tr>
       <th class="text-right" width=15%>아이디</th>
       <td width=85% class="inline">
         <input type=text name=id id="join_id" size=30 class="input-sm" readonly>
         <input type=button id="checkBtn" value="아이디중복체크" class="btn btn-sm btn-primary">
       </td>
      </tr>
      <tr>
       <th class="text-right" width=15%>비밀번호</th>
       <td width=85% class="inline">
         <input type=password name=pwd id=join_pwd size=30 class="input-sm">
         &nbsp;&nbsp;재입력:
         <input type=password name=pwd1 id=pwd1 size=30 class="input-sm">
       </td>
      </tr>
      <tr>
       <th class="text-right" width=15%>이름</th>
       <td width=85%>
         <input type=text name=name id=name size=30 class="input-sm">
       </td>
      </tr>
      <tr>
       <th class="text-right" width=15%>성별</th>
       <td width=85% class="inline">
         <input type=radio value="남자" name=sex checked="checked">남자
         <input type=radio value="남자" name=sex>여자
       </td>
      </tr>
      <tr>
       <th class="text-right" width=15%>생년월일</th>
       <td width=85%>
         <input type=date size=30 name=birthday class="input-sm" id="day">
       </td>
      </tr>
      <tr>
       <th class="text-right" width=15%>이메일</th>
       <td width=85% class="inline">
         <input type=text name="email" id=email size=70 class="input-sm">
         <input type=button id="eBtn" class="btn btn-sm btn-success" value="이메일확인">
         &nbsp;<span style="color:blue" id="ePrint"></span>
       </td>
      </tr>
      <tr>
       <th class="text-right" width=15%>우편번호</th>
       <td width=85% class='inline'>
         <input type=text name=post id=post size=30 class="input-sm" readonly>
         <input type=button id="postBtn" value="우편번호찾기"
          class="btn btn-sm btn-danger">
       </td>
      </tr>
      <tr>
       <th class="text-right" width=15%>주소</th>
       <td width=85%>
         <input type=text name=addr1 id=addr1 size=95 class="input-sm" readonly>
       </td>
      </tr>
      <tr>
       <th class="text-right" width=15%>상세주소</th>
       <td width=85%>
         <input type=text name=addr2 id=addr2 size=95 class="input-sm">
       </td>
      </tr>
      <tr>
       <th class="text-right" width=15%>전화번호</th>
       <td width=85% class="inline">
         <input type=text name=tel1 id=tel1 size=15 class="input-sm" value="010">
         <input type=text name=tel2 id=tel2 size=30 class="input-sm">
         <input type=button id="tBtn" class="btn btn-sm btn-info" value="전화확인">
         &nbsp;<span style="color:blue" id="tPrint"></span>
       </td>
      </tr>
      <tr>
       <th class="text-right" width=15%>소개</th>
       <td width=85%>
         <textarea rows="10" cols="100" id="content" name="content"></textarea>
       </td>
      </tr>
      <tr>
        <td colspan="2" class="text-center">
         <input type=button class="btn btn-sm btn-primary" value="회원가입" id="joinBtn" >
         <input type=button class="btn btn-sm btn-danger" value="취소"
          onclick="javascript:history.back()">
        </td>
      </tr>
    </table>
    </form>
   </main>
  </div>
</body>
</html>