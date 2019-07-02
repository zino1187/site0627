<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일 업로드</title>
<script>
addEventListener("load", function(){//문서가 로드되면...
	document.querySelector("button").addEventListener("click", function(){
		upload();
	});
}); 
//서버측에 파일 업로드 요청~~
function upload(){
	//form 태그의 속성에서는 enctype 임을 주의!!
	form1.encoding="multipart/form-data";
	
	form1.action="/upload/regist.jsp"; //전송 url 
	form1.method="post";
	form1.submit();
}
</script>
</head>
<body>
	<form name="form1">
		<input type="text" placeholder="파일 제목" name="title"/>
		<br>
		<input type="file" name="myfile">
	</form>
	<button>업로드 실행</button>
</body>
</html>









