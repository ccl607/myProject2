<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신간리스트</title>
</head>
<body>
	<h1>[신간리스트]</h1>
	
	<form name="newBooksListForm" method="post" action="/group4erp/viewBookInfo.do">
		<table class="tbcss1" name="bookList" cellpadding="5" cellspacing="5" width="500">		
			<tr>
				<td rowspan="5" width="148" height="152">표지추가</td>
				<th>isbn</th><td>909900909</td>
			</tr>
			<tr>
				<th>제목</th><td>도서샘플리스트</td>
			</tr>
			<tr>
				<th>저자</th><td>손오공</td>
			</tr>
			<tr>
				<th>출판사</th><td>삼장법사</td>
			</tr>
			<tr>
				<th>출간일</th><td>2019.12.23</td>
			</tr>
			
			<tr>
				<td rowspan="5" width="148" height="152">표지추가</td>
				<th>isbn</th><td>90991111222</td>
			</tr>
			<tr>
				<th>제목</th><td>팩트풀니스</td>
			</tr>
			<tr>
				<th>저자</th><td>***</td>
			</tr>
			<tr>
				<th>출판사</th><td>김영사</td>
			</tr>
			<tr>
				<th>출간일</th><td>2019.09.25</td>
			</tr>
		</table>
	</form>
</body>
</html>