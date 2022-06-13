//Lukas Edmüller
function toogleView()
{
    var settingButtons = document.getElementById("toggleExerciseAddition");
    var clickedButton = document.getElementById("toogleViewButton");
    if(settingButtons.style.visibility === "visible"){
        settingButtons.style.visibility = "hidden";
        clickedButton.innerHTML = "Bearbeitungsansicht";
    }
    else{
        settingButtons.style.visibility = "visible";
        clickedButton.innerHTML = "Trainingsansicht";
    }
}

function onExerciseClick1(exercise)
{
	var check = document.getElementsByName(exercise.name)
	if(check.length === 0)
    {
	    addExercise(exercise); // HTML DOM Manipulation 
    }
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "../ExerciseServlet?selectedExercise="+exercise.id, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send();

    var xmlhttpExerciseToTraining = new XMLHttpRequest();
    xmlhttpExerciseToTraining.open("POST", "../ExerciseToTrainingServlet?exerciseId="+exercise.id + "&trainingId=" + 1 , true);
    xmlhttpExerciseToTraining.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttpExerciseToTraining.send();
	
    var insertableSearchbar = document.getElementById("searchBar");
    insertableSearchbar.setAttribute("type", "hidden");
    var searchItems = document.getElementById("searchResultContainer");
    searchItems.style.display = "none";
}