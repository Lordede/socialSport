/**
 * Cem Durmus
 */

//Starpunkt Users
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

function createUserElements(jsonString) {
    let parentTableElm = document.querySelector("#insertUsers");
    let templateTableRow = document.querySelector("#trTemplate");
    parentTableElm.textContent="";
    let parseJson = JSON.parse(jsonString);
    parseJson.forEach(user => {
        const templateRow = templateTableRow.content.cloneNode(true).children[0];
        parentTableElm.appendChild(templateRow)
        const username = templateRow.querySelector(".username");
        const firstname = templateRow.querySelector(".firstname");
        const lastname = templateRow.querySelector(".lastname");
        const email = templateRow.querySelector(".email")
        username.textContent = user.benutzername;
        firstname.textContent = user.vorname;
        lastname.textContent = user.nachname;
        email.textContent = user.eMail;
        templateRow.addEventListener("click", () => selectUser(user));
    });
}

function selectUser(user) {
    let delButton = document.querySelector("#delUser");
    let adminButton = document.querySelector("#setAdmin");
    let userContainer = document.querySelector("#userContainer");
    if(document.querySelector(".userName"))
    {
        document.querySelector(".userName").remove();
    }
    if(delButton.getAttribute("hidden"))
    {
        delButton.removeAttribute("hidden");
        adminButton.removeAttribute("hidden");
    }
    let userName = document.createElement("div");
    userName.setAttribute("class", "userName");
    userName.textContent = user.benutzername;
    userContainer.appendChild(userName);
    let color = "#1B4332";
    userContainer.style.backgroundColor = color;
    delButton.addEventListener("click", ()  => deleteUser(user));
    adminButton.addEventListener("click", () => makeUserAdmin(user))
}

function deleteUser(user) {
    let request = new XMLHttpRequest;
    request.open("DELETE", "../UserUpdateServlet?id="+user.id, true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send();
    window.location.reload();
}


function makeUserAdmin(user) {
    let request = new XMLHttpRequest;
    request.open("POST", "../UserUpdateServlet", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("setAdmin="+user.id);
    window.location.reload();
}
//Generische Suchfunktion im Interface
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

//Read
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

function createNewExercise() {

    let inputImage = document.querySelector("#image");
    let buttonSubmission = document.querySelector("#submitExercise");
    let inputName = document.querySelector(".nameExercise");
    let idListOfRadioBox = ['c1', 'c2', 'c3', 'c4', 'c5'];
    idListOfRadioBox.forEach(listItem => {
        let radioBox = document.getElementById(listItem);
        if (!inputName) {
            alert("keine gültige Eingaben");
        }

        if (!radioBox.value) {
            let formData = new FormData();
            formData.append("exerciseName=", inputName.value);
            formData.append("muscleGroup", radioBox.value);
            formData.append("image", inputImage.files[0]);
            fetch("ExerciseServlet",
            {
                method: "POST",
                body: formData
            });
        }
    });
}
