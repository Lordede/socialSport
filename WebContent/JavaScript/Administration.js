/**
 * Cem Durmus
 */
function createUserElements() {
    let parentTableElm = document.querySelector("#userTable");
    let templateTableRow = document.querySelector("#trTemplate");

    let usersJson = "";
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.onload = function () {
        usersJson = request.responseText;
        let parseJson = JSON.parse(usersJson);
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
        })
    }
    request.send();
}

function listAllExercises(jsonString) {
    let exercisesArrayList = JSON.parse(jsonString);
    let tableRowTemplateExercise = document.querySelector("#trExercise");
    let containerTable = document.querySelector("#exerciseContainer");

    exercisesArrayList.forEach(exercise => 
    {
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

function readExercises() {
    var xmlhttp = new XMLHttpRequest();
    var jsonString;
    xmlhttp.open("GET", "../ExerciseServlet?addButton=name", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function () {
        jsonString = xmlhttp.responseText;
        listAllExercises(jsonString);
    }
    xmlhttp.send();
}


function createNewExercise() {
    var xmlhttp = new XMLHttpRequest();
    let inputName = document.querySelector(".nameExercise");

    if (!inputName) {
        return alert("keine gültige Eingaben");
    }

    const checkboxIds = ['c1', 'c2', 'c3', 'c4', 'c5'];
    for (const checkboxId of checkboxIds) {
        const checkboxElem = document.getElementById(checkboxId);
        if (!checkboxElem.value) continue;

        const label = document.querySelector(`label[for=${checkboxId}]`).textContent;
        xmlhttp.open("POST", "../ExerciseServlet", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("name=" + label + "&points=2");
    }
}
