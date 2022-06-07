<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/uebungen.css"> <!-- href nach konvertierung zu jsp anpassen -->
    <base href="${pageContext.request.requestURI}" /> 
  	
    <title>Training</title>
    <link rel="stylesheet" href="../css/training.css">

    <script src="../JavaScript/CreateExercise.js"></script>
    <script src="../JavaScript/TriggerExerciseAdditon.js" type="text/javascript"></script>

</head>
<noscript><h1>BITTE JAVASCRIPT AKTIEREN!</h1></noscript>
<body>
   <!--  <%@ include file="fragments/authenticationCheck.jspf" %>  -->
        <%-- Überprüfung, ob der User eingeloggt ist --%>
            <header> Social Sport</header>

            <nav id="navbar" class="navbar">
                <!--TODO: Links hinzufuegen-->
                <ul class="navbar">
                    <li class="navbar">Home</li>
                    <li class="navbar">Training</li>
                    <li class="navbar">Leaderboard</li>
                </ul>
            </nav>


            <main id="exercises">
                <!--TODO: Logik hinzufuegen-->

                <!--<article class ="exercise elements">
            <h2 class = "ExerciseHeadline">Bench Press</h2>
            <table class="exerciseTable">
                <tr class = "headLineTable">
                    <th>Satz</th>
                    <th>KG</th>
                    <th>Wiederholungen</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>BLANK</td>
                    <td>BLANK</td>
                    <td>&check;</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>BLANK</td>
                    <td>BLANK</td>
                    <td>&check;</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>BLANK</td>
                    <td>BLANK</td>
                    <td>&check;</td>
                </tr>
            </table>
            <p><button class="button addSet"> Satz hinzuf&uuml;gen</button></p>
        </article>-->

            </main>


            <aside id="searchContainer" class="elements">
                <div id="toggleExerciseAddition">
                    <input type="hidden" id="searchBar">
                    <button id="addButton" value="addButton">Übung hinzufügen</button>
                    <template id="searchResults">
                        <div>
                            <div class="searchExerciseName" exercise-name></div>
                            <div class="searchMuscleGroup" exercise-group></div>
                        </div>
                    </template>
                    <div id="searchResultContainer"></div>
                </div>
            </aside>

            <div class="clear"></div>

            <div id="buttons">
                <button class="button" id="cancelWorkout">Workout abbrechen</button>
                <button class="button" id="test">Testübung hinzufügen</button>
            </div>

            <footer>Hier generischen Footer einfügen</footer>
            <!--Hier generischen Footer einfügen -->

            <script>

                document.addEventListener("DOMContentLoaded", init);

                function init() {

                    var button = document.getElementById("addButton");
                        document.addEventListener("DOMContentLoaded", init);

                    //document.getElementById("addButton").addEventListener('click', toggleExerciseSearchbar);
                    button.addEventListener('click', toggleExerciseSearchbar);
                    // document.ElementByName('addButton').submit();
                    console.log("html spricht an");

                    var testbutton = document.getElementById("test");
                    //                testbutton.addEventListener("click", addExercise); 	//KEINE "()"" bei Methode und bei CallBack darf kein Paramter haben!

                    var addSetButton = document.getElementsByClassName("addSet");    		// Alle "SetButton" überwachen

                    for (var i = 0; i < addSetButton.length; i++) {                  		// Alle "SetButton" mit EventListener überwachen
                        addSetButton[i].addEventListener("click", getExerciseReference)
                    }

                }          
            </script>
</body>

</html>