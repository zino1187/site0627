<%@page import="domain.User"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String user=request.getParameter("user");
	String phone = request.getParameter("phone");
	
	User dto = new User();
	dto.setUser(user);
	dto.setPhone(phone);
	//dto 를 전혀 다른 페이지로 가져갈수 있을까??
	//without session, application		
	
	//현재 이 요청에 대해 응답을 실시하지 말고, 서버상에 있는 
	//result.jsp 로 포워딩 시키자!!
	RequestDispatcher dis=null;
	dis=request.getRequestDispatcher("result.jsp");
	
	//요청객체에 원하는 데이터 심어보자!! 
	//request 객체가 죽지 동안에는 데이터를 꺼내 쓸수 있다..
	request.setAttribute("dto", dto);
	
	//포워딩하기!!!
	dis.forward(request , response);
%>







