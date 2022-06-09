function createUserElements() {
    let parentTableElm = document.querySelector("#userTable");
    let templateTableRow = document.querySelector("#trTemplate");

    //let buttonLoadUsers = document.querySelector(".loadUsers");

    var request = new XMLHttpRequest();
    let usersJson = "";
    request.open("Get", "../UserUpdateServlet?getUsers")
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.onload = function () 
    {
        usersJson = request.responseText;
        let parseJson = JSON.parse(usersJson);
        parseJson.forEach(user => 
        {
            const tableRowElm = templateTableRow.content.cloneNode(true).children[0];
            const username = tableRowElm.querySelector(".username");
            const firstname = tableRowElm.querySelector(".firstname");
            const lastname = tableRowElm.querySelector(".lastname");
            const email = tableRowElm.querySelector(".email")
            username.textContent = user.username;
            firstname.textContent = user.firstname;
            lastname.textContent = user.lastname;
            email.textContent = user.eMail;
            parentTableElm.appendChild(tableRowElm)
        })
    }
    request.send();
}

function listAllExercises(jsonString) {
    let exercisesArrayList = JSON.parse(jsonString);

    // exercise.forEach(exerciseInput =>{
    //     const isVisible = exerciseInput.name.includes(value);
    //     exerciseInput.element.classList.toggle("hide", !isVisible);
    // })

    const searchResults = document.getElementById("searchResults");
    const toggleContainer = document.getElementById("searchResultContainer");
    toggleContainer.style.display = "block";
    toggleContainer.innerHTML = "";
    exercisesArrayList.forEach(exercise => {
        const result = searchResults.content.cloneNode(true).children[0];
        toggleContainer.appendChild(result);
        const header = result.querySelector('.searchExerciseName');
        header.addEventListener("click", () => onExerciseClick(exercise))
        const body = result.querySelector('.searchMuscleGroup');
        header.textContent = exercise.name;
        body.textContent = exercise.muscleGroup;
        console.log(exercise);
    });
}

function readExercises() {
    var xmlhttp = new XMLHttpRequest();
    var jsonString;
    xmlhttp.open("GET", "../ExerciseServlet?addButton=name", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function () {
        jsonString = xmlhttp.responseText;
        extractExercises(jsonString);
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
