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
        <script src="../JavaScript/Administration.js" type="text/javascript"></script>
    </head>
    <body>
        <header>
            
        </header>
        <main>
            <div id="userAdministration">
                <button id="loadUsers">Nutzer Laden</button>
                <table id="userTable">
                    <tr>
                        <th>Benutzername</th>
                        <th>E-Mail</th>
                        <th>Vorname</th>
                        <th>Nachname</th>
                    </tr>
                    
                    
                </table>
                <div id="userContainer"> <!--Hier ausgewählten User reinladen-->
                    
                </div>
            </div>
            <div id="exerciseHandler">
                <h2>Übung erstellen:</h2>
                <form class="exerciseHandler" action="../ExerciseServlet" method="post"
                accept-charset="utf-8" enctype="multipart/form-data">
                <div class="nameExercise">
                    <p>Name der Übung:</p>
                    <input type="text" name="exerciseName" size="30" maxlength="35"><br>
                </div>
                <div>
                    <fieldset>
                        <legend>Muskelgruppe wählen: </legend>
                        <input type="radio" name="muscleGroup" value="Beinmuskulatur" id="c1"> 
                        <label for="c1">Beinmuskulatur</label> 
                        <input type="radio" name="muscleGroup" value="Rückenmuskulatur" id="c2">
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
                <label for="deleteExercise">Übung Löschen</label>
                <input type="text" id="deleteExercise">
                <Button id="submitExercise" type="submit">Übung Erstellen</Button>
            </form>
        </div>
        <script>
            var button = document.getElementById("loadUsers");
            document.addEventListener("DOMContentLoaded", init);
            
            function init() {
                //document.getElementById("addButton").addEventListener('click', toggleExerciseSearchbar);
                button.addEventListener('click', createUserElements);
                // document.ElementByName('addButton').submit();
                console.log("html spricht an");
            }
            
            </script>
    </main>
    <footer>
        
    </footer>
    <template id="trTemplate">
        <tr class="tableRowTempl">
            <td class="username"></td>
            <td class="firstname"></td>
            <td class="lastname"></td>
            <td class="email"></td>
        </tr>
    </template>
</body>
</html>
