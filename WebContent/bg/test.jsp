<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//클라이언트가 전송한 파라미터 받기!!
	//파라미터는 언제나 자료형이 스트링
	String bg=request.getParameter("bg"); //red, blue
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

function setBg(){
	form1.action="test.jsp";
	form1.submit();
}
</script>
</head>
<body bgcolor="<%=bg%>">
	<form name="form1">
		<select name="bg">
			<option value="red">빨간색</option>
			<option value="blue">파란색</option>
			<option value="green">초록색</option>
		</select>
	<form>
	<button onClick="setBg()">색상변경</button>
</body>
</html>




