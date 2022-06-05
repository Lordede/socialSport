"use strict";

/*Creates the exercise with table dynamically */
function insertExercise() {
    var counter = 1;
    var insertableBlock;
    var setData = "${set}";
    var exerciseName = "${exercise.name}";
    var muscleGroup = "${exercise.muscleGroup"
    var img = "${exercise.img}"
    var parent = document.getElementById("exerciseContainer");

    insertableBlock = document.createElement('div');
    insertableBlock.setAttribute("id", "exercise" + counter(counter))
    insertableBlock.innerHTML = '<h2>' + exerciseName + '</h2>' +
        '<h3>' + '(' + muscleGroup + ')' + '</h3>' +
        loadTable;
    parent.appendChild(insertableBlock);


}
var setData = []; //Daten vom Set

function loadTable() //Content injetion
{
    var counter = 3;
    var exerciseTable = document.createElement('table');
    var parent = document.getElementById('exercise');

    exerciseTable.setAttribute("id", "exerciseTable" + counter(counter)); //get specific Table
    let dataHtml = ''; //append html data as string

    window.onload = () =>  //initialisierung des windowobjects wobei die tableData (aus html) geladen wird

    {
        loadTableData(setData);
    }

    for (let set of setData) {
        dataHtml += '<tr><td>${set.no}</td><td>${set.rep}</td><td>${set.kg}</tr>';
    }
    tableBody.innerHTML = dataHtml; //initialisierung default Belegung DOM
    parent.appendChild('exercise');
}

// author: Hubertus

/*
addExercise fügt eine neue Übung ein, bei der man dann x belibige Setze trainieren kann
*/
function addExercise() {

    var article = document.createElement("article");
    var h2 = document.createElement("h2");
    var table = document.createElement("table");
    var tr = document.createElement("tr");
    var th = document.createElement("th");
    var td = document.createElement("td");

    var counter = 1; // TODO: darüber wirklich die Anazhl der Sätze tracken?

    //Innerhalb von Exercises einen neuen <article> anlegen
    exercises.appendChild(article);
    article.setAttribute("class", "exercise elements");

    //Innerhalb von dem neuen <article> eine neue <h2> anlegen 
    article.appendChild(h2);
    h2.innerText = "Test" // TODO: Dynamisch richtigen Namen einfügen
    article.setAttribute("name", h2.innerHTML)
    h2.setAttribute("class", "ExerciseHeadline");

    //Innerhalb der neuen <h2> einen <table> anlegen
    h2.appendChild(table);
    table.setAttribute("class", "exerciseTable");

    //Statisch die Headzeile einfügen
    table.innerHTML = ("<tr class =\"headLineTable\"><th>Satz</th><th>KG</th><th>Wiederholungen</th></tr>");

    //Leere Zeile für  den ersten Set einfügen
    table.appendChild(tr);
    var satztd = document.createElement("td");
    var kgtd = document.createElement("td");
    var whdtd = document.createElement("td");
    var checktd = document.createElement("td");
    satztd.innerHTML = counter;
    counter++;

    //Feld um Gewicht einzutragen
    var kg = document.createElement("input");
    kg.setAttribute("placeholder", "Gewicht");
    kgtd.appendChild(kg);

    //Feld um whd einzutragen
    var whd = document.createElement("input");
    whd.setAttribute("placeholder", "Wiederholungen");
    whdtd.appendChild(whd);

    //Button um Set abzuschließen
    var check = document.createElement("input");
    check.setAttribute("type", "checkbox");
    //check.setAttribute("checked", "off");
    checktd.appendChild(check);

    tr.appendChild(satztd);
    tr.appendChild(kgtd);
    tr.appendChild(whdtd);
    tr.appendChild(checktd);

    var setButton = document.createElement("button");
    setButton.setAttribute("class", "button addSet");
    setButton.innerHTML = "Satz hinzufügen";


    article.appendChild(setButton); //
    //setButton.setAttribute("name", setButton.parentNode) 
    init(); // Damit auch ein EventListener auf dem neuen Button gesetzt wird
}

// Funktion fügt jeweil bei dem Button von dem es aufgerufen wird einen Satz hinzu
function addSet() {

    console.log("CallingButton: " + this.parentNode.getAttribute("name")); // Kann man so feststellen, von welchem Button die Methode aufgerufden wurde?
    let NameOfcallingExercise = this.parentNode.getAttribute("name");      // Name der Übung

    /*
    Das Nachfolgende muss irgendwie auch elleganter gehen aber für den Moment funktioniert es
    ---
    */

    var callingExercise = document.getElementsByName(NameOfcallingExercise); //Liste der Übungen mit dem Namen
    var callingExercise = callingExercise[0];// Verweis auf Article          //Da aber jede Übung pro Training nur einmal auftauchen darf, kann man einfach immer die erste nehmen

    var callingExercise = callingExercise.children[0]; // -> h2!
    var callingExercise = callingExercise.children[0]; // -> table!

    /*
    ---
    */

    var tr = document.createElement("tr");

    callingExercise.appendChild(tr);

    //Erstellung der Table Data Elemente
    var satztd = document.createElement("td");
    var kgtd = document.createElement("td");
    var whdtd = document.createElement("td");
    var checktd = document.createElement("td");


    satztd.innerHTML = "#"; //TODO: Einfügen

    //Feld um Gewicht einzutragen
    var kg = document.createElement("input");
    kg.setAttribute("placeholder", "Gewicht");
    kgtd.appendChild(kg);

    //Feld um whd einzutragen
    var whd = document.createElement("input");
    whd.setAttribute("placeholder", "Wiederholungen");
    whdtd.appendChild(whd);

    //Button um Set abzuschließen
    var check = document.createElement("input");
    check.setAttribute("type", "checkbox");
    checktd.appendChild(check);

    tr.appendChild(satztd);
    tr.appendChild(kgtd);
    tr.appendChild(whdtd);
    tr.appendChild(checktd);
}