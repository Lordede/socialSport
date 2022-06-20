<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--Um hier reinzukommen erst Passwort abfrage-->
<html lang="de">
<head>
<base href="${pageContext.request.requestURI}" />
<!-- Basis f�r relative Verweise -> Basis = Speicherort -->
<title>Profil Bearbeiten</title>
<meta charset="utf-8">
<meta name="description" content="Ihre one stop Sport Plattform">
<meta name="keywords" content="Benutzerkonto bearbeiten">
<meta name="author" content="Cem Durmus">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="../css/layout.css">
<link rel="stylesheet" href="../css/navbar.css">
<script src="../JavaScript/FavoriteManagement.js" type="text/javascript"></script>
</head>
<body>
	<%@ include file="fragments/authenticationCheck.jspf"%>		<%-- Login Check--%>
	<%@ include file="fragments/navbar.jspf" %>               	<%-- Navbar --%>
	<%@ include file="fragments/noScript.jspf" %>
	<header>
		<h1>Profil bearbeiten</h1>
	</header>
	<article>
		<h2>Bearbeiten Sie Ihr Profil hier</h2>
		<div class="userName">
			<form class="ChangeUsername" action="../UserUpdateServlet"
				method="post" accept-charset="utf-8">
				<p>Ihr aktueller Username ist:</p>
				<p>${userData.username}</p>
				<input type="text" name="changeUsername" size="30" maxlength="35"><br>
				<Button type="submit" value="changeUsername">Benutzernamen
					ändern</Button>
			</form>
		</div>
		<div>
			<form action="../UserUpdateServlet" method="post"
				accept-charset="utf-8">
				<label for="changeFirstName"> Vorname: ${userData.firstName}
				</label><br> <input type="text" size="30" name="changeFirstName"
					maxlength="35"><br> <label for="changeLastName">Nachname:
					${userData.lastName}</label><br> <input type="text" size="30"
					name="changeLastName" maxlength="35"><br>
				<Button type="submit">
					<!-- formaction="../UserUpdateServlet" formmethod="post"df -->
					Namen ändern
				</Button>
			</form>
		</div>
		<div>
			<form action="../UserUpdateServlet" method="post"
				accept-charset="utf-8">
				<label for="password">Passwort: </label><br> <input
					type="password" name="password" size="30" maxlength="35"><br>
				<Button type="submit" name="password" value="password"
					formaction="../UserUpdateServlet" formmethod="post">
					Passwort ändern</Button>
			</form>
		</div>
		<div>
			<form action="../UserUpdateServlet" method="post"
				accept-charset="utf-8">
				<label for="changeMail"> E-Mail: ${userData.eMail}</label><br>
				<input type="email" name="changeMail" size="30" maxlength="35"><br>
				<Button type="submit" value="changeMail">
					<!-- formaction="../UserUpdateServlet" formmethod="post"> -->
					E-Mail ändern
				</Button>
			</form>
		</div>
		<div>
			<form action="../UserUpdateServlet" method="post">
				<Button type="submit" name="deleteUser"
					formaction="../UserUpdateServlet" formmethod="post">Benutzer
					löschen</Button>
			</form>
		</div>
		<div>
			<h3>Favorisierte Übungen bearbeiten</h3>
			<div id="toggleExerciseAddition">
				<template id="favoriteExercises">
					<div>
						<div class="searchExerciseName" exercise-name></div>
						<div class="searchMuscleGroup" exercise-group></div>
					</div>
				</template>
				<div id="favoritesContainer">
				</div>
				<div id="exerciseContainer"></div>
				<button id="delExercise" hidden="hidden">Übung aus Favoriten Löschen</Button>
				<script>
					document.addEventListener("DOMContentLoaded", readFavoriteExercises);
				</script>
			</div>
		</div>
	</article>
	<footer>
		<!--Generische Navigationsleiste-->
	</footer>
</body>
</html>