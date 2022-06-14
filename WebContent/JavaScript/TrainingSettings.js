//Lukas Edmüller
function toogleView()
{
    var settingButtons = document.getElementById("toggleExerciseAddition");
    var clickedButton = document.getElementById("toogleViewButton");
    var setContainer = document.getElementsByClassName("setContainer");
    if(settingButtons.style.visibility === "visible"){
        settingButtons.style.visibility = "hidden";
        for(let i = 0; i < setContainer.length; i++){
            setContainer.item(i).style.visibility = "visible";
        }
        clickedButton.innerHTML = "Bearbeitungsansicht";
    }
    else{
        settingButtons.style.visibility = "visible";
        for(let i = 0; i < setContainer.length; i++){
            setContainer.item(i).style.visibility = "hidden";
        }
        clickedButton.innerHTML = "Trainingsansicht";
    }

    init();
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

    var headerElement = document.getElementsByTagName("header");
    var trainingId = headerElement[0].id;
    console.log(trainingId);

    var xmlhttpExerciseToTraining = new XMLHttpRequest();
    xmlhttpExerciseToTraining.open("POST", "../ExerciseToTrainingServlet?exerciseId="+exercise.id + "&trainingId=" + trainingId , true);
    xmlhttpExerciseToTraining.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttpExerciseToTraining.send();
	
    // var insertableSearchbar = document.getElementById("searchBar");
    // insertableSearchbar.setAttribute("type", "hidden");
    // var searchItems = document.getElementById("searchResultContainer");
    // searchItems.style.display = "none";
}