<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.requestURI}" /> <!-- Basis für relative Verweise -> Basis = Speicherort -->
<meta charset="utf-8">
<title>Registriervorgang erfolgreich!</title>
<link rel="stylesheet" href="../css/registrationSuccsess.css" />
</head>
<body>
<%@ include file="fragments/noScript.jspf" %>
<!-- HIER DARF NICHT AUF LOGIN ÜBERPRÜFT WERDEN! -->
	<header><h1>Der Registriervorgang war erfolgreich!</h1></header>
	<main class="center">
		<ul id="list">
			<li>UserID:  	${form.id}		</li>
			<li>e-Mail: 	${form.eMail}	</li>
			<li>Username: 	${form.username}</li>
			<li>Vorname: 	${form.firstName}</li>
			<li>Nachname:	${form.lastName}</li>
		</ul>
	</main>
	<nav class="center">
		<ul class ="ulnav">
			<li><a href="../html/login.html"> Zur&uuml;ck zum Login</a></li>
			
		</ul>
	</nav>
</body>
</html>