
/*
Creates searchbar and opens container for the exercises 
 */
/* let addButton = document.getElementById("addButton");
addButton.addEventListener('click', event => {toggleExerciseSearchbar()}); */
/*function toggleExerciseSearchbar()
{
    var insertableSearchbar = document.createElement('input'); 	//<input>
    insertableSearchbar.setAttribute("id", "searchBar");		//<input id="searchbar">
    insertableSearchbar.setAttribute("type", "search");			//..
    //insertableSearchbar.setAttribute("click", showExercises);	//..
    var parentElement = document.getElementById("searchBar2");
    parentElement.appendChild(insertableSearchbar);
    console.log("funktioniert");
}

function showDefinedExercises()
{
    var exerciseInsert = document.createElement('div');
    exerciseInsert.setAttribute("id", "boxOfExercises");
    exerciseInsert.innerHTML = CreateExercise.insertExercise();
    var parent = document.getElementById("exerciseContainer");
    parent.appendChild(exerciseInsert);
    
}
*/

function getDefinedExercises() // Hier sollen die Übungen aus der Datenbak gezogen werden.
{
    var exercises
    exercises = ["Bankdrücken" ,"Dip","Klimmzug"] //TODO: Nicht hardcoden, sondern zugriff aus DB!

    return exercises;
}

