<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file = "/WEB-INF/views/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지</title>
<script src = "http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	
	$(document).ready(function() {
		
		$("#postCodeBtn").click(function() {
			//alert("우편번호 검색");
			selectPostCode();
		});
		
		$("#joinProcBtn").click(function() {
			//alert("우편번호 검색");
			checkFormAndJoin();
		});
		
		$("#cancelProcBtn").click(function() {
			//alert("취소 검색");
			$("#joinForm")[0].reset();
		});
		
		
		
	});
	
	function selectPostCode() {
		
		new daum.Postcode({
			oncomplete: function(data) {
				
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('user_addr').value = data.zonecode;
                document.getElementById('user_addr2').value = roadAddr;
                
               
			}
		}).open();
	}
	

</script>


</head>
<body><center>
	<br><br><h1>[회원가입]</h1><br>
	
	<form class="joinForm" name="joinForm" id="joinForm" method="post" action="/myProject/joinMemberProc.do">
		
		<table class="joinTb" name="joinTb" id="joinTb">
			<tr>
				<td>아이디</td><td colspan="2"><input type="text" id="user_id" name="user_id" maxlength="15"></td>
			</tr>
			<tr>
				<td>비밀번호</td><td><input type="text" id="user_id" name="user_id" maxlength="15"></td><td><label id="checkPwd"></label></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td><td colspan="2"><input type="text" id="pwd_check" name="pwd_check" maxlength="15"></td>
			</tr>
			<tr>
				<td>주소</td><td colspan="2"><input type="text" id="user_addr" name="user_addr">&nbsp;<input type="button" id="postCodeBtn" name="postCodeBtn" value="우편번호 검색"></td>
			</tr>
			<tr>
				<td>상세주소</td><td colspan="2"><input type="text" id="user_addr2" name="user_addr2">&nbsp;</td>
			</tr>
			<tr>
				<td>생년월일</td><td colspan="2"><select>
												<option value="1970">1970</option>
												</select>년
												&nbsp;
												<select>
													<option value="01">01</option>
												</select>월
												&nbsp;
												<select>
													<option value="01">01</option>
												</select>일
												</td>
			</tr>
			<tr>
				<td>성별</td><td colspan="2"><input type="radio" id="user_gender" name="user_gender" value="01">남&nbsp;
											<input type="radio" id="user_gender" name="user_gender" value="02">녀
							</td>
			</tr>
			<!-- <tr>
				<td>연락처</td><td colspan="2"><input type="text" id="user_phone" name="user_phone" maxlength="13"></td>
			</tr> -->
		
		</table>
		<br><br><br>
		<button id="joinProcBtn" name="joinProcBtn">회원가입</button>&nbsp;
		<button id="cancelProcBtn" name="cancelProcBtn">취소</button>&nbsp;
		<button id="goBackBtn" name="goBackBtn">돌아가기</button>
	</form>

</center>

</body>
</html>