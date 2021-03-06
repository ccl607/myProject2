<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file = "/WEB-INF/views/common.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 행사 신청 결재</title>
<script>
	$(document).ready(function() {
		
	});
	
	function responseApproval() {
		alert( $('[name=eventApprovalDoc]').serialize());
		$.ajax({
			url : "/group4erp/updateEventApproavalProc.do",				//호출할 서버쪽 URL 주소 설정
			type : "post",										//전송 방법 설정
			data : $('[name=eventApprovalDoc]').serialize(),		//서버로 보낼 파라미터명과 파라미터값을 설정
			
			success : function(upCnt) {
				if(upCnt==1) {
					alert("결재 성공!");
					
					location.replace("/group4erp/viewApprovalList.do");
				} else if(delCnt==-1) {	
					//alert("업체가 이미 삭제되었습니다!");
					
					location.replace("/group4erp/viewApprovalList.do");

				} else {
					alert("서버쪽 DB 연동 실패!");
				}
			}

			//서버의 응답을 못 받았을 경우 실행할 익명함수 설정
			, error : function() {		//서버의 응답을 못받았을 경우 실행할 익명함수 설정
				alert("서버 접속 실패!");
			}	
		});
		
	}

</script>


</head>
<body><center>	
	<form name="eventApprovalDoc" method="post" action="/group4erp/updateEventApproavalProc.do">
		<table name="outerLineTable tab" cellpadding="5" cellspacing="5" width="700" align="center">
		
		<tr>
			<td align="right" ><table name="jikup" border="1" cellpadding="5" cellspacing="0"><tr><td  width="90" align="center">기안자 </td><td  width="90" align="center">부서장</td><td width="90" align="center">대표이사 </td> </tr> 
														<tr><td align="center">${approvalInfoList.emp_name}</td><td align="center">
																												
																												<% String mgr_no = (String)session.getAttribute("mgr_emp_no"); 
																													String emp = (String)session.getAttribute("emp_id");
																													System.out.println("mgr_no==="+mgr_no);
																													String ceo_no = (String)session.getAttribute("ceo_no");
																													System.out.println("ceo_no==="+ceo_no);
																												if(mgr_no != null) { %>
																													<input type="radio" name="approvalYn" value="7">승인 &nbsp;
																													<input type="radio" name="approvalYn" value="6">반려 &nbsp;
																													<input type="hidden" name="mgr_emp" value='<%=emp %>'>
																												<% } else if(mgr_no==null && ceo_no!=null) { %>
																													
																													
																													<label>${approvalInfoList.mgr_name}</label>
																												
																												<% } %>
																												
																											</td>
																											
																											<td>
																											
																											<% if(mgr_no == null) { %>
																											
																												<input type="radio" name="approvalYn" value="5">승인 &nbsp;
																												<input type="radio" name="approvalYn" value="6">반려 &nbsp; 
																											<% } %>
																											</td>
																											
																											</tr></table>						
																											
		</tr>
			
		<tr>
			<table class="documentTable tab" name="documentTable" cellpadding=5" cellspacing="5" width="600">		
				<tr>
					<td width="100">신청번호</td><td colspan="3">${approvalInfoList.evnt_no}</td>
					<input type="hidden" name="document_no" value="${approvalInfoList.evnt_no}">
				</tr>
				<tr>
					<td width="100">기안자</td><td>직급 : &nbsp;${approvalInfoList.jikup}</td><td width="50" >성명 : ${approvalInfoList.emp_name}</td>
				</tr>
				<tr>
					<td width="100">일시</td><td colspan="3">${approvalInfoList.reg_dt} </td>
				</tr>
				<tr>
					<td width="100">안건 주제</td ><td colspan="3">이벤트 행사 신청</td>
				</tr>
				<tr>
					<td width="100" rowspan="3">내용</td><td width="100" >이벤트 타이틀</td><td colspan="2">${approvalInfoList.evnt_title}</td>
				</tr>
				
				<tr>
					<td>진행 일시</td><td colspan="2">${approvalInfoList.evnt_start_dt} ~ ${approvalInfoList.evnt_end_dt}</td>
				</tr>
				<tr>
					<td width="100">첨부파일</td><td colspan="3">${approvalInfoList.atchd_data}</td>
				</tr>
				<tr>
					<td width="100">비고</td><td colspan="3">${approvalInfoList.evnt_comment}</td>
				</tr>
				<tr>
					<td width="100">메모</td><td colspan="3"><textarea name="e_work_comment" cols="40" rows="10" placeholder="반려할 시 사유를 기입해주세요."></textarea></td>
				</tr>				
			</table><br>
			<h4>위와 같이 진행할 예정이니 검토 부탁드립니다.</h4>
			<input type="button" value="결재" onClick="responseApproval();"> &nbsp;
			<input type="reset" value="초기화">
		</tr>	
	</table>
	<br><br>
	

	</form>
	
</center>

</body>
</html>