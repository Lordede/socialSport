/**
 * @author Hubertus Seitz
 **/
"use strict"



//Vermutlich so in Produktivumgebungen nicht machbar, da Clientseitige Auswertung
//Anderesrseits kann eigentlich nicht viel gemacht werden ohne das der Server die Userdaten in den SessionScope geschrieben hat-
function authenticationCheck(userData) { 

	if (userData === "") {

		alert("Bitte einloggen!");
		window.location.replace("login.html");
	}
	else {
		console.log("UserData wurde gefunden!");
		console.log(userData);
	}
}


// Diese Funktion sendet einen AJAX-Aufruf an das LoginServlet mit der HTTP Delete methode. Dadruch wird der Nutzer ausgeloggt.
function logout() {

	var xmlhttp = new XMLHttpRequest;
	var url = "../LoginServlet?logout";

	xmlhttp.addEventListener("load", function() {

		var response = xmlhttp.response;

		window.location.href = "login.html";
	});

	xmlhttp.open("DELETE", url, true)
	xmlhttp.send();
}




