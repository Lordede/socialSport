//Erstellt von Cem Durmus
/** 
 * Eingabeleiste zum Suchen von Übungen welche die Übungssuche steuert mit der weitergabe
 * an die readSearchExercises
 * 
 * */

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
    readFavoriteExercises();
    insertableSearchbar.addEventListener("input", event => {
        const input = event.target.value;
        readSearchExercises(input, function(exercisesJson){
        extractExercises(exercisesJson);
    });
})

document.getElementById("addButton").style.visibility = "hidden"

}

function finishWorkout(){
    window.location = 'trainingsmenu.jsp';
}

/**
 * erstellung des boxofexercises div-containers welche dann durch die readContent() funktion gefüllt wird.
 */
function showExercises()
{
    var exerciseInsert = document.createElement('div');
    exerciseInsert.setAttribute("id", "boxOfExercises");
    var exerciseContainer = document.getElementById("toggleExerciseAddition");
    exerciseContainer.appendChild(exerciseInsert);
    readContent();
    // exerciseInsert.innerHTML = CreateExercise.insertExercise();
    // return exerciseInsert;
//    
}

/**
 * Initialer Ajax beim aufruf der Suche zum erhalten der Antwort des Servers als jsonstring
 */
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

/**
 *  Die suchfunktion schickt einen gebenen input zum ExerciseServlet, welcher dann anschließend die 
 *  erhaltene Antwort an die extractions-Funktion weiterleitet, die dann angepasst zur Suche neue Trainings zeigt. 
 * */
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
/**
 * Funktion zum auslesen der favorisierten Übungen
 */
function readFavoriteExercises(){
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "../FavoriteExerciseServlet", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function()
    {
        let exercisesJson = xmlhttp.responseText;
        extractFavorites(exercisesJson);
    }
    xmlhttp.send();
}
/**
 * Füllung der favorisierten Übungen
 * @param {String} jsonString: antwort des Servers welcher als Json String gesendet wird
 * Template wird hier ausgefüllt für die Anzeige
 */
function extractFavorites(jsonString)
{
    let exercisesArrayList = JSON.parse(jsonString) //[{"":""},{"rep":"3"}]
    const searchResults = document.getElementById("searchResults");
    const toggleContainer = document.getElementById("favoritesContainer");
    exercisesArrayList.forEach(exercise => {
        const result = searchResults.content.cloneNode(true).children[0];
        result.setAttribute("class","resultItem");
        toggleContainer.appendChild(result);
        const header = result.querySelector('.searchExerciseName');
        header.addEventListener("click", () => onExerciseClick(exercise))
        const body = result.querySelector('.searchMuscleGroup');
        header.textContent = exercise.name; //set.rep
        body.textContent = exercise.muscleGroup;
        console.log(exercise);
   });
   
}
	
/**
 * Füllung der Übungen
 * @param {String} jsonString: antwort des Servers welcher als Json String gesendet wird
 * Template wird hier ausgefüllt für die Anzeige
 */
function extractExercises(jsonString)
{
    let exercisesArrayList = JSON.parse(jsonString) //[{"":""},{"rep":"3"}]
    const searchResults = document.getElementById("searchResults");
    const toggleContainer = document.getElementById("searchResultContainer");
    toggleContainer.style.display = "block";
    toggleContainer.innerHTML = "";
    exercisesArrayList.forEach(exercise => {
        const result = searchResults.content.cloneNode(true).children[0];
        result.setAttribute("class","resultItem");
        toggleContainer.appendChild(result);
        const header = result.querySelector('.searchExerciseName');
        header.addEventListener("click", () => onExerciseClick(exercise))
        const body = result.querySelector('.searchMuscleGroup');
        header.textContent = exercise.name; 
        body.textContent = exercise.muscleGroup;
        console.log(exercise);
        
   });
   
}