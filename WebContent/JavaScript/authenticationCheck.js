"use strict"

function authenticationCheck(userData){
	
	
	
	if (userData === "") { //wird noch nicht richtig ausgewertet
		
		
		document.getElementsByTagName('BODY')[0].innerHTML = ''; // https://social.msdn.microsoft.com/Forums/en-US/29f4e7ce-ab58-43e3-a0fa-0b34f9967117/clear-body-with-javascript?forum=aspwebformsdata
		console.log("Keine UserData gefunden!")
		alert("Bitte einloggen!")
	}
	else{
		console.log("UserData wurde gefunden!")
		console.log(userData)
	}
}