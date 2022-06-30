
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Cem Durmus">
<base href="${pageContext.request.requestURI}" />
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/layout.css">
</head>
<body>
	<%@ include file="fragments/noScript.jspf"%>
	<div>
		<h1>${exercise.name}</h1>
		<br>
		<h2>${exercise.muscleGroup}</h2>
		<br> <br> <img
			src="${pageContext.request.contextPath}/BildServlet?id=${exercise.id}"
			alt="Bild der Übung" width="180" height="180">
	</div>

	<form>
		<button formaction="administrationInterface.jsp">Zurück zur Adminverwaltung</button>
	</form>
</body>
</html>

