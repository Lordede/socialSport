/**
 * Cem Durmus
 * Im Trainingsmenü verwendete Funktionen
 * */

/**
 * Auslesen der abgeschlossenen Trainings, aus der Datenbank mittels eines AJAX aufrufes, welcher ein Json-String
 * von dem TrainingServlet erhält. Dabei wir auch die Suchleiste abgehört und die Eingabe eines Zeichens festzustellen und dann startet die
 * Suchfunktion welche ein callback enthält.
 */

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
/**
 * erhält einen JsonString, welcher anschließend geparsed wird um anschließend ein Array darzustellen.
 * woraus dann mittels einer forEach-loop das Template - trainingscontainer- gefüllt wird.
 * @param {String} jsonString 
 * Ein Training ist klickbar und führt zum aufruf der SelectTrainings methode
 */

function extractTraining(jsonString) {
    let trainingsList = JSON.parse(jsonString);
    const searchResults = document.querySelector(".searchResults");
    const toggleContainer = document.querySelector(".trainingsContainer");
    toggleContainer.style.display = "block";
    toggleContainer.innerHTML = "";
    trainingsList.forEach(training => {
        const trainingsNameElm = searchResults.content.cloneNode(true).children[0];
        toggleContainer.appendChild(trainingsNameElm);
        trainingsNameElm.addEventListener("click", () => selectTraining(training));
        trainingsNameElm.textContent = training.name;
    });

}
/**
 * für dazu, dass ein neues Training ausgewählt wird und als Trainingssession in der Datenbank hinterlegt wird
 * @param {Object} training 
 */
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

}
/**
 * versteckt das input-Feld und den Button zum erstellen eines neuen Trainings.
 * Dabei wird nach der Aufdeckung die Möglichkeit gefördert, ein Training anzulegen,
 * mit einem selbsterwähltem Namen. zuletzt wir durch den Button-click das Training
 * and die sendTrainingDB function übermittelt
 */
function hideSearch() {
    let label = document.getElementById("nameOfTrainingLabel");
    let button = document.getElementById("sendTraining");
    let hidden = label.getAttribute("hidden");
    var inputField = document.getElementById("nameOfTrainingInput");
    inputField.setAttribute("type", "text");
    if (hidden) {
        label.removeAttribute("hidden");
        inputField.removeAttribute("hidden");
        button.removeAttribute("hidden");
    }
    else {
        label.setAttribute("hidden", "hidden");
        inputField.setAttribute("hidden", "hidden");
        button.setAttribute("hidden", "hidden");
    }
    button.addEventListener("click", () => sendTrainingDB(inputField));

}
/**
 * in dieser Funktion wird ein AJAX aufruf gestartet, wo im voraus geprüft wird ob es 
 * sich um eine leere Angabe handelt. Hier wird ebenfalls eine Trainingssession erstellt und auf 
 * das Trainings interface weitergeleitet.
 * @param {String} inputField 
 */
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
        xmlhttp.send("name=" + input.value);


    }

}

/**
 * hier wird geprüft ob es sich bei dem mitgegebenen String um einen handelt, welcher ein Leerzeichen enthält
 * @param {String} s 
 * @returns {boolean}
 */
function hasWhiteSpace(s) {
    return s.indexOf(' ') >= 0;
}

/**
 *  Die suchfunktion schickt einen gebenen input zum TrainingsServlet, welcher dann anschließend die 
 *  erhaltene Antwort an die extractions-Funktion weiterleitet, die dann angepasst zur Suche neue Trainings zeigt. 
 * */
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

