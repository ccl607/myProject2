<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String uid = request.getParameter("uid");
	String pwd = request.getParameter("pwd");
	
	try {
		if( uid.equals("abc") && pwd.equals("123") ) {
			out.print("<script>alert('로그인 성공');</script>");
		} else {
			out.print("<script>alert('로그인 실패'); location.replace('loginForm.jsp'); </script>");
		}
	} catch(Exception e) {
		out.print("데이터가 없습니다.");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<center>웰컴...<%=uid %>님 </center>
<a href="/views/loginForm.jsp">[로그인 화면으로 이동]</a>

</body>
</html>