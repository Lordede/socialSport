<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="de">
    <head>
    
        <title>Dashboard</title>
        <meta charset="utf-8">
        <meta name="author" content="Cem Durmus">
        <meta name="description" content="chose your training">
        <meta name="keywords" content="Training Sport Workout">
        <link rel="stylesheet" href="../css/navbar.css">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="../css/layout.css">
        <base href="${pageContext.request.requestURI}" /> <!-- Basis für relative Verweise -> Basis = Speicherort -->
        <script src="../JavaScript/TrainingsManagement.js" type="text/javascript"></script>
    </head>
    <body>
    <%@ include file="fragments/authenticationCheck.jspf" %> <%-- ÃberprÃ¼fung, ob User eingeloggt ist --%>
    <nav>
    		<ul>
                <li><a href="./dashboard.jsp">Dashboard</a></li>
                <li><a class="active"  href="./trainingsmenu.jsp">Training</a></li>
                <li><a href="./leaderboard.jsp">Leaderboard</a></li>
                <li><a href="./accountSetting.jsp">Account Settings</a></li>
                <li><a href="./exercise.jsp">Übung erstellen</a></li>
  				<li class="logout"><a class="active" href="#about">Logout</a></li>
			</ul> 
    	</nav>   
        <article>
            <h1><strong>Training: </strong></h1>
            <div id="training">
                <div id="addTraining">
                    <button id="loadTrainings">Trainings laden</button>
                    <button id="addNewTraining">Training hinzufügen</button>
                    <br>
                    <label id="nameOfTrainingLabel" hidden="hidden">Name des Traings</label>
                    <input id="nameOfTrainingInput" type="hidden">
                    <button id="sendTraining" hidden="hidden">Training erstellen</button>
                </div>
                <div id="searchPastTrainings">
                    <label id ="searchBarTrainingLabel">Training Suchen</label>
                    <input id="searchBarTraining" type="search">
                    <template class="searchResults">
                        <div class="trainingsName"></div>
                    </template>
                </div>
                <div class="trainingsContainer"></div>
            </div>
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
