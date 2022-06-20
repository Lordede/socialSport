"use strict";
// author: Hubertus

/*
addExercise fügt eine neue Übung ein, bei der man dann x belibige Setze trainieren kann
*/
function addExercise(exercise, isLoadExercise) {

    var article = document.createElement("article");
    article.setAttribute("id", exercise.id);
    var h2 = document.createElement("h2");
    var bild = document.createElement("img")
    var table = document.createElement("table");
    var tr = document.createElement("tr");
    var th = document.createElement("th");
    var td = document.createElement("td");
    var container = document.createElement("div");
    var removeExerciseButton = document.createElement("button");
    
    
    // Checkt Erstellung Exercise in Bearbeitungs- oder Trainingssicht -> Regelt Sichtbarkeit von Elementen 
    container.setAttribute("class", "setContainer");
    if(isLoadExercise){
        container.style.visibility = "visible";
        removeExerciseButton.style.visibility = "hidden";
    }
    else
    {
        container.style.visibility = "hidden"; 
        removeExerciseButton.style.visibility = "visible";
        article.style.height = "150px";
        
    }
  

    //Innerhalb von Exercises einen neuen <article> anlegen
    exercises.appendChild(article);
    article.setAttribute("class", "exercise elements");

    //Remove exercise button
    removeExerciseButton.innerHTML = "Übung entfernen";
    removeExerciseButton.setAttribute("class", "removeExerciseButton");
    article.appendChild(removeExerciseButton);

    //Innerhalb von dem neuen <article> eine neue <h2> anlegen 
    article.appendChild(h2);
    h2.innerText = exercise.name;// TODO: Dynamisch richtigen Namen einfügen
    article.setAttribute("name", h2.innerHTML)
    h2.setAttribute("class", "ExerciseHeadline");

    //Bild
    article.appendChild(bild);

    //SetDiv
    article.appendChild(container);


    bild.setAttribute("src", "../BildServlet?id="+exercise.id);
    bild.setAttribute("width", "50");
    bild.setAttribute("height", "50");

    //Innerhalb der neuen <h2> einen <table> anlegen
    container.appendChild(table);
    table.setAttribute("class", "exerciseTable");

    //Statisch die Headzeile einfügen
    table.innerHTML = ("<tr class =\"headLineTable\"><th>Satz</th><th>KG</th><th>Wiederholungen</th></tr>");

    var setButton = document.createElement("button");
    setButton.setAttribute("class", "button addSet");
    setButton.innerHTML = "Satz hinzufügen";
    container.appendChild(setButton);

    addSet(table)

    init(); // Damit auch ein EventListener auf dem neuen Button gesetzt wird
}


function getExerciseReference() {

    let NameOfcallingExercise = this.parentNode.parentNode.getAttribute("name");      // Name der Übung

    /*
    Das Nachfolgende muss irgendwie auch eleganter gehen aber für den Moment funktioniert es

    INFO: Über das Attribut "name" des jeweiligen Article weiß der Button wo er das Set hinzufügen soll! 

    ---
    */

    var callingTable = document.getElementsByName(NameOfcallingExercise);   //Liste der Übungen mit dem Namen
    var callingTable = callingTable[0];// Verweis auf Article                //Da aber jede Übung pro Training nur einmal auftauchen darf, kann man einfach immer die erste nehmen
    var callingTable = callingTable.children[3].children[0]; // -> table!

    addSet(callingTable)

    /*
    ---
    */
    // Funktion fügt jeweil bei dem Button von dem es aufgerufen wird einen Satz hinzu
}
function addSet(callingTable) {
    var tr = document.createElement("tr");
    var numberOfChildren = callingTable.children.length;

    callingTable.appendChild(tr);

    //Erstellung der Table Data Elemente
    var satztd = document.createElement("td");
    var kgtd = document.createElement("td");
    var whdtd = document.createElement("td");
    var checktd = document.createElement("td");


    satztd.innerHTML = numberOfChildren;

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
    check.setAttribute("class", "checkbox")
    check.setAttribute("type", "checkbox");
    checktd.appendChild(check);

    tr.appendChild(satztd);
    tr.appendChild(kgtd);
    tr.appendChild(whdtd);
    tr.appendChild(checktd);
    init(); //damit der Satz auch vom Eventlistener überwacht wird
}



function disableSet() {

    let exerciseid = this.parentNode.parentNode.parentNode.parentNode.parentNode.getAttribute("id");   // Smell
    let idOfTraining = document.getElementsByTagName("header");
    idOfTraining = idOfTraining[0].getAttribute("id");              // id des Trainings

    /*
        notwendig um vom aufrufgenden Element zu den jeweiligen Elementen zu navigieren
    */
    var tds = this.parentNode.parentNode.children;
    var kgZelle = tds[1];
    var whdZelle = tds[2];

    var kginput = kgZelle.children[0];
    var whdinput = whdZelle.children[0];

    if (whdinput.value != "") {   // kg darf null / "" sein, da man ja ohne weiteres z.B Klimmzüge ohne Gewicht machen kann.
        if (sendSet(kginput.value, whdinput.value, exerciseid)) {    // nur sperren, wenn auch gesendet wurde

            //sperren der Eingabefelder
            this.checked = true;                         //nur optisch schöner // Warum geht das nicht mit this.setAttribute?
            this.setAttribute("disabled", "true");
            kginput.setAttribute("disabled", "true");
            whdinput.setAttribute("disabled", "true");

        }
    }


}

function sendSet(kginput, whdinput, exerciseid) {

    var xmlhttp = new XMLHttpRequest;
    var url = "../SetServlet";
    
    xmlhttp.addEventListener("load", function () {

        var response = xmlhttp.response;
        console.log("Der Ajax Aufruf gibt zurück"+response);

        /* 
        TODO:
        Überprüfen, ob die Antwort vom Server auf Erfolg hinweist oder nicht
        Erfolg -> return true
        Errror -> return false

        Das ist wichtig, da das UI darauf reagiert
        
        */
   
    });
    
    xmlhttp.open("POST", url, true)
    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlhttp.send("rep="+whdinput+"&kg="+kginput+"&exerciseid="+exerciseid);
    return true; // TODO:
}



