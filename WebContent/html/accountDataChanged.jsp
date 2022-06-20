<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="author" content="Cem Durmus" />
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap" rel="stylesheet">
 <link rel="stylesheet" href="../css/layout.css">
<!-- Basis f�r relative Verweise -> Basis = Speicherort -->
<base href="${pageContext.request.requestURI}" />
<title>Insert title here</title>
</head>
<body>
<%@ include file="fragments/noScript.jspf" %>
<%@ include file="fragments/authenticationCheck.jspf" %> <%-- Überprüfung, ob User eingeloggt ist --%>
	<header>
		<h1>Profil Bearbeiten</h1>
	</header>
	<article>
		<h2>Bearbeiten Sie Ihr Profil hier</h2>
		<div class="userName">
			<form action="User_Servlet" method="post">
				<p>Benutzername:</p>
				${userData.username}
			</form>
		</div>
		<div>
			<form action="User_Servlet" method="post">
				<p>Vorname: ${userData.firstName}</p>
				<p>Nachname: ${userData.lastName}</p>
			</form>
		</div>
		<div>
			<form action="User_Servlet" method="post">
				<p>Passwort:</p>
			</form>
		</div>
		<div>
			<form action="User_Servlet" method="post">
				<p>E-Mail: ${userData.eMail}</p>
			</form>
		</div>
		<div>
			<a href="./accountSetting.jsp">Weitere Daten ändern</a>
		</div>
	</article>
	<footer>
		<!--Generische Navigationsleiste-->
	</footer>
</body>
</html>