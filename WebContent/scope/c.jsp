<%@page import="shop.Member"%>
<%@page import="shop.Cart"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//브라우저를 닫지 않고 온 클라이언트라면, 계속 해당 세션을
	//참조할 수 있다 .따라서 우리가 담아 놓은 cart 객체를 꺼내서 사용
	Cart cart=(Cart)session.getAttribute("cart");
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
</head>
<body bgcolor="green">
장바구니에 담겨진 제품은 <%=cart.getName() %><br>
갯수는 <%=cart.getEa() %><br>
가격은 <%=cart.getPrice() %><br>
</body>
</html>




