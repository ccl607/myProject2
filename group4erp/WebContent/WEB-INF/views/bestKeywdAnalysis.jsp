<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file = "/WEB-INF/views/common.jsp" %>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src = "/www.google.com/jsapi"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전략 분석 자료</title>
<script>

	$(document).ready(function() {

		$("#bestKwdAnalysis").hide();
		
		$("[name=rank]").change(function() {
			
			var cnt = $(this).filter(":checked").length;
			var rank='';

			if(cnt==1) {
				//change 이벤트가 발생한 체크박스의 형제들의 체크를 모두 풀기
				$(this).siblings().prop("checked", false);
				rank = $(this).filter(":checked").val();
			} 

			goSearch(rank);
	
		});

		setTableTrBgColor(
				"bestKeywdTable",	//테이블 class 값
				"${headerColor}",			//헤더 tr 배경색
				"${oddTrColor}",		//홀수행 배경색
				"${evenTrColor}",	//짝수행 배경색
				"${mouseOverColor}"			//마우스 온 시 배경색
			);

		$(".pagingNumber").html(
				getPagingNumber(
					"${corpListCnt}"						//검색 결과 총 행 개수
					,"${corpSearchDTO.selectPageNo}"			//선택된 현재 페이지 번호
					,"${corpSearchDTO.rowCntPerPage}"		//페이지 당 출력행의 개수
					,"10"										//페이지 당 보여줄 페이지번호 개수
					,"goSearch();"						//페이지 번호 클릭 후 실행할 자스코드
				)
			);

		//inputData('[name=rowCntPerPage]',"${corpSearchDTO.rowCntPerPage}");
		inputData('[name=selectPageNo]',"${corpSearchDTO.selectPageNo}");
		
	});

	google.charts.load('current', {'packages' : ['corechart'] } );

	google.charts.setOnLoadCallback(drawChart);


	function drawChart() {
		var data = google.visualization.arrayToDataTable(${bestKwd_chart_data});

		var options = {
				title: '일별 키워드 검색 건수',
				width :800, 
				height: 500
		};

		var chart = new google.visualization.LineChart(document.getElementById('bestKwdCntChart'));

		chart.draw(data, options);
	}

	function goSearch(rank) {
		//alert(rank);
		$.ajax({
            url : "/group4erp/searchBestKwd.do"
            , type: "post"
            , data : "rank="+rank
            , dataType:"JSON"
            ,success : function(data){
               	if(data != null){
               		var len = $(".bestKeywdTable tr").length-1;
              
                   	for(var i=1; i<=data.length; i++) {
						$(".bestKeywdTable tr:eq("+i+")").find("td:eq(0)").html(data[i-1].rank);
						$(".bestKeywdTable tr:eq("+i+")").find("td:eq(1)").html(data[i-1].srch_kwd);
						$(".bestKeywdTable tr:eq("+i+")").find("td:eq(2)").html(data[i-1].kwd_cnt);						
                    }

					if(data.length < len) {
						for(var i=len; i>data.length; i--) {
							$(".bestKeywdTable tr:eq("+i+")").remove();
						}
					} else if(data.length >= len){
						//alert("len=="+len+" / "+data.length);
						for(var i=len; i<=data.length; i++ ) {
							$(".bestKeywdTable tr:eq("+(i)+")").after("<tr onClick='viewKwdDailyCnt("+data[i].srch_kwd+");'><td align='center'>"+data[i].rank+"</td>"+
																	"<td align='center'>"+data[i].srch_kwd+"</td>"+
																	"<td align='center'>"+data[i].kwd_cnt+"</td></tr>");
							
						}
					}
               	}
            } 
            , error : function() {
               alert("서버 접속 실패");
            }
         });
	}

	function viewKwdDailyCnt(searchKeyword) {
		alert("test");
	}

	function viewKwdDailyCnt() {
		alert("test2");
	}

	function openBestKwdData() {
		$("#bestKwdAnalysis").show();
	}
	


</script>
</head>
<body><center>
	<h1>[키워드 검색 분석 자료]</h1><br>
	
	<form name="keywordSearchForm" method="post" action="/group4erp/searchKeywordInfo.do">
		<table border="0" cellpadding="5" cellspacing="5" >
			
			<tr>
				<td align="left">[상위순]</td><td><input type="checkbox" name="rank" class="rank" value="10">Best 10 &nbsp;
											<input type="checkbox" name="rank" class="rank" value="15">Best 15 &nbsp;
											<input type="checkbox" name="rank" class="rank" value="20">Best 20 &nbsp;</td>
			</tr>
		</table>
		<input type="hidden" name="selectPageNo">
     	
	</form>
	
	<table name="outerBorderTb" border="0" cellpadding="5" cellspacing="5" height="100%">
		<tr>
			<td valign="top">
				<div id="bestKwdCntChart" style="width: 800px; height: 500px;"> </div>
			</td>
				
			<td valign="top">
				<table class="bestKeywdTable tab" name="bestKeywdTable tab" cellpadding="5" cellspacing="5">
					<th style="cursor:pointer">순위</th><th style="cursor:pointer">키워드</th><th style="cursor:pointer">검색 횟수</th>
						<c:forEach items='${bestKwdDTOList}' var="bestKwdDTOList" varStatus="loopTagStatus">
							<tr onClick="viewKwdDailyCnt('${bestKwdDTOList.srch_kwd}');">
								<td align="center">${bestKwdDTOList.rank}</td>
								<td align="center">${bestKwdDTOList.srch_kwd}</td>
								<td align="center">${bestKwdDTOList.kwd_cnt}</td>				
							</tr>				
						</c:forEach>
				</table>
			</td>
		</tr>		
	</table>

</div>


	
</center>

</body>
</html>