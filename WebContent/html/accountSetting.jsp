<!DOCTYPE html>
<!--Um hier reinzukommen erst Passwort abfrage-->
<html lang="de">
    <head>
    <base href="${pageContext.request.requestURI}"  /> <!-- Ganz wichtig damit die jsp auch logisch da liegt wo sie isch im Dateisystem befindet -->
           <title>
            Profil Bearbeiten
        </title>
        <meta charset="UTF-8">
        <meta name="description" content="Ihre one stop Sport Plattform">
        <meta name="keywords" content="Sport, Gym, social media, sports">
        <meta name="author" content="Cem Durmus">
    </head>
    <body>
    <%@ include file="fragments/authenticationCheck.jspf" %> <%-- �berpr�fung, ob User eingeloggt ist --%>
    
        <header>
        	<noscript> <h1> Bitte aktivieren Sie Javascript, da die Website sonst nicht funktioniert! </h1> </noscript>
            <h1>Profil Bearbeiten</h1>
        </header>
        <article>
            <h2>Bearbeiten Sie Ihr Profil hier</h2>
            <div class="profile_image">
                <img src="" alt="Profil Picture">
                <form action="../UserUpdateServlet" method="post" enctype="multipart/form-data">
                    <p> Profilbild �ndern: </p>
                        <input type="image"
                         name="changeImage"
                         accept="image/*"
                         formaction="../UserUpdateServlet"
                         formmethod="post">
                    	<button type="submit"
                     	 name="changeImage"
                     	 value="changeImage"
                       	 formaction="../UserUpdateServlet" 
                    	 formmethod="post">
                    	 Bild �ndern
                     </button>
                </form>
            </div>
            <div class="userName">														
                <form class="ChangeUsername" action="../UserUpdateServlet" method="post">
                <p>Ihr aktueller Username ist:</p><p>${userData.username}</p>
        		<input type="text" name="changeUsername" size="30" maxlength="35"><br> 
        		<Button type="submit" value="changeUsername" >Benutzernamen �ndern</Button>
                </form>
             </div> 
            <div>
            	<form action="../UserUpdateServlet" method="post">
                    <label for="changeFirstName"> Vorname: ${userData.firstName} </label><br>
                    <input type="text" size="30" name="changeFirstName" maxlength="35"><br>
                    <label for="changeLastName">Nachname: ${userData.lastName}</label><br>
                    <input type="text" size="30" name="changeLastName" maxlength="35"><br>
                    <Button type="submit"> <!-- formaction="../UserUpdateServlet" formmethod="post"df -->
                    	 Namen �ndern
                     </Button>
                </form>
            </div>
            <div>
            	<form action="../UserUpdateServlet" method="post">
                    <label for="password">Passwort: </label><br>
                    <input type="password" name="password" size="30" maxlength="35"><br>
                    <Button type="submit" name="password" value="password" formaction="../UserUpdateServlet" formmethod="post"> Passwort �ndern</Button>
                </form>
            </div>
            <div>
            	<form action="../UserUpdateServlet" method="post">
          			<label for="changeMail"> E-Mail: ${userData.eMail}</label><br>
                    <input type="email" name="changeMail" size="30" maxlength="35"><br>
                    <Button type="submit" value="changeMail" > <!-- formaction="../UserUpdateServlet" formmethod="post"> --> E-Mail �ndern </Button>
                </form>
            </div>
             <div>
                <form action="../UserUpdateServlet" method="post">
                   <Button type="submit" name="deleteUser" formaction="../UserUpdateServlet" formmethod="post">Benutzer l�schen </Button>
                </form>
            </div>
        </article>
        <footer>
            <!--Generische Navigationsleiste-->
        </footer>
    </body>
</html>