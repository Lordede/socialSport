
//Lukas Edmüller
function toogleView()
{
    var settingButtons = document.getElementById("toggleExerciseAddition");
    var clickedButton = document.getElementById("toogleViewButton");
    var setContainer = document.getElementsByClassName("setContainer");
    var removeExerciseButtons = document.getElementsByClassName("removeExerciseButton");
    var exercises = document.getElementsByClassName("exercise");

    var imgs = document.getElementsByTagName("img")

    if(settingButtons.style.visibility === "visible"){
        settingButtons.style.visibility = "hidden";
        for(let i = 0; i < setContainer.length; i++){
            setContainer.item(i).style.visibility = "visible";
            removeExerciseButtons.item(i).style.visibility = "hidden";
            exercises.item(i).style.height = "auto";
        }

        for(let i = 0; i < imgs.length; i++){
            imgs.item(i).style.visibility = "visible";
        }

        document.getElementById("searchContainer").style.borderStyle = "hidden";
        document.getElementById("searchContainer").style.height = "50px";
        clickedButton.innerHTML = "Bearbeitungsansicht";
    }
    else{
        settingButtons.style.visibility = "visible";
        for(let i = 0; i < setContainer.length; i++){
            setContainer.item(i).style.visibility = "hidden";
            removeExerciseButtons.item(i).style.visibility = "visible";
            exercises.item(i).style.height = "150px";
        }

        for(let i = 0; i < imgs.length; i++){
            imgs.item(i).style.visibility = "hidden";
        }

        
        document.getElementById("searchContainer").style.borderStyle = "solid";
        document.getElementById("searchContainer").style.height = "auto";
        clickedButton.innerHTML = "Trainingsansicht";
    }

    init();
}

function onExerciseClick(exercise)
{
	var check = document.getElementsByName(exercise.name)
	if(check.length === 0)
    {
	    addExercise(exercise, false); // HTML DOM Manipulation 
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

function loadSavedExercises(){
    var headerElement = document.getElementsByTagName("header");
    var trainingId = headerElement[0].id;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "../ExerciseToTrainingServlet?trainingId=" + trainingId, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function ()
    {
        let json = xmlhttp.responseText;
        loadExercises(json);
    }
    xmlhttp.send();

}

function loadExercises(json)
{
    let parseString = JSON.parse(json);
    parseString.forEach(exercise => {
        addExercise(exercise, true);
    });
}

function removeExercise(){
    let exerciseId = this.parentNode.getAttribute("id"); 
    let header = document.getElementsByTagName("header");
    let trainingId = header[0].getAttribute("id");

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("DELETE", "../ExerciseToTrainingServlet?trainingId=" + trainingId + "&exerciseId=" + exerciseId, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    // xmlhttp.onload = function ()
    // {
        
    // }
    xmlhttp.send();
    let exercises = document.getElementsByClassName("exercise elements");
        for(let i = 0; i < exercises.length; i++){
            if(exercises[i].getAttribute("id") === exerciseId){
                let removedElement = exercises[i];
                removedElement.parentNode.removeChild(removedElement);
            }
        }
}