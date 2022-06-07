<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<base href="${pageContext.request.requestURI}" />
<!-- Basis f�r relative Verweise -> Basis = Speicherort -->
<title>Insert title here</title>
</head>
<body>
	<%@ include file="fragments/authenticationCheck.jspf"%>
	<%-- �berpr�fung, ob User eingeloggt ist --%>
	<h1>Schade dass Sie uns verlassen haben</h1>
</body>
</html>