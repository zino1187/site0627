<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
//클라이언트 스크립트 언어 (실행이 클라이언트 측에 일어남..)
<%
function test(){
	alert("dsfdf");
}
%>
test();
var user="scott";
var pass="1234";
alert(user);
</script>
</head>
<body>
<% 
	int k=7;
%>
<%
	for(int i=1;i<=9;i++){
		out.print("2 * "+i+" = "+(2*i)+" <br>");
	}
%>
</body>
</html>







