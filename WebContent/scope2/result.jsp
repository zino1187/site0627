<%@page import="domain.User"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="pink">
최종적인 결과 출력 <p>
<%
	User dto=(User)request.getAttribute("dto");
	out.print(dto.getUser());
	out.print("<br>");
	out.print(dto.getPhone());
%>

</body>
</html>



