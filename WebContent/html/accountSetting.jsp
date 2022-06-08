<!DOCTYPE html>
<!--Um hier reinzukommen erst Passwort abfrage-->
<html lang="de">
<head>
<base href="${pageContext.request.requestURI}" />
<!-- Basis f�r relative Verweise -> Basis = Speicherort -->
<title>Profil Bearbeiten</title>
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
                <li><a class="active" href="./accountSetting.jsp">Account Settings</a></li>
                <li><a href="./exercise.jsp">Übung erstellen</a></li>
  				<li style="float:right"><a class="active" href="#about">Logout</a></li>
			</ul> 
    	</nav>
	<header>
		<h1>Profil Bearbeiten</h1>
	</header>
	<article>
		<h2>Bearbeiten Sie Ihr Profil hier</h2>
		<div class="profile_image">
			<img src="" alt="Profil Picture">
			<form action="../UserUpdateServlet" method="post"
				enctype="multipart/form-data" accept-charset="utf-8">
				<p>Profilbild �ndern:</p>
				<input type="image" name="changeImage" accept="image/*"
					formaction="../UserUpdateServlet" formmethod="post">
				<button type="submit" name="changeImage" value="changeImage"
					formaction="../UserUpdateServlet" formmethod="post">Bild
					�ndern</button>
			</form>
		</div>
		<div class="userName">
			<form class="ChangeUsername" action="../UserUpdateServlet"
				method="post" accept-charset="utf-8">
				<p>Ihr aktueller Username ist:</p>
				<p>${userData.username}</p>
				<input type="text" name="changeUsername" size="30" maxlength="35"><br>
				<Button type="submit" value="changeUsername">Benutzernamen
					�ndern</Button>
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
					Namen �ndern
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
					Passwort �ndern</Button>
			</form>
		</div>
		<div>
			<form action="../UserUpdateServlet" method="post"
				accept-charset="utf-8">
				<label for="changeMail"> E-Mail: ${userData.eMail}</label><br>
				<input type="email" name="changeMail" size="30" maxlength="35"><br>
				<Button type="submit" value="changeMail">
					<!-- formaction="../UserUpdateServlet" formmethod="post"> -->
					E-Mail �ndern
				</Button>
			</form>
		</div>
		<div>
			<form action="../UserUpdateServlet" method="post">
				<Button type="submit" name="deleteUser"
					formaction="../UserUpdateServlet" formmethod="post">Benutzer
					l�schen</Button>
			</form>
		</div>
	</article>
	<footer>
		<!--Generische Navigationsleiste-->
	</footer>
</body>
</html>