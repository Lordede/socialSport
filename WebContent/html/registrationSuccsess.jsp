<
<!-- 
*
*	TODO:	Zu JSP Konvertieren, bei der die übertragenen Daten (bis auf Passwort) angezeigt werden
*
* -->


<!DOCTYPE html>
<html>
<head>
<!-- Basis für relative Verweise -> Basis = Speicherort -->
<base href="${pageContext.request.requestURI}" />
<meta charset="utf-8">
<title>Registriervorgang erfolgreich!</title>
</head>
<body>
	<h1>Der Registriervorgang war erfolgreich!</h1>
	<main>
		<ul>
			<li>UserID: ${sessionScope.email} </li>
			<li>eMail:</li>
			<li>Username:</li>
			<li>Vorname:</li>
			<li>Nachname:</li>
		</ul>
	</main>
	<nav>
		<ul>
			<li><a href="../html/login.html"> Zur&uuml;ck zum Login</a></li>
			<!-- TODO: Link muss nach konvertierung zu jsp angebpasst werden  -->
		</ul>
	</nav>
</body>
</html>