<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="author" content="Cem Durmus"/>
<title>Insert title here</title>
</head>
 <body>
 <%@ include file="fragments/authenticationCheck.jspf" %> <%-- Überprüfung, ob User eingeloggt ist --%>
        <header>
        	<noscript> <h1> Bitte aktivieren Sie Javascript, da die Website sonst nicht funktioniert! </h1> </noscript>
            <h1>Profil Bearbeiten</h1>
        </header>
        <article>
            <h2>Bearbeiten Sie Ihr Profil hier</h2>
            <div class="profile_image">
                <img src="" alt="Profil Picture">
                <form action="" method="post" enctype="multipart/form-data">
                    <p> Profilbild ändern: 
                    </p>
                </form>
            </div>
            <div class="userName">
                <form action="User_Servlet" method="post">
                    <p>Benutzername: 
                    </p> ${userData.username}
                </form>
            </div>
            <div>
            	<form action="User_Servlet" method="post">
                    <p>Vorname: 
                    </p>
                    <p>Nachname:
                    </p>
                </form>
            </div>
            <div>
            	<form action="User_Servlet" method="post">
                    <p>Passwort: 
                    </p>
                </form>
            </div>
            <div>
            	<form action="User_Servlet" method="post">
                    <p>E-Mail: 
                    </p>
                </form>
            </div>
           
        </article>
        <footer>
            <!--Generische Navigationsleiste-->
        </footer>
    </body>
</html>