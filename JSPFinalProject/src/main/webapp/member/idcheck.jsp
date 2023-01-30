<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 30px;
}
.row{
	width: 350px;
	margin: 0px auto;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#okBtn').hide();
	$('#idBtn').on('click',function(){
		let id=$('#id').val()
		if(id.trim()=="")
		{
			$('#id').focus();
			return
		}
		//입력이 된 상태 => id를 보내고 결과값을 읽어서 출력(송수신) => axios.get(), axios.post() => Vue,React
		// a.jsp => 서버 => a.jsp, a.jsp <===> 서버
		// -----           ----- 새로운 파일 생성
		/*
			HTML: 정적(화면만 출력)
			JavaScript: 브라우저 동적으로 변경
			--------------------------------- 브라우저에서만 사용이 가능(단점: 오라클 연결이 안된다)
			Java
			  = Java: 오라클 연결
			  = Servlet: 브라우저 연결
			    request,session
			    						  Model호출 시 .do 사용
			JSP ====> Controller 거침 ====> Model 거침 ====> DAO 거침
			    <====                <====          
		*/
		$.ajax({
			type:'post',
			url:'../member/idcheck_result.do', //MVC (Model=자바파일을 거친다)
			data:{"id":id},
			// 실행된 JSP파일을 읽어온다
			success:function(result) //콜백: 시스템에 의해 자동 호출
			{
				let res=Number(result.trim()); //정수형 변경
				if(res===0)
				{
					$('#result').html('<font color=blue>'+id+"는(은) 사용 가능한 아이디입니다</font>");
					$('#okBtn').show();
				}
				else
				{
					$('#result').html('<font color=red>'+id+"는(은) 이미 사용중인 아이디입니다</font>");
					$('#okBtn').hide();
				}
				
			}
		})
	})
	$('#okBtn').click(function(){
		parent.join_frm.id.value=$('#id').val();
		parent.Shadowbox.close();
		
	})
	
})
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <table class="table">
        <tr>
          <td>ID:<input type="text" name="id" id="id" size=15 class="input-sm">
            <input type="button" value="아이디중복체크" class="btn btn-sm btn-primary" id="idBtn">
          </td>
        </tr>
        <tr>
          <td>
		    <span id="result"></span>
          </td>
        </tr>
        <tr>
          <td class="text-center">
            <input type="button" value="확인" class="btn btn-sm btn-success" id="okBtn">
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>