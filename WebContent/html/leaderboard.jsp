<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>
<meta charset="utf-8">
<meta name="author" content="Cem Durmus">
<meta name="description" content="leaderboard">
<meta name="keywords" content="best leader leaderboard">
<base href="${pageContext.request.requestURI}" />
<link rel="stylesheet" href="../css/navbar.css">
<!-- Basis f�r relative Verweise -> Basis = Speicherort -->
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
                <li><a class="active" href="./leaderboard.jsp">Leaderboard</a></li>
                <li><a href="./accountSetting.jsp">Account Settings</a></li>
                <li><a href="./exercise.jsp">Übung erstellen</a></li>
  				<li style="float:right"><a class="active" href="#about">Logout</a></li>
			</ul> 
    	</nav>
	<header>
		<h1>
			<strong>Leaderboard: </strong>
		</h1>
		<input type="search" name="suche" formaction="get" formmethod="">
	</header>
	<!-- <nav>
		<ul>
			<li><a href="./dashboard.html">Dashboard</a></li>
			<li><a href="./training.html">Training</a></li>
			<li><a href="./leaderboard.html">Leaderboard</a></li>
		</ul>
	</nav> -->
	<article>
		<ol>
			<li><h1>&nbsp;</h1></li>
			<li><h1>&nbsp;</h1></li>
			<li><h1>&nbsp;</h1></li>
		</ol>
	</article>
	<footer> </footer>
</body>
</html>
