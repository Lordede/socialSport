<!DOCTYPE html>
<html lang="de">
    <head>
        <title>Dashboard</title>
        <meta charset="utf-8">
        <meta name="author" content="Cem Durmus">
        <meta name="description" content="leaderboard">
        <meta name="keywords" content="best leader leaderboard">
        <!-- Basis f�r relative Verweise -> Basis = Speicherort -->
		<base href="${pageContext.request.requestURI}" />
    </head>
    <body>
    <%@ include file="fragments/authenticationCheck.jspf" %> <%-- �berpr�fung, ob User eingeloggt ist --%>
        <header>
            <h1><strong>Leaderboard: </strong></h1>
            <input type="search" 
            name="suche"
            formaction="get"
            formmethod="">
        </header>
        <nav>
            <ul>
                <li><a href="./dashboard.html">Dashboard</a></li>
                <li><a href="./training.html">Training</a></li>
                <li><a href="./leaderboard.html">Leaderboard</a></li>
            </ul>
        </nav>
        <article>
            <ol>
                <li><h1>&nbsp;</h1></li>
                <li><h1>&nbsp;</h1></li>
                <li><h1>&nbsp;</h1></li>
            </ol>
        </article>
        <footer>
            
        </footer>
    </body>
</html>