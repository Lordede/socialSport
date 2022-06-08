<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="de">
<head>
<base href="${pageContext.request.requestURI}" />
<!-- Basis f�r relative Verweise -> Basis = Speicherort -->
<title>Übung</title>
<meta charset="UTF-8">
<meta name="description" content="Ihre one stop Sport Plattform">
<meta name="keywords" content="Sport, Gym, social media, sports">
<meta name="author" content="Cem Durmus">
<link rel="stylesheet" href="../css/navbar.css">
</head>
<body>
	<%@ include file="fragments/authenticationCheck.jspf"%>
	<%-- �berpr�fung, ob User eingeloggt ist --%>
	<!--Lukas Edmüller
        Navbar 
        https://www.w3schools.com/Css/css_navbar_horizontal.asp -->
    	<nav>
    		<ul>
                <li><a href="./dashboard.jsp">Dashboard</a></li>
                <li><a href="./training.jsp">Training</a></li>
                <li><a href="./leaderboard.jsp">Leaderboard</a></li>
                <li><a href="./accountSetting.jsp">Account Settings</a></li>
                <li><a class="active" href="./exercise.jsp">Übung erstellen</a></li>
  				<li style="float:right"><a class="active" href="#about">Logout</a></li>
			</ul> 
    	</nav>
	<header>
		<h1>Profil Bearbeiten</h1>
	</header>
	<article>
		<h2>Übung erstellen:</h2>
		<form class="ChangeUsername" action="../ExerciseServlet" method="post"
			accept-charset="utf-8">
			<div class="name">
				<p>Name der Übung:</p>
				<input type="text" name="exerciseName" size="30" maxlength="35"><br>
			</div>
			<div>
				<fieldset>
					<legend>Muskelgruppe wählen: </legend>
					<input type="checkbox" name="muscleGroup" value="Beinmuskulatur"
						id="c1"> <label for="c1">Beinmuskulatur</label> <input
						type="checkbox" name="muscleGroup" value="Rückenmuskulatur"
						id="c2"> <label for="c2">Rückenmuskulatur</label> <input
						type="checkbox" name="muscleGroup" value="Armmuskulatur" id="c3">
					<label for="c3">Armmuskulatur</label> <input type="checkbox"
						name="muscleGroup" value="Brustmuskulatur" id="c4"> <label
						for="c4">Brustmuskulatur</label> <input type="checkbox"
						name="muscleGroup" value="Bauchmuskulatur" id="c5"> <label
						for="c5">Bauchmuskulatur</label>
				</fieldset>
			</div>
			<Button type="submit">Übung Erstellen</Button>
		</Form>
		<div>
			<table>
				<tr>
					<th>Name der �bung</th>
					<th>Muskelgruppe</th>
				</tr>
				<tr>
					<td>${exercise.name}</td>
					<td>${exercise.muscleGroup}</td>
				</tr>
			</table>
		</div>
		<div>
			<h2>Satzauswahl:</h2>
			<form action="../SetServlet" method="post" accept-charset="utf-8">
				<label for="exerciseName">In Welchem Satz befindest du dich?</label>
				<input type="text" name="exerciseName" size="30" maxlength="3"><br>
				<label for="exerciseWeight">Wie viel Gewicht?</label> <input
					type="text" name="exerciseWeight" size="30" maxlength="3"><br>
				<label for="exerciseName">Wie viele Wiederholungen?</label> <input
					type="text" name="exerciseName" size="3" maxlength="3"><br>
				<Button type="submit" value="createSet">Satz erstellen</Button>
			</form>
		</div>
	</article>
	<footer>
		<!--Generische Navigationsleiste-->
	</footer>
</body>
</html>