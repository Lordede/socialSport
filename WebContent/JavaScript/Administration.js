/**
 * Cem Durmus
 */

/**
 * Auslesen aller User mittels Ajax aufruf,
 * weiterleitung der Search Method
 */
function readUsers() {
    var request = new XMLHttpRequest();
    request.open("Get", "../UserUpdateServlet?getUsers")
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.onload = function () {
        let usersJson = request.responseText;
        createUserElements(usersJson);
    }
    request.send();
    let searchBarUsers = document.querySelector("#searchInput");
    searchBarUsers.addEventListener("input", event => {
        const input = event.target.value;
        searchAdminUi("UserUpdateServlet", "searchUser", input, function (jsonString) {
            createUserElements(jsonString);
        });
    });
    
}
/**
 * Füllung der User Tabelle
 * @param {String} jsonString: antwort des Servers welcher als Json String gesendet wird
 * Template wird hier ausgefüllt und ebenefalls klickbar gemacht für die weiterleitung an 
 * selectUser
 */
function createUserElements(jsonString) {
    let parentTableElm = document.querySelector("#insertUsers");
    let templateTableRow = document.querySelector("#trTemplate");
    parentTableElm.textContent="";
    let parseJson = JSON.parse(jsonString);
    parseJson.forEach(user => {
        const templateRow = templateTableRow.content.cloneNode(true).children[0];
        templateRow.setAttribute("class", "user");
        parentTableElm.appendChild(templateRow)
        const username = templateRow.querySelector(".username");
        const firstname = templateRow.querySelector(".firstname");
        const lastname = templateRow.querySelector(".lastname");
        const email = templateRow.querySelector(".email")
        username.textContent = user.benutzername;
        firstname.textContent = user.vorname;
        lastname.textContent = user.nachname;
        email.textContent = user.eMail;
        templateRow.addEventListener("click", function()
        {
            selectUser(user);
            templateRow.remove();
        });
    });
}

/**
 * Auswahl des Users zur weiteren Verarbeiten
 * @param {Object} user
 * dabei wird ein User in einen dafür vorgesehenen Container geladen und 
 * wird zusammen mit den Buttons und dem Text geladen 
 */
function selectUser(user) {
    let delButton = document.querySelector("#delUser");
    let adminButton = document.querySelector("#setAdmin");
    let userContainer = document.querySelector("#userContainer");
    let emptyBox = document.getElementById("boxLeeren");
    if(document.querySelector(".userName"))
    {
        if(document.querySelector(".userName").textContent==user.benutzername)
        {
            document.querySelector(".userName").remove();
        }
        
    }
    if(delButton.getAttribute("hidden"))
    {
        delButton.removeAttribute("hidden");
        adminButton.removeAttribute("hidden");
        emptyBox.removeAttribute("hidden");
    }
    let userName = document.createElement("div");
    userName.setAttribute("class", "userName");
    userName.textContent = user.benutzername;
    userContainer.appendChild(userName);
    let color = "#1B4332";
    userContainer.style.backgroundColor = color;
    delButton.addEventListener("click", ()  => deleteUser(user));
    adminButton.addEventListener("click", () => makeUserAdmin(user));
    emptyBox.addEventListener("click",() => window.location.reload());
}
/**
 * Ajax aufruf an den Server um den user zu Löschen und anschließend wird die
 * Seite geladen on Load.
 * @param {Object} user 
 */
function deleteUser(user) {
    let request = new XMLHttpRequest;
    request.open("DELETE", "../UserUpdateServlet?id="+user.id, true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send();
    request.onload = function ()
    {
        window.location.reload();
    }
    
}

/**
 * Ajax aufruf an den Server um den user zu admin und anschließend wird die
 * Seite geladen onLoad.
 * @param {Object} user 
 */
function makeUserAdmin(user) {
    let request = new XMLHttpRequest;
    request.open("POST", "../UserUpdateServlet", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.onload = function ()
    {
        window.location.reload();
    }
    request.send("setAdmin="+user.id);
}
/**
 * Generische Suchfunktion im Interface 
 * @param {String} servletname: um zwischen dem ExerciseServlet und dem UserUpdateServlet zu unterscheiden  
 * @param {String} nameOfInputField: ebenfalls um die richtigen Parameter im Servlet anzusprechen
 * @param {String} searchInput: inhalt des Input-Feldes
 * @param {String} callback: die suchfunktion schickt einen gebenen input zum Servlet, welcher dann anschließend die 
 *                            erhaltene Antwort an die extractions-Funktion weiterleitet, die dann angepasst zur Suche neue Items zeigt
 * 
 */

function searchAdminUi(servletname, nameOfInputField, searchInput, callback) {
    let request = new XMLHttpRequest;
    let extractedJson = ""
    request.open("GET", "../" + servletname + "?" + nameOfInputField + "=" + searchInput);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.onload = function () {
        extractedJson = request.responseText;
        callback(extractedJson);
    }
    request.send();
}

//Startpunkt Exercise
/**
 * Startpuntk der Exercise Extraktion welche den JsonString des Servers erhält und diese dann an 
 * die weitern Funktionen weitergibt.
 */
function readExercises() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "../ExerciseServlet?addButton");
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function () {
        let jsonString = xmlhttp.responseText;
        listAllExercises(jsonString);
    }
    
    xmlhttp.send();
	let exerciseInputBar = document.querySelector("#searchExercises");
    exerciseInputBar.addEventListener("input", event => {
        const input = event.target.value;
        searchAdminUi("ExerciseServlet", "exerciseInputField", input, function (exerciseJson) {
            listAllExercises(exerciseJson);
        });
    });
}

/**
 * Füllung der Exercise Tabelle
 * @param {String} jsonString: antwort des Servers welcher als Json String gesendet wird
 * Template wird hier ausgefüllt für die Anzeige
 */
function listAllExercises(jsonString) {
    let exercisesArrayList = JSON.parse(jsonString);
    let tableRowTemplateExercise = document.querySelector("#trExercise");
    let containerTable = document.querySelector("#insertExercises");
    containerTable.innerHTML = "";
    exercisesArrayList.forEach(exercise => {
        let templateRowExercise = tableRowTemplateExercise.content.cloneNode(true).children[0]; //Table row
        containerTable.appendChild(templateRowExercise);
        const exerciseName = templateRowExercise.querySelector(".exerciseName");
        const muscleGroup = templateRowExercise.querySelector(".muscleGroup");
        const image = templateRowExercise.querySelector(".picture");
        exerciseName.textContent = exercise.name;
        muscleGroup.textContent = exercise.muscleGroup;
        //exercise.image = exercise.image;
    });
}
