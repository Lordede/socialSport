function getTraining()
{

}
function readPastTrainings()
{
    var xmlhttp = new XMLHttpRequest();
    var jsonString;
    xmlhttp.open("GET", "../TrainingServlet?loadTrainings=name", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function()
    {
		jsonString = xmlhttp.responseText;
		extractTraining(jsonString);
	}
		xmlhttp.send() ;
}

function extractTraining(jsonString)
{
    let exercisesArrayList = JSON.parse(jsonString);
   
        // exercise.forEach(exerciseInput =>{
        //     const isVisible = exerciseInput.name.includes(value);
        //     exerciseInput.element.classList.toggle("hide", !isVisible);
        // })
        
    const searchResults = document.getElementById("searchPastTrainings");
    const toggleContainer = document.getElementById("trainingsContainer");
    toggleContainer.style.display = "block";
    toggleContainer.innerHTML = "";
    exercisesArrayList.forEach(Training => {
        const result = searchResults.content.cloneNode(true).children[0];
        toggleContainer.appendChild(result);
        const trainingsName = result.querySelector('.name');
        trainingsName.addEventListener("click", () => onExerciseClick(exercise))
       
        trainingsName.textContent = Training.name;
        console.log(exercise);
   });
   
}
function hideSearch()
{
    var xmlhttp = new XMLHttpRequest();
    var inputField = document.getElementById("nameOfTrainingInput");
    inputField.setAttribute("type","text");
    let label = document.getElementById("nameOfTrainingLabel");
    let button = document.getElementById("sendTraining");
    button.addEventListener("click", function(){
        xmlhttp.open("POST", "../TrainingServlet", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    xmlhttp.send("name="+inputField.value+"&points=2");
    })
    let searchButton = document.getElementById("")
    let hidden = label.getAttribute("hidden");
    
    if (hidden) {
       label.removeAttribute("hidden");
       button.removeAttribute("hidden");
    } else {
       label.setAttribute("hidden", "hidden");
       button.setAttribute("hidden","hidden");
    }
    
}