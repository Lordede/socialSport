//Erstellt von Cem Durmus
/*
Creates searchbar and opens container for the exercises 
 */
/* let addButton = document.getElementById("addButton");
addButton.addEventListener('click', event => {toggleExerciseSearchbar()}); */
function toggleExerciseSearchbar()
{
    var insertableSearchbar = document.getElementById("searchBar");
    insertableSearchbar.setAttribute("type", "text");
    insertableSearchbar.setAttribute("name", "exerciseInputField");
    var parentElement = document.getElementById("toggleExerciseAddition");
   
    parentElement.appendChild(insertableSearchbar);
    showExercises();
    // showExercises;
    //console.log("funktioniert 22");
    insertableSearchbar.addEventListener("input", event => {
        const input = event.target.value;
        readSearchExercises(input, function(exercisesJson){
        extractExercises(exercisesJson);
    });
})
}

function showExercises()
{
    var exerciseInsert = document.createElement('div');
    exerciseInsert.setAttribute("id", "boxOfExercises");
    // exerciseInsert.innerHTML= extractExercises();
    var exerciseContainer = document.getElementById("toggleExerciseAddition");
    exerciseContainer.appendChild(exerciseInsert);
    readContent();
    // exerciseInsert.innerHTML = CreateExercise.insertExercise();
    // return exerciseInsert;
//    
}

function readContent()
{
    var xmlhttp = new XMLHttpRequest();
    var jsonString;
    xmlhttp.open("GET", "../ExerciseServlet?addButton=name", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function()
    {
		jsonString = xmlhttp.responseText;
		extractExercises(jsonString);
	}
		xmlhttp.send() ;
}
    //extractExercises(jsonString);

function readSearchExercises(exerciseName, callback)
{
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "../ExerciseServlet?exerciseInputField="+exerciseName, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function()
    {
        let exercisesJson = xmlhttp.responseText;
        callback(exercisesJson);
    }
    xmlhttp.send();
}

	
function extractExercises(jsonString)
{
    let exercisesArrayList = JSON.parse(jsonString);
   
        // exercise.forEach(exerciseInput =>{
        //     const isVisible = exerciseInput.name.includes(value);
        //     exerciseInput.element.classList.toggle("hide", !isVisible);
        // })
        
    const searchResults = document.getElementById("searchResults");
    const toggleContainer = document.getElementById("searchResultContainer");
    toggleContainer.style.display = "block";
    toggleContainer.innerHTML = "";
    exercisesArrayList.forEach(exercise => {
        const result = searchResults.content.cloneNode(true).children[0];
        toggleContainer.appendChild(result);
        const header = result.querySelector('.searchExerciseName');
        header.addEventListener("click", () => onExerciseClick(exercise))
        const body = result.querySelector('.searchMuscleGroup');
        header.textContent = exercise.name;
        body.textContent = exercise.muscleGroup;
        console.log(exercise);
   });
   
}

function onExerciseClick(exercise)
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
	
    var insertableSearchbar = document.getElementById("searchBar");
    insertableSearchbar.setAttribute("type", "hidden");
    var searchItems = document.getElementById("searchResultContainer");
    searchItems.style.display = "none";
}

