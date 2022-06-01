<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.request.requestURI}" />
<title>Insert title here</title>
</head>
<body>
	<div>
	<h1>${exercise.name}</h1><br>
	<h2>${exercise.muscleGroup}</h2><br>
	<br><img src="../BildServlet?id=${exercise.id}">
	</div>
	
</body>
</html>