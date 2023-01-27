<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	1. selector => CSS에 나오는 내용 => $('tag_name') $('#id명') $('.클래스명') $(내장객체) $(function)
																			|
																		this => 행위를 한 태그 자신
																		$(this)
									자바스크립트
										|
									------------
										window
										  |
								-------------------------
								|		|		|		|
							document  location  history  screen
							
							window: open(팝업창) => 아이디 중복 체크, 우편번호 검색, close()
							document: write, querySelector()...
							location: href => 이동
							history: back() => 이전, forward() => 다음
	2. jquery 함수
	  = 태그의 값 읽기(getter)
	    태그와 태그 사이의 값 => text(), html()
	    <h1>
	      <span>Hello JavaScript</span>
	    </h1>
	    
	    $('h1').text() => Hello JavaScript1 Hello JavaScript2
	    $('h1').html() => <span>Hello JavaScript1</span>
	    				  <span>Hello JavaScript2</span>
	    태그의 속성값 		=> attr("속성명")
	    <img src="값">
	    $('img').attr("src") => 값
	    
	    입력창의 값을 가지고 올 때
	    <input type="text" id="id" value="admin"> => val() (input,select,textarea)
	    $('#id').val() ==> admin
	  = 태그의 값을 설정(setter)
	    <span><span> ==> $('span').text("Hello") => <span>Hello<span>
	    <span><span> ==> $('span').texst("<font color=red>Hello</font>")
	      <span><font color=red>Hello</font><span>
	      		---------------------------- 문자열 처리
	      $('span').html("<font color=red>Hello</font>")
	  	---------------------------------------------------------------- setter: 값을 한번만 
	  = 상태
	    $(selector).focus()
	    $(selector).hide()
	    $(selector).show()
	    $(selector).disable()
	3. 이벤트 종류
	4. 효과
	5. Ajax
 --%>
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
	width: 700px;
	margin: 0px auto;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let fileIndex=0 //전역변수
$(function(){
	$('h1').text("자료실 글쓰기")
	$('#addBtn').click(function(){
		//처리
		$('#user-table > tbody').append(
			'<tr id="m'+fileIndex+'">'
			+'<td width=20%>파일 '+(fileIndex+1)+'</td>'
			+'<td width=80%><input type=file size=20 class="input-sm"></td>'
			+'</tr>'
		)
		fileIndex++;
	})
	$('#delBtn').on('click',function(){
		if(fileIndex>0)
		{
			$('#m'+(fileIndex-1)).remove();
			fileIndex--;
		}
	})
	$('#writeBtn').click(function(){
		let name=$('#name').val();
		let subject=$('#subject').val();
		let content=$('#content').val();
		let pwd=$('#pwd').val();
		let msg="이름:"+name+"\n"
			+"제목:"+subject+"\n"
			+"내용:"+content+"\n"
			+"비밀번호:"+pwd
		alert(msg)
	})
})
</script>
</head>
<body>
  <!-- text, val, append  -->
  <div class="container">
    <h1 class="text-center"></h1>
    <div class="row">
      <table class="table">
        <tr>
          <th width="20%" class="text-rigth success">이름</th>
          <td width="80%">
            <input type="text" id=name size=15 class="input-sm">
          </td> 
        </tr>
        <tr>
          <th width="20%" class="text-rigth success">제목</th>
          <td width="80%">
            <input type="text" id=subject size=50 class="input-sm">
          </td> 
        </tr>
        <tr>
          <th width="20%" class="text-rigth success">내용</th>
          <td width="80%">
            <textarea rows="10" cols="50" id=content></textarea>
          </td> 
        </tr>
        <tr>
          <th width="20%" class="text-rigth success">첨부파일</th>
          <td width="80%">
            <table class="table">
              <tr>
                <td class="text-right">
                  <%-- id, class	=> css,javascript제어, name	=> 자바에서 값을 받을 때 사용  --%>
                  <input type="button" class="btn btn-xs btn-danger" id="addBtn" value="Add">
                  <input type="button" class="btn btn-xs btn-primary" value="Delete" id="delBtn">
                </td>
              </tr>
            </table>
            <table class="table" id="user-table">
              <tbody>
              
              </tbody>
            </table>
          </td>  
        </tr>
        <tr>
          <th width="20%" class="text-rigth success">비밀번호</th>
          <td width="80%">
            <input type="password" id=pwd size=10 class="input-sm">
          </td> 
        </tr>
        <tr>
          <td colspan="2" class="text-center">
            <input type="button" class="btn btn-sm btn-success" value="글쓰기" id=writeBtn> 
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>