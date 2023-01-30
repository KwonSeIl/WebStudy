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
	width: 550px;
	margin: 0px auto;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<!-- 
	JAVA / ORACLE / JSP(MVC) = Spring
	         |
	        MyBatis
	 JavaScript: 변수 설정(let,const), 연산자, 제어문, function
	 			 ---------------------------------------- 라이브러리(핵심: jquery(ajax))
	 
	 = JSP(MVC) = Jquery(Ajax) => DBCP
	 = --------   ------------    ----
	      |            |           |
	     Spring	   VueJS		MyBatis(80%)
	      |			   |		   |
	     Spring-Boot  ReactJS	 JPA (20%)
	     ---------------------------------------- AWS(리눅스)
-->
<script type="text/javascript">
$(function(){
	$('#postfindBtn').click(function(){
		let dong=$('#dong').val();
		if(dong.trim()==="")
		{
			$('#dong').focus()
			return
		}
		$.ajax({
			type:'post',
			url:'../member/postfind_result.do', //결과값 실행 위치
			data:{"dong":dong}, //실행에 필요한 데이터 전송
			success:function(result) //실행결과를 읽어온다
			{
				$('#print').html(result) //읽어온 데이터 출력위치 지정
			}
		})
	})
})
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <table class="table">
        <tr>
          <td class="text-center">
            입력:<input type="text" name="dong" id="dong" size=20 class="input-sm">
            <input type="button" value="우편번호검색" class="btn btn-sm btn-danger" id="postfindBtn">
          </td>
        </tr>
      </table>
      <span id="print">
        
      </span>
    </div>
  </div>
</body>
</html>