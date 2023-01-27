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
.container-fluid{
	margin-top: 30px;
}
.row{
	width: 300px;
	margin: 0px auto;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#sh').hide();
	$('#checkBtn').click(function(){
		let id=$('#id').val();
		if(id.trim()=="")
		{
			$('#id').focus();
			return
		}
		// 전송 => 결과값 받기 ==> Vue,react
		$.ajax({
			type:'post',
			url:'idcheck_result.jsp',
			data:{"id":id},
			success:function(result)
			{
				let value=result.trim();
				if(value==0)
				{
					$('#result').html('<font color=blue>'+id+'는(은) 사용 가능한 아이디입니다');
					$('#sh').show();
				}
				else
				{
					$('#result').html('<font color=red>'+id+'는(은) 이미 사용 중인 아이디입니다');
					$('#sh').hide();
				}
			}
		})
	})
	$('#ok').click(function(){
		parent.join_frm.id.value=$('#id').val()
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
          <td>ID:
            <input type=text name=id id=id size=15 class="input-sm">
            <input type=button id="checkBtn" value="아이디체크" class="btn btn-sm btn-primary">
          </td>
        </tr>
        <tr>
          <td class="text-center" id="result">ID를 입력하세요</td>
        </tr>
        <tr id="sh">
          <td class="text-center"><input type=button value="획인" class="btn btn-sm btn-success" id="ok"></td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>