
/*
Creates searchbar and opens container for the exercises 
 */
/* let addButton = document.getElementById("addButton");
addButton.addEventListener('click', event => {toggleExerciseSearchbar()}); */
function toggleExerciseSearchbar()
{
    var insertableSearchbar = document.createElement('input');
    insertableSearchbar.setAttribute("id", "searchBar");
    insertableSearchbar.setAttribute("type", "text");
   // insertableSearchbar.setAttribute("click", showExercises);
    var parentElement = document.getElementById("toggleExerciseAddition");
   
    parentElement.appendChild(insertableSearchbar);
    // showExercises;
    //console.log("funktioniert 22");
}

function showExercises()
{
    var exerciseInsert = document.createElement('div');
    exerciseInsert.setAttribute("id", "boxOfExercises");
    parentElement.appendChild(insertableSearchbar);
    // exerciseInsert.innerHTML = CreateExercise.insertExercise();
    // return exerciseInsert;
    
}