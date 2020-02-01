<!-- JSP 파일이 공통으로 수입해야 할 파일들을 절대경로를 사용해 수입한다.
	그리고 이 페이지를 해당 JSP 파일에서 include 한다. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSP 페이지에서 사용할 사용자정의태그인 JSTL의 C 코어 태그 선언 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- css 파일 & jquery 라이브러리 파일 수입 -->
<link href="/myProject2/resources/common.css" rel="stylesheet" type="text/css">
<script src = "/myProject2/resources/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src = "/myProject2/resources/common.js"></script>
