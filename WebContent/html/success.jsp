<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css">
<meta charset="UTF-8">
<base href="${pageContext.request.requestURI}" />
<title>Insert title here</title>
</head>
<%@ include file="fragments/noScript.jspf" %>
<body>
	<div>
	<h1>${exercise.name}</h1><br>
	<h2>${exercise.muscleGroup}</h2><br>
	<br><img src="${pageContext.request.contextPath}/BildServlet?id=${exercise.id}">
	</div>
	
</body>
</html>