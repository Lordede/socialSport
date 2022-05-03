<



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
			<li>UserID:  	${form.id}		</li> <!-- TODO: Fix Bug: Gibt immer 0 aus? -->
			<li>e-Mail: 	${form.eMail}	</li>
			<li>Username: 	${form.userName}</li>
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