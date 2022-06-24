<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<title>Leaderboard</title>
<meta charset="utf-8">
<meta name="author" content="Hubertus Seitz">
<meta name="description" content="leaderboard">
<base href="${pageContext.request.requestURI}" />
<%@ include file="fragments/authenticationCheckHEAD.jspf"%>
<%@ include file="fragments/navbarHEAD.jspf"%>
<%@ include file="fragments/footerHEAD.jspf"%>
<script src="../JavaScript/Leaderboard.js"></script>
<link rel="stylesheet" href="../css/leaderboard.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap"
	rel="stylesheet" />

</head>
<body>

	<%@ include file="fragments/noScript.jspf"%>
	<%@ include file="fragments/authenticationCheck.jspf"%>
	<%@ include file="fragments/navbar.jspf"%>

	<header>
		<!---------->
		<!--Hubertus Seitz--->
		<h1>
			<strong>Leaderboard: </strong>
		</h1>
		<%-- wieder sichtbar machen, sobald es benötigt wird --%>
		<%-- <input type="search" name="suche" formaction="get" formmethod=""
							placeholder="Nach Username suchen..." autofocus>  --%>
	</header>

	<main>
		<article id="leaderboard">
			<table id="leaderboardTable">
				<tr id="Headrow">
					<th>Punktezahl</th>
					<th>Username</th>
					<th>ID</th>
				</tr>

			</table>
		</article>
		<div id="Arnold">
			<div class="pictureWrapper">
				<img 	src="../img/ArnoldSchwarzenegger.jpg"
						alt="ArnoldSchwarzenegger" height="200" width="333"
						id="ArnoldPicture">
			</div>
			<p id="quote">»Aufgeben ist keine Option. Jeder hat erfolgreich
				zu sein.«</p>
			<p class="pictureWrapper">~Arnold Schwarzenegger</p>
		</div>
	</main>
	<%@ include file="fragments/footer.jspf"%>
	<script>
		window.addEventListener("DOMContentLoaded", init);
		function init() {
			window.addEventListener("load", loadLeaderboard()); //Leaderboard soll mit Seitenaufbau geladen werden

		}
	</script>


</body>

</html>