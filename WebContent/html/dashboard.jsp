<!DOCTYPE html>
<html lang="de">
    <head>
    	<base href="${pageContext.request.requestURI}" />
        <title>Dashboard</title>
        <meta charset="utf-8">
        <meta name="author" content="Cem Durmus">
        <meta name="description" content="main page to have overview of your Training and the leaderbord">
        <meta name="keywords" content="Training Sport Leaderboard">
        
        <script type="text/javascript" src="../JavaScript/authenticationCheck.js" ></script>
        
    </head>
    <body>
        <header>
        <noscript> <h1> Bitte aktivieren Sie Javascript, da die Website sonst nicht funktioniert! </h1> </noscript>
            <h1><strong>Dashboard: </strong></h1>
            <input type="search" 
            name="suche">
        </header>
        <nav>
            <ul>
                <li><a href="./dashboard.html">Dashboard</a></li>
                <li><a href="./training.html">Training</a></li>
                <li><a href="./leaderboard.html">Leaderboard</a></li>
                <li><a href="html/accountSetting.jsp">Account Settings</a>
            </ul>
        </nav>
        <article>
            <table>
                <thead>
                    <tr>
                        <th>Datum</th>
                        <th>Trainingseinheit</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
                </tfoot>
            </table>
        </article>
        <script> // TODO: Fragen ob das i.o ist.
        var userData = "${userData}"
        	authenticationCheck(userData) // Wenn der User nicht eingloggt ist wird der Inhalt der Website entfernt und der Nutzer daz
        </script>
    </body>
</html>