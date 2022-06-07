/**
 * 
 * @author Hubertus Seitz
 * 
 * Es sollten alle Addons in Chrome deaktiviert werden damit keine Fehler geschmissen werden!
 * 
 */


function emailAvailable(){

    console.log("EMAIL: Change!");
    
    var searchURL = "../CheckAvailabilityServlet"; //TODO: Hinzufügen
    var email = document.getElementById("emailInput").value;
    
    if(email != null && email.length>0){
        searchURL += "?email=" + encodeURIComponent(email); //Erstellung des Querry Strings
    }
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.responseType = "json";


        xmlhttp.addEventListener("load", function(){    //Ausführen einer anonymen Funktion, sobald eine Antwort vom Server gekommen ist
        var availability = xmlhttp.response;        // JSON to JS Object!
            if(availability == null || availability.length == 0){
                console.log("NICHT Verfügbar!");
                console.log(availability);
            }
            else{
                console.log("Verfügabr!")
                console.log(availability);
            }

        });

        xmlhttp.open("GET", searchURL, true)
        xmlhttp.send(); //setRequestHeader() nur bei Post benötigt
            
     





    

}

function usernameAvailable(){

    console.log("USERNAME: Change!");

}