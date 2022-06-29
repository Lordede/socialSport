<%-- Author: Hubertus Seitz--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.requestURI}" />
<meta charset="utf-8">
<title>Kontakte</title>
<link rel="stylesheet" href="../css/contacts.css">
<%@ include file="fragments/footerHEAD.jspf"%>
</head>
<body>

	<header>
		<h1>Wenn Sie fragen oder Anregungeng zur Anwendung haben,
			schreiben Sie gerne einem von uns!</h1>
	</header>

	<main>


		<p>
			<a href="../index.jsp">Zurück zur Startseite</a>

		</P>
		<p>
			<a href="dashboard.jsp">Zurück zum Dashboard</a>
		</P>

		<div id="flexarea">
		<article>
			<div class="personalInfo">
				<h2>Cem Durmus</h2>
				<p>
					E-Mail: <a href="mailto:ced5742@thi.de">ced5742@thi.de</a>
				</p>
			</div>
			<img src="../img/Utilities/ExampleAvatar.png" width="100"
				height="100" alt="ExampleAvatar">

		</article>

		<article>
			<div class="personalInfo">
				<h2>Lukas Edmüller</h2>
				<p>
					E-Mail: <a href="mailto:lue0558@thi.de">lue0558@thi.de</a>
				</p>
			</div>
			<img src="../img/Utilities/ExampleAvatar.png" width="100"
				height="100" alt="ExampleAvatar">

		</article>

		<article>
			<div class="personalInfo">
				<h2>Hubertus Seitz</h2>
				<p>
					E-Mail: <a href="mailto:hus4725@thi.de">hus4725@thi.de</a>
				</p>
			</div>
			<img src="../img/Utilities/ExampleAvatar.png" width="100"
				height="100" alt="ExampleAvatar">

		</article>
	</div>

	</main>
	<!--Footer-->
	<%@ include file="fragments/footer.jspf"%>

</body>
</html>