/**
 * Cem Durmus
 */

//Starpunkt Users
function readUsers() {

    var request = new XMLHttpRequest();
    request.open("Get", "../UserUpdateServlet?getUsers")
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.onload = function () {
        usersJson = request.responseText;
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
    let parentTableElm = document.querySelector("#userTable");
    let templateTableRow = document.querySelector("#trTemplate");
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
        selectUser(user);
    });
}

function selectUser(user) {
    let userContainer = document.querySelector("#userContainer");
    let userName = document.createElement("div");
    userName.setAttribute("id", "userName");
    userName.textContent = user.username;
    let deleteUser = document.createElement("button");
    deleteUser.setAttribute("id", "delUser");
    deleteUser.textContent = "Benutzer Löschen";
    setAdmin.addEventListener("click", makeUserAdmin(user));
    deleteUser.addEventListener("click", deleteUser(user));
    let setAdmin = document.createElement("button");
    setAdmin.setAttribute("id", "setAdmin");
    setAdmin.textContent = "Adminrechte vergeben";
    userName.append(deleteUser);
    userName.appendChild(setAdmin);
    userContainer.appendChild(userName);
}
function deleteUser(user) {
    let request = new XMLHttpRequest;
    request.open("DELETE", "../UserUpdateServlet", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("setAdmin");
}


function makeUserAdmin(user) {
    let request = new XMLHttpRequest;
    request.open("POST", "../UserUpdateServlet", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("setAdmin");
}
//Generische Suchfunktion im Interface
function searchAdminUi(servletname, nameOfInputField, searchInput, callback) {
    let request = new XMLHttpRequest;
    let extractedJson = ""
    request.open("GET", "../" + servletname + "?" + nameOfInputField + "=" + searchInput, true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.onload = function () {
        if (nameOfInputField == "exerciseInputBar") {
            extractedJson = request.responseText;
            callback(extractedJson);
        } else {
            extractedJson = request.responseText;
            callback(extractedJson)
        }

    }
}

//Startpunkt Exercise
function readExercises() {
    var xmlhttp = new XMLHttpRequest();
    var jsonString;
    let exerciseInputBar = document.querySelector("#searchExercises");
    xmlhttp.open("GET", "../ExerciseServlet?addButton=name", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function () {
        jsonString = xmlhttp.responseText;
        listAllExercises(jsonString);
    }
    xmlhttp.send();

    exerciseInputBar.addEventListener("input", event => {
        const input = event.target.value;
        searchAdminUi("ExerciseServlet", "exerciseInputBar", input, function (exerciseJson) {
            listAllExercises(exerciseJson);
        });
    });
}

//Read
function listAllExercises(jsonString) {
    let exercisesArrayList = JSON.parse(jsonString);

    let tableRowTemplateExercise = document.querySelector("#trExercise");
    let containerTable = document.querySelector("#exerciseContainer");
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
            // xmlhttp.open("POST", "../ExerciseServlet", true);
            // xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            // xmlhttp.send("exerciseName=" + inputName.value + "&muscleGroup=" + radioBox.value);// + "&image=" +inputImage.value
        }
    });
}
