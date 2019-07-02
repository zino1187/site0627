<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//클라이언트가 전송한 dan 변수 받기!!
	String dan=request.getParameter("dan");
	if(dan == null){ //널처리~~
		dan="2";
	}
	out.print("넘겨받은 dan 은 "+dan);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
window.addEventListener("load", function(){
	var sel=document.querySelector("select");
	
	sel.addEventListener("change", function(){
		if(this.value !="0"){ //0이 아니면...
			send();
		}else{
			alert("단을 선택하세요!!");
		}
	});
});

//서버에 전송!!
function send(){
	var form=document.querySelector("form");
	form.action="list.jsp";
	form.method="get";//디폴트는 get  vs  post
	form.submit();//전송!!
}
</script>
</head>
<body>
<form>
	<select name="dan">
		<option value="0">단 선택</option>
		<%for(int i=2;i<=9;i++){ %>
		<option  <%if(i==Integer.parseInt(dan)){%>selected<%}%>   value="<%=i%>"><%=i%>단 선택</option>
		<%} %>
	</select>
</form>
<p>
<%
	for(int i=1;i<=9;i++){
		out.print(dan+" * "+i+" = "+(Integer.parseInt(dan)*i)+" <br>");
	}
%>
</body>
</html>




