<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<base href="${pageContext.request.requestURI}" />
<!-- Basis f�r relative Verweise -> Basis = Speicherort -->
<meta charset="utf-8" />

<title>Training</title>
<link rel="stylesheet" href="../css/training.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap"
	rel="stylesheet">

<script src="../JavaScript/CreateExercise.js"></script>
<script src="../JavaScript/TriggerExerciseAdditon.js"
	type="text/javascript"></script>
<script src="../JavaScript/TrainingSettings.js" type="text/javascript"></script>
<%@ include file="fragments/authenticationCheckHEAD.jspf"%>
<%@ include file="fragments/navbarHEAD.jspf"%>
<%@ include file="fragments/footerHEAD.jspf"%>

</head>


<body>
	<%@ include file="fragments/noScript.jspf"%>
	<%@ include file="fragments/authenticationCheck.jspf"%>
	<%-- Login check --%>
	<%@ include file="fragments/navbar.jspf"%>
	<%-- Navbar --%>

	<header id="${training.id}">Social Sport</header>
	<!-- Über die ID "wissen" die Sätze beim abspeichern zu welchem training sie gehören-->

	<article id="tippBox"> <h3>Tipps:</h3> 
		<p>Deine Sets werden durch das Abschließen automatisch gespeichert!</p>
		<p>Bitte beleibe während einer Trainingssession auf dieser Seite.</p>
		<p>Wenn du weg navigierst wird deine Trainingssession beendet, du kannst aber immer eine neue beginnen!</p>
		<button id="disableTipps">Alles klar!</button>
		</article>

	<main id="exercises"></main>

	<aside id="searchContainer" class="elements">
		<button id="toogleViewButton">Bearbeitungsansicht</button>
		<div id="toggleExerciseAddition">
			<input type="hidden" id="searchBar" />
			<button id="addButton" value="addButton">Übung hinzufügen</button>
			<template id="searchResults">
				<div>
					<div class="searchExerciseName" exercise-name></div>
					<div class="searchMuscleGroup" exercise-group></div>
				</div>
			</template>
			<div id="searchResultContainer"></div>
			<div>
				<h3>Favorisierten Übungen</h3>
				<div id="favoritesContainer"></div>
			</div>
		</div>
	</aside>

	<div class="clear"></div>

	<div id="buttons">
		<button class="button" id="finishWorkout">Workout abschließen</button>
	</div>

	<!--Footer-->
	<%@ include file="fragments/footer.jspf"%>


	<script>
		document.addEventListener("DOMContentLoaded", init);

		function init() {
			// var button = document.getElementById("addButton"); Wird nicht mehr benötigt
			var addSetButton = document.getElementsByClassName("button addSet");
			var removeExerciseButtons = document
					.getElementsByClassName("removeExerciseButton");
			var toogleViewButton = document.getElementById("toogleViewButton");

			//Um die Tipps auszublenden!
			var tippButton = document.getElementById("disableTipps")
			tippButton.addEventListener("click", function(e){

				console.log("CLICK!")
				document.getElementById("tippBox").setAttribute("class", "hidden");

			});
			//--


			//Lukas Edmüller
			toogleViewButton.addEventListener("click", toogleView);

			// document.ElementByName('addButton').submit();

			var finishWorkoutButton = document.getElementById("finishWorkout");

			//document.getElementById("addButton").addEventListener('click', toggleExerciseSearchbar);
			toogleViewButton.addEventListener("click", toggleExerciseSearchbar);
			finishWorkoutButton.addEventListener("click", finishWorkout);
			// document.ElementByName('addButton').submit();
			for (var i = 0; i < addSetButton.length; i++) {
				// Alle "SetButton" mit EventListener überwachen
				addSetButton[i].addEventListener("click", getExerciseReference);
			}

			//Lukas Edmüller
			// Alle removeExerciseButtons mit EventListener versorgen
			for (var i = 0; i < removeExerciseButtons.length; i++) {
				// Alle "SetButton" mit EventListener überwachen
				removeExerciseButtons[i].addEventListener("click",
						removeExercise);
			}

			//Eventlistener für die Checkboxes der Sets
			var checkboxes = document.getElementsByClassName("checkbox");
			for (var i = 0; i < checkboxes.length; i++) {
				// Alle "SetButton" mit EventListener überwachen
				checkboxes[i].addEventListener("change", disableSet);
			}
			// ------

			//https://stackoverflow.com/questions/7317273/warn-user-before-leaving-web-page-with-unsaved-changes

			// window
			// 		.addEventListener(
			// 				"beforeunload",
			// 				function(e) {
			// 					var confirmationMessage = "It looks like you have been editing something. "
			// 							+ "If you leave before saving, your changes will be lost.";
			// 					(e || window.event).returnValue = confirmationMessage; //Gecko + IE
			// 					return confirmationMessage; //Gecko + Webkit, Safari, Chrome etc.
			// 				});

			window.addEventListener("load", loadSavedExercises);

			

			//---
		}
	</script>
</body>

</html>