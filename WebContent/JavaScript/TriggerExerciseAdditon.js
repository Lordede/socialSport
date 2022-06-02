
/*
Creates searchbar and opens container for the exercises 
 */
/* let addButton = document.getElementById("addButton");
addButton.addEventListener('click', event => {toggleExerciseSearchbar()}); */
function toggleExerciseSearchbar()
{
    var insertableSearchbar = document.createElement('input');
    insertableSearchbar.setAttribute("id", "searchBar");
    insertableSearchbar.setAttribute("type", "search");
    insertableSearchbar.setAttribute("click", showExercises);
    var parentElement = document.getElementById("searchBar2");
    parentElement.appendChild(insertableSearchbar);
    console.log("funktioniert");
}

function showExercises()
{
    var exerciseInsert = document.createElement('div');
    exerciseInsert.setAttribute("id", "boxOfExercises");
    exerciseInsert.innerHTML = CreateExercise.insertExercise();
    var parent = document.getElementById("exerciseContainer");
    parent.appendChild(exerciseInsert);
    
}