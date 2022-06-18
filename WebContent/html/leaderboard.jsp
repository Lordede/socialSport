<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>
<meta charset="utf-8">
<meta name="author" content="Hubertus Seitz">
<meta name="description" content="leaderboard">
<meta name="keywords" content="best leader leaderboard">
<base href="${pageContext.request.requestURI}" />
<%@ include file="fragments/authenticationCheckHEAD.jspf"%>
<%@ include file="fragments/navbarHEAD.jspf"%>
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap"
	rel="stylesheet">
<body>

	<%@ include file="fragments/noScript.jspf"%>
	<%@ include file="fragments/authenticationCheck.jspf"%>
	<%@ include file="fragments/navbar.jspf"%>
	<%-- Überprüfung, ob User eingeloggt ist --%>

	<header>
		<!---------->
		<!--Hubertus Seitz--->
		<h1>
			<strong>Leaderboard: </strong>
		</h1>
		<input type="search" name="suche" formaction="get" formmethod="">
	</header>

	<article id="leaderboard">
		<table id="leaderboardTable">
			<tr id="Headrow">
				<th>Punktezahl</th>
				<th>Username</th>
				<th>ID</th>
			</tr>

		</table>
	</article>
	<script>
		window.addEventListener("DOMContentLoaded", init);
		function init() {
			window.addEventListener("load", loadLeaderboard()); //Leaderboard soll mit Seitenaufbau geladen werden

		}
	</script>
	<footer> </footer>
</body>

</html>