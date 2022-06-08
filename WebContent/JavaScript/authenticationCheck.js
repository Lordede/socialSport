//Hubertus Seitz

"use strict"

function authenticationCheck(userData){
	
	
	
	if (userData === "") { // Kann man das so auswerten?
		
		alert("Bitte einloggen!")
		window.location.replace("login.html")   
	}
	else{
		console.log("UserData wurde gefunden!")
		console.log(userData)
	}
}