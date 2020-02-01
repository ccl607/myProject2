<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지</title>
</head>
<body><center>
	[회원가입]
	
	<form class="joinForm" name="joinForm" id="joinForm" method="post" action="/myProject/joinMemberProc.do">
		
		<table class="joinTb" name="joinTb" id="joinTb">
			<tr>
				<td>아이디</td><td><input type="text" id="user_id" name="user_id" maxlength="15"></td>
		
		</table>
	
	</form>

</center>

</body>
</html>