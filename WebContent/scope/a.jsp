<%@page import="shop.Member"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//정보를 담고 유지할 수 있는 내장객체는 ??
	//request <  session < application			
%>
<%
	Member member=(Member)session.getAttribute("member");
%>
<%=member.getUname() %> 님 로그인 중
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function send(){
	var form=document.querySelector("form");
	form.action="b.jsp";
	form.method="post";
	form.submit();
}
</script>
</head>
<body>
	<form>
		<input type="text" name="name" 	placeholder="제품명"/>
		<input type="text" name="ea" 		placeholder="갯수"/>
		<input type="text" name="price" 	placeholder="가격"/>
	</form>
	<button onClick="send()">전송</button>
</body>
</html>






