<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="de">
    <head>
    
        <title>Dashboard</title>
        <meta charset="utf-8">
        <meta name="author" content="Cem Durmus">
        <meta name="description" content="chose your training">
        <meta name="keywords" content="Training Sport Workout">
        <base href="${pageContext.request.requestURI}" /> <!-- Basis für relative Verweise -> Basis = Speicherort -->
        <script src="../JavaScript/TrainingsManagement.js" type="text/javascript"></script>
    </head>
    <body>
    <%@ include file="fragments/authenticationCheck.jspf" %> <%-- ÃberprÃ¼fung, ob User eingeloggt ist --%>
        <header>
            <h1><strong>Training: </strong></h1>
            <input type="search" 
            name="suche"
            formaction=""
            formmethod="get">
        </header>
        <nav>
            <ul>
                <li><a href="./dashboard.html">Dashboard</a></li>
                <li><a href="./training.html">Training</a></li>
                <li><a href="./leaderboard.html">Leaderboard</a></li>
            </ul>
        </nav>
        <article>
            <div id="training">
                <div id="addTraining">
                    <button id="loadTrainings">Trainings laden</button>
                    <button id="addNewTraining">Training hinzufügen</button>
                    <label id="nameOfTrainingLabel" hidden="hidden">Name des Traings</label>
                    <input id="nameOfTrainingInput" type="hidden">
                    <button id="sendTraining" hidden="hidden">Training erstellen</button>
                </div>
                <div id="searchPastTrainings">
                    <label id ="searchBarTrainingLabel">Übung Suchen</label>
                    <input id="searchBarTraining" type="search">
                    <template class="searchResults">
                        <div class="trainingsName"></div>
                    </template>
                </div>
                <div class="trainingsContainer"></div>
            </div>
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
            <button type="addTraining"
                    name="new training"
                    formaction=""
                    formmethod="post">
                    Neues Training
            </button>
            <script>
                let searchTrainingInput = document.getElementById("searchPastTrainings")
                let loadAllTrainings = document.getElementById("loadTrainings");
                let createTraining = document.getElementById("addNewTraining");
                document.addEventListener("DOMContentLoaded", init);

                function init() {
                    loadAllTrainings.addEventListener("click", readPastTrainings);
                    //searchTrainingInput.addEventListener("input", searchTraining);
                    createTraining.addEventListener("click", hideSearch);
                    console.log("html spricht an");
                }
            </script>
        </article>
    </body>
</html>
