<!DOCTYPE html>
<!--Um hier reinzukommen erst Passwort abfrage-->
<html lang="de">
    <head>
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
            <div class="profile_image">
                <img src="" alt="Profil Picture">
                <form action="../UserUpdateServlet" method="post" enctype="multipart/form-data">
                    <p> Profilbild ändern: 
                        <input type="image"
                         name="changeImage"
                         accept="image/*"
                         formaction="../UserUpdateServlet"
                         formmethod="post">
                    </p>
                    <p>
                    	<button type="submit"
                     	 name="changeImage"
                       	 formaction="../UserUpdateServlet" 
                    	 formmethod="post">
                     </button>
                    </p>
                </form>
            </div>
            <div class="userName">
                <form action="UserUpdateServlet method="post">
                	<p>Aktueller Benutzername:</p>${userData.username}
                    <p>Benutzername: 
                        <input type="text" 
                        size="30" 
                        maxlength="35">
                    </p>
                    <p>
                    	<button type="submit"
                     	 name="changeUsername"
                       	 formaction="../UserUpdateServlet" 
                    	 formmethod="post">
                     </button>
                    </p>
                </form>
            </div>
            <div>
            	<form action="UserUpdateServlet" method="post">
            		<p>Aktueller Name:</p>${userData.firstName} ${userData.lastName}
                    <label>Vorname: 
                        <input type="text" 
                        size="30" 
                        maxlength="35">
                    </label>
                    <p>Nachname:
                    	<input type="text"
                    	size="30"
                    	maxlength="35">
                    </p>
                    <p>
                    	<button type="submit"
                     	 name="changeNames"
                       	 formaction="../UserUpdateServlet" 
                    	 formmethod="post">
                     </button>
                    </p>
                </form>
            </div>
            <div>
            	<form action="UserUpdateServlet" method="post">
                    <p>Passwort: 
                        <input type="password" 
                        size="30" 
                        maxlength="35">
                    </p>
                    <p>
                    	<button type="submit"
                     	 name="changePassword"
                       	 formaction="../UserUpdateServlet" 
                    	 formmethod="post">
                     </button>
                    </p>
                </form>
            </div>
            <div>
            	<form action="UserUpdateServlet" method="post">
                    <p>E-Mail: 
                        <input type="email" 
                        size="30" 
                        maxlength="35">
                    </p>
                    <p>
                    	<button type="submit"
                     	 name="changeMail"
                       	 formaction="../UserUpdateServlet" 
                    	 formmethod="post">
                     </button>
                    </p>
                </form>
            </div>
             <div>
            	<p>
                   <button type="submit"
                    name="deleteUser"
                    formaction="../UserUpdateServlet" 
                   	formmethod="post">
                    </button>
                    </p>
            </div>
        </article>
        <footer>
            <!--Generische Navigationsleiste-->
        </footer>
    </body>
</html>