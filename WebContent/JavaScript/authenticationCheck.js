//Hubertus Seitz

"use strict"

//Vermutlich so in Produktivumgebungen nicht machbar da Clientseitige Auswertung -> Unsicher?


function authenticationCheck(userData) { 	


	if (userData === "") { // Kann man das so auswerten?

		alert("Bitte einloggen!");
		window.location.replace("login.html");
	}
	else {
		console.log("UserData wurde gefunden!");
		console.log(userData);
	}
}

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




