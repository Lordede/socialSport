<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <base href="${pageContext.request.requestURI}" />
        <meta charset="UTF-8">
        <meta name="description" content="Speichern Sie Ihre Traings hier">
        <meta name="keywords" content="Sport, Training, Sommerfigur, Trainingserfolg">
        <meta name="author" content="Cem Durmus">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="../css/layout.css">
        <link rel="stylesheet" href="../css/admin.css">
        <script src="../JavaScript/Administration.js" type="text/javascript"></script>
        <%@ include file="fragments/authenticationCheckHEAD.jspf"%>
		<%@ include file="fragments/navbarHEAD.jspf"%>
        <%@ include file="fragments/footerHEAD.jspf"%>
    </head>
    <body>
    <%@ include file="fragments/noScript.jspf"%>
	<%@ include file="fragments/authenticationCheck.jspf"%>
	<%@ include file="fragments/navbar.jspf"%>
    
        
        <header>
            
        </header>
        <main>
            <div id="userAdministration">
                <label for="searchInput">Mitglied suchen: </label>
                <input id="searchInput" type="type">
                <table id="userTable">
                    <thead>
                    <tr>
                        <th>Benutzername</th>
                        <th>Nachname</th>
                        <th>Vorname</th>
                        <th>E-Mail</th>
                    </tr>
                </thead>
                <tbody id="insertUsers">

                </tbody>    
                </table>
                <div id="userContainer"> <!--Hier ausgewählten User reinladen-->
                    <br>
                    <button id="delUser" hidden="hidden">Benutzer Löschen</button>
                    <button id="setAdmin" hidden="hidden">Adminrechte vergeben</button>
                </div>
            </div>
            <div id="exerciseHandler">
                <h2>Übung erstellen:</h2>
                <form class="exerciseHandler" action="../ExerciseServlet" method="post"
                accept-charset="utf-8" enctype="multipart/form-data">
                <div class="nameExercise">
                    <p>Name der Übung:</p>
                    <input type="text" name="exerciseName" size="30" maxlength="50"><br>
                </div>
                <div>
				<fieldset>
					<legend>Muskelgruppe wählen: </legend>
					<input type="radio" name="muscleGroup" value="Beinmuskulatur"id="c1"> 
                    <label for="c1">Beinmuskulatur</label> 
                    <input type="radio" name="muscleGroup" value="Rückenmuskulatur"id="c2">
                    <label for="c2">Rückenmuskulatur</label> 
                    <input type="radio" name="muscleGroup" value="Armmuskulatur" id="c3">
					<label for="c3">Armmuskulatur</label> 
                    <input type="radio" name="muscleGroup" value="Brustmuskulatur" id="c4"> 
                    <label for="c4">Brustmuskulatur</label> 
                    <input type="radio" name="muscleGroup" value="Bauchmuskulatur" id="c5"> 
                    <label for="c5">Bauchmuskulatur</label>
				</fieldset>
				<input id="image" 
                type="file"
                name="image"
                accept="image/*"
                formaction="../ExerciseServlet"
                formmethod="post">
            </div>
			<Button id="submitExercise" type="submit">Übung Erstellen</Button>
            <label for="searchExercises">Übung suchen</label>
            <input type="text" id="searchExercises">
            <table id="exerciseContainer">
                <thead>
                    <tr>
                        <th>Name der Übung</th>
                        <th>Muskelgruppe</th>
                    </tr>
                </thead>
                <tbody id="insertExercises"></tbody>
            </table>
		</form>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", init);
        window.onload = function(){
            readExercises();
            readUsers();
        };
        
        function init() {
            
            
        }
        
        </script>
    </main>
    <footer>
        <template id="trExercise">
            <tr>
                <td class="exerciseName"></td>
                <td class="muscleGroup"></td>
                <td class="picture"></td>
            </tr>
        </template>
        <template id="trTemplate">
            <tr>
                <td class="username"></td>
                <td class="firstname"></td>
                <td class="lastname"></td>
                <td class="email"></td>
            </tr>
        </template>
    </footer>
    <%-- Es sind ja mehrere Footer Elemente erlaubt? --%>
    <%@ include file="fragments/footer.jspf"%> 
</body>
</html>
