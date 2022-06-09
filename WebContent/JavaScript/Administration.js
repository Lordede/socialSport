function createUserElements()
{   
    let parentTableElm = document.querySelector("#userTable");
    let templateTableRow = document.querySelector("#trTemplate");

    let buttonLoadUsers = document.querySelector(".loadUsers");
    
        var request = new XMLHttpRequest();
        let usersJson = "";
        request.open("Get", "../UserUpdateServlet?getUsers")
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.onload = function()
        {
            usersJson = request.responseText;
            let parseJson = JSON.parse(usersJson);
            parseJson.forEach(user => {
                let tableRowElm = document.createElement("tr");
                const templateRow = templateTableRow.content.cloneNode(true).children[0];
                tableRowElm.appendChild(templateRow);
                const username = templateRow.querySelector(".username");
                tableRowElm.appendChild(username);
                const firstname = templateRow.querySelector(".firstname");
                tableRowElm.appendChild(firstname);
                const lastname = templateRow.querySelector(".lastname");
                tableRowElm.appendChild(lastname);
                const email = templateRow.querySelector()
                tableRowElm.appendChild(email);
                username.textContent=user.username;
                firstname.textContent=user.firstname;
                lastname.textContent=user.lastnamen;
                email.textContent=user.eMail;
                parentTableElm.appendChild(tableRowElm)
            })
        }
        request.send();
        
}

function listAllExercises(jsonString)
{
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
  
function readExercises()
{
    var xmlhttp = new XMLHttpRequest();
    var jsonString;
    xmlhttp.open("GET", "../ExerciseServlet?addButton=name", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onload = function()
    {
        jsonString = xmlhttp.responseText;
        extractExercises(jsonString);
    }
        xmlhttp.send() ;
}

function createNewExercise()
{
    var xmlhttp = new XMLHttpRequest();
    let inputName = document.querySelector(".nameExercise");
    let inputCheckBox1 = document.querySelector("#c1");
    let inputCheckBox2 = document.querySelector("#c2");
    let inputCheckBox3 = document.querySelector("#c3");
    let inputCheckBox4 = document.querySelector("#c4");
    let inputCheckBox5 = document.querySelector("#c5");
    let inputImage = document.querySelector("#image");
    let buttonSubmission = document.querySelector("#submitExercise");
    if(input.name === '')
    {
        alert("keine gültige Eingaben");
    } else 
    {
        if (inputCheckBox1.value != null)
        {
            xmlhttp.open("POST", "../ExerciseServlet", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("name="+input.value+"&points=2");
        }
        if (inputCheckBox2.value != null)
        {
            xmlhttp.open("POST", "../ExerciseServlet", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("name="+input.value+"&points=2");
        }
        if (inputCheckBox3.value != null)
        {
            xmlhttp.open("POST", "../ExerciseServlet", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("name="+input.value+"&points=2");
        }
        if (inputCheckBox4.value != null)
        {
            xmlhttp.open("POST", "../ExerciseServlet", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("name="+input.value+"&points=2");
        }
        if (inputCheckBox5.value != null)
        {
            xmlhttp.open("POST", "../ExerciseServlet", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("name="+input.value+"&points=2");
        }
    }
}