<%@page import="shop.Member"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	String user="scott";
	String pw="1234";
%>
<%
	request.setCharacterEncoding("utf-8");
	//클라이언트가 전송한 데이터를 이용하여 로그인 처리!!!
	String uname=request.getParameter("uname");
	String psw=request.getParameter("psw");
	
	if(uname.equals(user) && pw.equals(psw)){
		out.print("회원입니다");
		
		Member member = new Member();
		member.setUname(uname);
		member.setPsw(psw);
		
		//세션에 담아두기!!
		session.setAttribute("member", member);
		
		//브라우저로 하여금, 지정한 url 로 재접속 유도!!
		response.sendRedirect("/scope/a.jsp");
	}else{
		out.print("정보가 일치하지 않습니다");
	}
%>







