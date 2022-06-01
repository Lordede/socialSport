
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
    insertableSearchbar.setAttribute("onkeydown", showExercises());
    var parentElement = document.getElementById("sucheStarten");
    parentElement.appendChild(insertableSearchbar);
}

function showExercises()
{
    var exerciseInsert = document.createElement('div');
    insertableSearchbar.setAttribute("id", "boxOfExercises");
    var parent = document.getElementById("exerciseContainer");
    parent.appendChild(exerciseInsert);
    
}