<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
  $( function() {
    $( "#tabs" ).tabs();
    $('#telBtn').click(function(){
    	let tel=$('#tel').val();
    	if(tel.trim()=="")
    	{
    		$('#tel').focus();
    		return
    	}
    	$.ajax({
    		type:'post',
    		url:'../member/idfind_ok.do',
    		data:{"tel":tel},
    		success:function(response)
    		{
    			let res=response.trim();
    			if(res==='NO')
    			{
    				$('#tel_result').text("존재하지 않는 전화번호입니다")
    				$('#tel').val("")
    				$('#tel').focus();
    			}
    			else
    			{
    				$('#tel_result').html("<font color=red size=20px>"+res+"</font>")
    			}
    		}
    	})
    })
    $('#emailBtn').click(function(){
    	let email=$('#email').val();
    	if(email.trim()=="")
    	{
    		$('#tel').focus();
    		return
    	}
    	$.ajax({
    		type:'post',
    		url:'../member/idfind2_ok.do',
    		data:{"email":email},
    		success:function(response)
    		{
    			let res=response.trim();
    			if(res==='NO')
    			{
    				$('#email_result').text("존재하지 않는 이메일입니다")
    				$('#email').val("")
    				$('#email').focus();
    			}
    			else
    			{
    				$('#email_result').html("<font color=red size=20px>"+res+"</font>")
    			}
    		}
    	})
    })
  });
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
   <div id="tabs">
    <ul>
    <li><a href="#tabs-1">전화번호</a></li>
    <li><a href="#tabs-2">이메일로 찾기</a></li> 
  </ul>
  <div id="tabs-1" class="inline">
    <p>전화번호 : <input type=text name=tel size=20 id=tel class="input-sm">
       <input type=button value="검색" class="btn btn-sm btn-danger" id="telBtn">
    </p>
    <p id="tel_result"></p>
  </div>
  <div id="tabs-2" class="inline">
   <p>이메일 : <input type=text name=email size=20 id=email class="input-sm">
       <input type=button value="검색" class="btn btn-sm btn-primary" id="emailBtn">
    </p>
    <p id="email_result"></p>
  </div>
 </div>
</main>
</div>
</body>
</html>