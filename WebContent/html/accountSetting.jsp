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
        <header>
            <h1>Profil Bearbeiten</h1>
        </header>
        <article>
            <h2>Bearbeiten Sie Ihr Profil hier</h2>
<!--             <div class="profile_image"> -->
<!--                 <img src="" alt="Profil Picture"> -->
<!--                 <form action="../UserUpdateServlet" method="post" enctype="multipart/form-data"> -->
<!--                     <p> Profilbild ändern:  -->
<!--                         <input type="image" -->
<!--                          name="changeImage" -->
<!--                          accept="image/*" -->
<!--                          formaction="../UserUpdateServlet" -->
<!--                          formmethod="post"> -->
<!--                     </p> -->
<!--                     <p> -->
<!--                     	<button type="submit" -->
<!--                      	 name="changeImage" -->
<!--                        	 formaction="../UserUpdateServlet"  -->
<!--                     	 formmethod="post"> -->
<!--                      </button> -->
<!--                     </p> -->
<!--                 </form> -->
<!--             </div> -->
            <div class="userName">														
                <form class="ChangeUsername" action="../UserUpdateServlet" method="post">
                <p>Ihr aktueller Username ist:</p><p>${userData.username}</p>
        		<input type="text" name="changeUsername" size="30" maxlength="35"><br> 
        		<input type="submit" value="changeUsername" size="30" > 
				
                </form>
             	</div> 
<!--             </div> -->
<!--             <div> -->
<!--             	<form action="UserUpdateServlet" method="post"> -->
<%--             		<p>Aktueller Name:</p>${userData.firstName} ${userData.lastName} --%>
<!--                     <label>Vorname:  -->
<!--                         <input type="text"  -->
<!--                         size="30"  -->
<!--                         maxlength="35"> -->
<!--                     </label> -->
<!--                     <p>Nachname: -->
<!--                     	<input type="text" -->
<!--                     	size="30" -->
<!--                     	maxlength="35"> -->
<!--                     </p> -->
<!--                     <p> -->
<!--                     	<button type="submit" -->
<!--                      	 name="changeNames" -->
<!--                        	 formaction="../UserUpdateServlet"  -->
<!--                     	 formmethod="post"> -->
<!--                      </button> -->
<!--                     </p> -->
<!--                 </form> -->
<!--             </div> -->
<!--             <div> -->
<!--             	<form action="UserUpdateServlet" method="post"> -->
<!--                     <p>Passwort:  -->
<!--                         <input type="password"  -->
<!--                         size="30"  -->
<!--                         maxlength="35"> -->
<!--                     </p> -->
<!--                     <p> -->
<!--                     	<button type="submit" -->
<!--                      	 name="changePassword" -->
<!--                        	 formaction="../UserUpdateServlet"  -->
<!--                     	 formmethod="post"> -->
<!--                      </button> -->
<!--                     </p> -->
<!--                 </form> -->
<!--             </div> -->
<!--             <div> -->
<!--             	<form action="UserUpdateServlet" method="post"> -->
<!--                     <p>E-Mail:  -->
<!--                         <input type="email"  -->
<!--                         size="30"  -->
<!--                         maxlength="35"> -->
<!--                     </p> -->
<!--                     <p> -->
<!--                     	<button type="submit" -->
<!--                      	 name="changeMail" -->
<!--                        	 formaction="../UserUpdateServlet"  -->
<!--                     	 formmethod="post"> -->
<!--                      </button> -->
<!--                     </p> -->
<!--                 </form> -->
<!--             </div> -->
<!--              <div> -->
<!--             	<p> -->
<!--                    <button type="submit" -->
<!--                     name="deleteUser" -->
<!--                     formaction="../UserUpdateServlet"  -->
<!--                    	formmethod="post"> -->
<!--                     </button> -->
<!--                     </p> -->
<!--             </div> -->
        </article>
        <footer>
            <!--Generische Navigationsleiste-->
        </footer>
    </body>
</html>