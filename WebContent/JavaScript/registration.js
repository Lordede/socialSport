/**
 * 
 * @author Hubertus Seitz
 * 
 * Es sollten alle Addons in Chrome deaktiviert werden damit keine Fehler geschmissen werden!
 * 
 */


function emailAvailable() {

    var searchURL = "../CheckAvailabilityServlet"; //TODO: Hinzufügen
    var email = document.getElementById("emailInput").value;

    if (email != null && email.length > 0) {
        searchURL += "?email=" + encodeURIComponent(email); //Erstellung des Querry Strings
    }
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.responseType = "json";


    xmlhttp.addEventListener("load", function () {    //Ausführen einer anonymen Funktion, sobald eine Antwort vom Server gekommen ist
        var availability = xmlhttp.response;            // JSON to JS Object!
        var check = document.getElementById("emailcheck");

        if (!email.includes("@")) { //Falls noch kein @ eingegeben wurde soll auch noch kein "❌" angezeigt werden
            check.innerHTML = "";
        }
        else if (availability.email === "true") {

            console.log("E-Mail: Verfügabr!")
            check.innerHTML = "✅";

        }
        else {
            console.log("E-Mail: NICHT Verfügbar!");
            check.innerHTML = "❌";
        }


    });

    xmlhttp.open("GET", searchURL, true)
    xmlhttp.send(); //setRequestHeader() nur bei Post benötigt









}

function usernameAvailable() {

    var searchURL = "../CheckAvailabilityServlet"; //TODO: Hinzufügen
    var username = document.getElementById("usernameInput").value;
    var check = document.getElementById("usernamecheck");

    if (username != null && username.length > 0) {
        searchURL += "?username=" + encodeURIComponent(username); //Erstellung des Querry Strings
    }
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.responseType = "json";


    xmlhttp.addEventListener("load", function () {    //Ausführen einer anonymen Funktion, sobald eine Antwort vom Server gekommen ist
        var availability = xmlhttp.response;            // JSON to JS Object!

        if (username.length < 2) {
            check.innerHTML = "";
        }

        else if (availability.username === "true") {

            console.log("username: Verfügabr!")
            check.innerHTML = "✅";
        }
        else {
            console.log("username: NICHT Verfügbar!");
            check.innerHTML = "❌";

        }

    });

    xmlhttp.open("GET", searchURL, true)
    xmlhttp.send(); //setRequestHeader() nur bei Post benötigt

}