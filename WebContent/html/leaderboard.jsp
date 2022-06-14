<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<title>Dashboard</title>
		<meta charset="utf-8">
		<meta name="author" content="Cem Durmus">
		<meta name="description" content="leaderboard">
		<base href="${pageContext.request.requestURI}" />
		<link rel="stylesheet" href="../css/navbar.css">
		<script src="../JavaScript/Leaderboard.js"></script>
		<!-- Basis für relative Verweise -> Basis = Speicherort -->
	</head>

	<body>
		<%@ include file="fragments/authenticationCheck.jspf" %>
			<%-- �berpr�fung, ob User eingeloggt ist --%>
				<!--Lukas Edmüller
        Navbar 
        https://www.w3schools.com/Css/css_navbar_horizontal.asp -->
				<nav>
					<ul>
						<li><a href="./dashboard.jsp">Dashboard</a></li>
						<li><a href="./trainingsmenu.jsp">Training</a></li>
						<li><a class="active" href="./leaderboard.jsp">Leaderboard</a></li>
						<li><a href="./accountSetting.jsp">Account Settings</a></li>
						<li><a href="./exercise.jsp">Übung erstellen</a></li>
						<li class="logout"><a class="active" href="#about">Logout</a></li>
					</ul>
				</nav>
				<header>
					<!---------->
					<h1>
						<strong>Leaderboard: </strong>
					</h1>
					<input type="search" name="suche" formaction="get" formmethod="">
				</header>

				<article id="leaderboard">
					<table id="leaderboardTable">
						<tr id="Headrow">
							<th>Punktezahl</th>
							<th>Name</th>
							<th>ID</th>
						</tr>

					</table>
				</article>
				<script>

					window.addEventListener("DOMContentLoaded", init);
					function init() {
						window.addEventListener("load", loadLeaderboard());	//Leaderboard soll mit Seitenaufbau geladen werden

					}



				</script>
				<footer> </footer>
	</body>

	</html>