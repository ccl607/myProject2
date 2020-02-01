<!-- JSP 기술의 한 종류인 페이지 디렉티브(Page Directive)를 이용하여 현 JSP 페이지 처리 방식 선언하기 -->
<!-- 현재 이 JSP 페이지 실행 후 생성되는 문서는 HTML이고, 이 문서는 UTF-8 방식으로 인코딩한다고 설정함 -->
<!-- UTF-8 인코딩 방식은 한글을 포함한 전 세계 모든 문자열을 부호화할 수 있는 방법이다. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script language = "JavaScript">

	//body 태그 안의 소스를 모두 실행한 후에 실행할 자바스크립트 코드 설정
	$(document).ready(function() {
		
		//name=loginForm 가진 폼 태그 안의 class=login 가진 태그에
		//click 이벤트가 발생할 때 실행할 코드 설정
		$("[name=loginForm] .login").click(function() {
			
			checkLoginForm();
		});
		
		$("#joinMember").click(function() {
			//alert("test");
			location.href="/myProject2/goJoinMemberPage.do";
		});
		
	});
	
	//로그인 양식 유효성 체크 함수
	function checkLoginForm() {

		//입력된 아이디를 가져와 변수에 저장
		/*
		var admin_id = $('.admin_id').val();
		
		//아이디를 입력하지 않았거나 공백이 있으면 아이디 입력란을 비우고 경고한 후 함수 중단
		if(admin_id.split(" ").join("")=="" ) {
			alert("관리자 아이디 입력 요망");
			$('.admin_id').val("");
			return;	
		}
				
		var pwd = $('.pwd').val();			//입력된 암호를 가져와 변수에 저장
		
		if(pwd.split(" ").join("")=="" ) {		//암호를 입력하지 않았거나 공백이 있으면 암호 입력란을 비우고 경고한 후 함수 중단
			alert("관리자 암호 입력 요망");
			$('.pwd').val("");
			return;	
		} */
		
		if(is_empty( ".admin_id" ) ) {
			alert("관리자 아이디 입력 요망");
			$('.admin_id').val("");
			return;
		}
		
		if(is_empty(".pwd")) {
			alert("관리자 암호 입력 요망");
			$('.pwd').val("");
			return;
		}
		
		//alert(admin_id + " / " +pwd);
		//alert( $("[name=loginForm]").serialize() );
		//return;
		
		//현재 화면에서 페이지 이동 없이(=비동기 방식으로)
		//서버쪽 loginProc.do로 접속하여 아이디, 암호의 존재 개수 얻기
		
		$.ajax({			
			url : "/z_spring/loginProc.do"	//서버 쪽 호출 url 주소 지정
			, type : "post"					//form 태그 안의 데이터 즉, 파라미터값을 보내는 방법 지정
			, data : $("[name=loginForm]").serialize()	//서버로 보낼 파라미터명과 파라미터값을 설정
														//, data:"admin_id="+admin_id+"&pwd="+pwd 도 가능
														//, data : {'admin_id':admin_id, 'pwd':pwd}	//JSON 형식
			, success : function(data) {	//서버의 응답을 성공적으로 받았을 경우 실행할 익명함수 설정. 익명함수의 매개변수 data에는 서버가 응답한 데이터가 들어온다.
											//현재 data 매개변수에는 아이디, 암호의 존재 개수가 들어온다.
				if(data==1) {				//아이디 존재 개수가 1이면 /z_spring/boardList.do로 이동
					alert("로그인 성공");
					location.replace("/z_spring/boardListForm.do");
					//location.replace("/z_spring/boardListForm.do");
				} else if(data==0) {		//아이디 존재 개수가 0이면 경고
					alert("아이디, 암호가 존재하지 않습니다! 재입력 바람");
				} else {
					alert("서버 오류 발생! 관리자에게 문의 바람!");
				}				
			}
			
			, error : function() {		//서버의 응답을 못받았을 경우 실행할 익명함수 설정
				alert("서버 접속 실패!");
			}			
		});
	}

</script>
<title>로그인 페이지</title>
</head>
<body><center><br><br><br><br><br><br>
	<h1>마이 쇼핑몰 프로젝트</h1>
	<form name="loginForm" method="post" action = "/z_spring/loginProc.do">
		<table class="tbcss1" border=1 cellpadding=5>
			<b>[로그인]</b>
			<div style="height:6"></div>
			<tr>
				<th bgcolor="gray">아이디</th>
				<td><input type="text" name="admin_id" class="admin_id" size="20">
			</tr>
			<tr>
				<th bgcolor="gray">암호</th>
				<td><input type="password" name="pwd" class="pwd" size="20">
			</tr>
		</table>
		<div style="height:6"></div>
		<table><tr>
			<td><input type="button" value="로그인" class="login" >
			<td><input type="checkbox" name="is_login" value="y">아이디, 암호 기억
		</table>
		<br>
		<div id="joinMember">[아직 회원이 아니신가요?]</div>
	</form>
	
</body>
</html>