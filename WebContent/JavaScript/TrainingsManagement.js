/**Cem Durmus */
function readPastTrainings()
{

    var xmlhttp = new XMLHttpRequest();
    var jsonString;
    xmlhttp.open("GET", "../TrainingServlet?loadTrainings=name", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function () {
        jsonString = xmlhttp.responseText;
        extractTraining(jsonString);
    }
    xmlhttp.send();
    let searchBarPastExercises = document.getElementById("searchBarTraining");
    searchBarPastExercises.addEventListener("input", event => {
        const input = event.target.value;
        searchTraining(input, function (trainingJson) {
            extractTraining(trainingJson);
        });
    });
}

function extractTraining(jsonString) {
    let trainingsList = JSON.parse(jsonString);
    const searchResults = document.querySelector(".searchResults");
    const toggleContainer = document.querySelector(".trainingsContainer");
    toggleContainer.style.display = "block";
    toggleContainer.innerHTML = "";
    trainingsList.forEach(training => {
        const trainingsNameElm = searchResults.content.cloneNode(true).children[0];
        toggleContainer.appendChild(trainingsNameElm);
        console.log(trainingsNameElm);
        trainingsNameElm.addEventListener("click", () => selectTraining(training));
        trainingsNameElm.textContent = training.name;
    });

}
function selectTraining(training) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "../TrainingServlet?selectedTraining=" + training.id, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function () {
        var text = xmlhttp.responseText;
        // window.location = "training.jsp";
    }
    xmlhttp.send();

    //Edmüller Lukas
    var responsePost;
    var xmlhttpTrainingSessionPost = new XMLHttpRequest();
    xmlhttpTrainingSessionPost.open("POST", "../TrainingSessionServlet?trainingId=" + training.id, true);
    xmlhttpTrainingSessionPost.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttpTrainingSessionPost.onload = function () {
        responsePost = xmlhttp.responseText;
        window.location = "training.jsp";
    }
    xmlhttpTrainingSessionPost.send();
    //Ende Edmüller Lukas
    xmlhttp.send();

    // Trainingsession wird bei POst bereits in SessionScope geladen, daher GET nicht nötig
    // var xmlhttpTrainingSessionGet = new XMLHttpRequest();
    // xmlhttpTrainingSessionGet.open("GET", "../TrainingSessionServlet?id=" + parseInt(responsePost), true);
    // xmlhttpTrainingSessionGet.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    // xmlhttpTrainingSessionGet.onload = function()
    // {
    //     var text = xmlhttp.responseText;
    //     window.location = "training.jsp";
    // }
    // xmlhttpTrainingSessionGet.send();
}

function hideSearch() {
    let label = document.getElementById("nameOfTrainingLabel");
    let button = document.getElementById("sendTraining");
    let hidden = label.getAttribute("hidden");
    var inputField = document.getElementById("nameOfTrainingInput");
    inputField.setAttribute("type", "text");
    if (hidden) {
        label.removeAttribute("hidden");
        button.removeAttribute("hidden");
    }
    else {
        label.setAttribute("hidden", "hidden");
        button.setAttribute("hidden", "hidden");
    }
    button.addEventListener("click", () => sendTrainingDB(inputField));

}
function sendTrainingDB(inputField) {
    var xmlhttp = new XMLHttpRequest();
    var input = inputField;
    if (hasWhiteSpace(input.value) || input.value === '') {
        alert("keine gültige Eingaben");
    } else {
        xmlhttp.open("POST", "../TrainingServlet", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.onload = function () {
            var trainingId = xmlhttp.responseText;

            //Edmüller Lukas
            var xmlhttpTrainingSessionPost = new XMLHttpRequest();
            xmlhttpTrainingSessionPost.open("POST", "../TrainingSessionServlet?trainingId=" + trainingId, true);
            xmlhttpTrainingSessionPost.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttpTrainingSessionPost.onload = function () {
                responsePost = xmlhttp.responseText;
                window.location = "training.jsp";
            }
            xmlhttpTrainingSessionPost.send();

            xmlhttp.send();
            //window.location = "training.jsp";
        }
        xmlhttp.send("name=" + input.value + "&points=2");


    }





}
function hasWhiteSpace(s) {
    return s.indexOf(' ') >= 0;
}


function searchTraining(trainingName, callback) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "../TrainingServlet?exerciseInputField=" + trainingName, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function () {
        let trainingsJson = xmlhttp.responseText;
        callback(trainingsJson);
    }
    xmlhttp.send();
}

