<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/layout.css">
<meta charset="UTF-8">
<base href="${pageContext.request.requestURI}" />
<%@ include file="fragments/footerHEAD.jspf"%>
<!-- Basis für relative Verweise -> Basis = Speicherort -->
<title>Insert title here</title>
</head>
<body>
	<%@ include file="fragments/authenticationCheck.jspf"%>
	<%@ include file="fragments/noScript.jspf" %>
	<%-- Überprüfung, ob User eingeloggt ist --%>
	<h1>Schade dass Sie uns verlassen haben, wir hoffen doch, dass sie zurückkehren</h1>
	<%@ include file="fragments/footer.jspf"%>
</body>
</html>