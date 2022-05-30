"use strict"

function authenticationCheck(userData){
	
	
	
	if (userData === "") { //wird noch nicht richtig ausgewertet
		
		alert("Bitte einloggen!")
		window.location.replace("login.html");   
	}
	else{
		console.log("UserData wurde gefunden!")
		console.log(userData)
	}
}