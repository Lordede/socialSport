"use strict";
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

    var setButton = document.createElement("button");
    setButton.setAttribute("class", "button addSet");
    setButton.innerHTML = "Satz hinzufügen";
    article.appendChild(setButton);

    addSet(table)

    init(); // Damit auch ein EventListener auf dem neuen Button gesetzt wird
}

// Funktion fügt jeweil bei dem Button von dem es aufgerufen wird einen Satz hinzu
function getExerciseReference() {

    let NameOfcallingExercise = this.parentNode.getAttribute("name");      // Name der Übung

    /*
    Das Nachfolgende muss irgendwie auch eleganter gehen aber für den Moment funktioniert es

    INFO: Über das Attribut "name" des jeweiligen Article weiß der Button wo er das Set hinzufügen soll! 
    ---
    */

    var callingTable = document.getElementsByName(NameOfcallingExercise); //Liste der Übungen mit dem Namen
    var callingTable = callingTable[0];// Verweis auf Article          //Da aber jede Übung pro Training nur einmal auftauchen darf, kann man einfach immer die erste nehmen

    var callingTable = callingTable.children[0]; // -> h2!
    var callingTable = callingTable.children[0]; // -> table!

    addSet(callingTable)

  

    article.appendChild(setButton); //

    /*
    ---
    */

}
function addSet(callingExercise) {
    var tr = document.createElement("tr");

    callingExercise.appendChild(tr);

    //Erstellung der Table Data Elemente
    var satztd = document.createElement("td");
    var kgtd = document.createElement("td");
    var whdtd = document.createElement("td");
    var checktd = document.createElement("td");


    satztd.innerHTML = "#"; //TODO: Satznummer hinzufügen!

    //Feld um Gewicht einzutragen
    var kg = document.createElement("input");
    kg.setAttribute("type", "number")
    kg.setAttribute("placeholder", "Gewicht");
    kgtd.appendChild(kg);

    //Feld um whd einzutragen
    var whd = document.createElement("input");
    whd.setAttribute("placeholder", "Wiederholungen");
    whd.setAttribute("type", "number")
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

