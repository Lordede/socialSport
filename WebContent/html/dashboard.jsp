<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="de">
    <head>
        <title>Dashboard</title>
        <meta charset="utf-8">
        <meta name="author" content="Cem Durmus">
        <meta name="description" content="main page to have overview of your Training and the leaderbord">
        <meta name="keywords" content="Training Sport Leaderboard">
<!--         https://stackoverflow.com/questions/20490848/servlet-not-redirected-to-page-showing-non-styled-page -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navbar.css" />
        <base href="${pageContext.request.requestURI}" /> <!-- Basis f�r relative Verweise -> Basis = Speicherort -->
    </head>
    <body>
    <%@ include file="fragments/authenticationCheck.jspf" %> <%-- �berpr�fung, ob User eingeloggt ist --%>
    <!--Lukas Edmüller
        Navbar 
        https://www.w3schools.com/Css/css_navbar_horizontal.asp -->
    	<nav>
    		<ul>
                <li><a class="active" href="./dashboard.jsp">Dashboard</a></li>
                <li><a href="./training.jsp">Training</a></li>
                <li><a href="./leaderboard.jsp">Leaderboard</a></li>
                <li><a href="./accountSetting.jsp">Account Settings</a></li>
                <li><a href="./exercise.jsp">Übung erstellen</a></li>
  				<li style="float:right"><a class="active" href="#about">Logout</a></li>
			</ul> 
    	</nav>
        <header>
            <h1><strong>Dashboard: </strong></h1>
            <input type="search" 
            name="suche"
            
            formaction="get"
            formmethod="">
        </header>
        <!-- <nav>
            <ul>
                <li><a href="./dashboard.html">Dashboard</a></li>
                <li><a href="./training.html">Training</a></li>
                <li><a href="./leaderboard.html">Leaderboard</a></li>
                <li><a href="./accountSetting.jsp">Account Settings</a></li>
                <li><a href="./html/exercise.html">�bung erstellen</a></li>
            </ul>
        </nav> -->
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
            
            <form action="../SetServlet" method="get">
            <input type=number>
            <input type=submit>
            </form>
        </article>
    </body>
</html>
