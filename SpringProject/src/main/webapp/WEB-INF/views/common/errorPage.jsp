<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <jsp:include page="menubar.jsp"/> 태그를 못가져오기 때문에 --%>
	<%@include file="menubar.jsp" %>
	<h1 align="center">에러 페이지: ${errorMsg }</h1>
	<jsp:include page="footer.jsp"/>
</body>
</html>