<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--Um hier reinzukommen erst Passwort abfrage-->
<html lang="de">
<head>
<base href="${pageContext.request.requestURI}" />
<!-- Basis für relative Verweise -> Basis = Speicherort -->
<title>Accounteinstellungen</title>
<meta charset="utf-8">
<meta name="description" content="Ihre one stop Sport Plattform">
<meta name="keywords" content="Benutzerkonto bearbeiten">
<meta name="author" content="Cem Durmus">
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="../css/layout.css">
<link rel="stylesheet" href="../css/navbar.css">
<link rel="stylesheet" href="../css/accountSetting.css">
<script src="../JavaScript/FavoriteManagement.js" type="text/javascript"></script>

<%@ include file="fragments/authenticationCheckHEAD.jspf"%>
<%@ include file="fragments/navbarHEAD.jspf"%>
<%@ include file="fragments/footerHEAD.jspf"%>

</head>
<body>
	<%@ include file="fragments/authenticationCheck.jspf"%>
	<%-- Login Check--%>
	<%@ include file="fragments/navbar.jspf"%>
	<%-- Navbar --%>
	<%@ include file="fragments/noScript.jspf"%>
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
				<input type="text" name="changeUsername" size="30" maxlength="35" minlength="3" required><br>
				<Button type="submit" value="changeUsername">Benutzernamen
					ändern</Button>
			</form>
		</div>
		<div>
			<form action="../UserUpdateServlet" method="post"
				accept-charset="utf-8">
				<label for="changeFirstName"> Vorname: ${userData.firstName}</label><br>
				<input type="text" size="30" id="changeFirstName"
					name="changeFirstName" maxlength="35" minlength="3" required><br>
				<Button value="changeFirstName" type="submit">Vornamen
					ändern</Button>
				<br>
			</form>
			<form action="../UserUpdateServlet" method="post"
				accept-charset="utf-8">
				<label for="changeLastName">Nachname: ${userData.lastName}</label><br>
				<input type="text" size="30" id="changeLastName"
					name="changeLastName" maxlength="35" minlength="3" required><br>
				<Button value="changeLastName" type="submit">Nachnamen
					ändern</Button>
			</form>
		</div>
		<div>
			<form action="../UserUpdateServlet" method="post"
				accept-charset="utf-8">
				<label for="password">Passwort: </label><br> <input
					type="password" id="password" name="password" size="30"
					maxlength="35" minlength="3" required><br>
				<Button type="submit" name="password" value="password"
					formaction="../UserUpdateServlet" formmethod="post">
					Passwort ändern</Button>
			</form>
		</div>
		<div>
			<form action="../UserUpdateServlet" method="post"
				accept-charset="utf-8">
				<label for="changeMail"> E-Mail: ${userData.eMail}</label><br>
				<input type="email" id="changeMail" name="changeMail" size="30"
					maxlength="35" minlength="5" required><br>
				<Button type="submit" value="changeMail">
					<!-- formaction="../UserUpdateServlet" formmethod="post"> -->
					E-Mail ändern
				</Button>
			</form>
		</div>
		<div>
			<form action="../UserUpdateServlet" method="GET">
				<Button type="submit" name="deleteUser"
					formaction="../UserUpdateServlet" formmethod="GET">Benutzer
					löschen</Button>
			</form>
		</div>
		<div>
			<h3>Favorisierte Übungen bearbeiten</h3>
			<div id="toggleExerciseAddition">
				<template id="favoriteExercises">
					<div>
						<div class="searchExerciseName" exercise-name></div>
					</div>
				</template>
				<div id="favoritesContainer"></div>
				<div id="handleExercise">
					<button id="delExercise" hidden="hidden">
					Ausgewählte	Favoriten Löschen</Button>
					<button id="abbrechen" hidden="hidden">abbrechen</button>
					<div id="exerciseContainer"></div>
				</div>
				<script>
					document.addEventListener("DOMContentLoaded",
							init);
							
						function init()
						{
							readFavoriteExercises();

						}
					// var buttons = document.querySelectorAll("Button");
					// buttons.forEach(button => button.addEventListener("click", event => {window.location.reload(true)}));
				</script>
			</div>
		</div>
	</article>
	<%@ include file="fragments/footer.jspf"%>
</body>
</html>