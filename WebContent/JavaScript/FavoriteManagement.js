/**
 * 
 * Cem Durmus 
 * 
 * */
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

function extractFavorites(jsonString)
{
    let exercisesArrayList = JSON.parse(jsonString) //[{"":""},{"rep":"3"}]
    const searchResults = document.getElementById("favoriteExercises");
    const toggleContainer = document.getElementById("favoritesContainer");
    toggleContainer.style.display = "block";
    toggleContainer.innerHTML = "";
    exercisesArrayList.forEach(exercise => {
        const result = searchResults.content.cloneNode(true).children[0];
        toggleContainer.appendChild(result);
        const header = result.querySelector('.searchExerciseName');
        header.addEventListener("click", () => onExerciseClick(exercise))
        header.textContent = exercise.name; //set.rep
        console.log(exercise);
   });
}
function onExerciseClick(exercise)
{
	let delButton = document.getElementById("delExercise");
    let exerciseHandleContainer = document.querySelector("#handleExercise");
    let hiddenProperty = delButton.getAttribute("hidden");
    let color = "#1B4332";
    if(hiddenProperty)
    {
        delButton.removeAttribute("hidden");
        exerciseHandleContainer.style.backgroundColor = color;
    }
    let exerciseContainer = document.querySelector("#exerciseContainer");
    exerciseContainer.textContent = exercise.name;
    delButton.addEventListener("click", ()  => deleteExercise(exercise));

}


function deleteExercise(exercise) {
    let request = new XMLHttpRequest;
    request.open("DELETE", "../FavoriteExerciseServlet?name="+exercise.name, true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.onload = function()
    {
        reloadWindow();
    }
    
    request.send();
}
function reloadWindow()
{
    location.reload();
}