<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="de">
    <head>
    
        <title>Trainingsmenü</title>
        <meta charset="utf-8">
        <meta name="author" content="Cem Durmus">
        <meta name="description" content="chose your training">
        <meta name="keywords" content="Training Sport Workout">
        <link rel="stylesheet" href="../css/navbar.css">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="../css/layout.css">
         <link rel="stylesheet" href="../css/trainingsmenu.css">
        <base href="${pageContext.request.requestURI}" /> <!-- Basis für relative Verweise -> Basis = Speicherort -->
        
        <script src="../JavaScript/TrainingsManagement.js" type="text/javascript"></script>
        <%@ include file="fragments/footerHEAD.jspf"%>
        <%@ include file="fragments/authenticationCheckHEAD.jspf" %>
        <%@ include file="fragments/navbarHEAD.jspf" %>
  </head>
  <body>
    <%@ include file="fragments/authenticationCheck.jspf" %> <%-- Überprüfung,
    ob User eingeloggt ist --%> <%@ include file="fragments/navbar.jspf" %> <%--
    Navbar --%> <%@ include file="fragments/noScript.jspf" %>
    <article class="center">
      <div class="center">
        <h1><strong>Training: </strong></h1>
        <div id="training">
          <div id="addTraining">
            <button id="addNewTraining">Training hinzufügen</button>
            <br />
            <label id="nameOfTrainingLabel" hidden="hidden"
              >Name des Traings</label
            >
            <input id="nameOfTrainingInput" type="hidden" />
            <button id="sendTraining" hidden="hidden">
              Training erstellen
            </button>
          </div>
          <div id="searchPastTrainings">
            <label id="searchBarTrainingLabel">Training suchen:</label>
            <input id="searchBarTraining" type="search" />
            <template class="searchResults">
              <div class="trainingsName"></div>
            </template>
          </div>
          <div class="trainingsContainer"></div>
        </div>
      </div>
      <div id="ruehl">
        <img
          id="imgRuehl"
          src="../img/Markus_Ruehl.jpg"
          alt="Markus Rühl am flexen"
        />
        <p id="quote">"Wer scheiß Arme hat, sieht scheiße aus"</p>
        <p>- Markus Rühl, Finalist Mr. Oplympia 1999</p>
      </div>
      <script>
        let searchTrainingInput = document.getElementById(
          "searchPastTrainings"
        );
        let createTraining = document.getElementById("addNewTraining");
        document.addEventListener("DOMContentLoaded", init);

                function init() {
                    //searchTrainingInput.addEventListener("input", searchTraining);
                    createTraining.addEventListener("click", hideSearch);
                    console.log("html spricht an");
                    readPastTrainings();
                }
            </script>
        </article>
        <%@ include file="fragments/footer.jspf"%>
    </body>

</html>
