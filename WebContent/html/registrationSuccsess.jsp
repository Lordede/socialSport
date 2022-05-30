<



<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.requestURI}" /> <!-- Basis für relative Verweise -> Basis = Speicherort -->
<meta charset="utf-8">
<title>Registriervorgang erfolgreich!</title>
</head>
<body>
<%@ include file="fragments/authenticationCheck.jspf" %> <%-- Überprüfung, ob User eingeloggt ist --%>
	<h1>Der Registriervorgang war erfolgreich!</h1>
	<main>
		<ul>
			<li>UserID:  	${form.id}		</li> <!-- TODO: Fix Bug: Gibt immer 0 aus? -->
			<li>e-Mail: 	${form.eMail}	</li>
			<li>Username: 	${form.username}</li>
			<li>Vorname: 	${form.firstName}</li>
			<li>Nachname:	${form.lastName}</li>
		</ul>
	</main>
	<nav>
		<ul>
			<li><a href="../html/login.html"> Zur&uuml;ck zum Login</a></li>
			
		</ul>
	</nav>
</body>
</html>