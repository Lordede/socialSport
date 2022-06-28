/**
 * @author Hubertus Seitz
 **/

  function loadLeaderboard(){ //laden des Leaderboards über einen  AJAX-Aufruf

    var url = "../JoinServlet?getLeaderboard";
    
    var xmlhttp = new XMLHttpRequest();

    xmlhttp.addEventListener("load" , function(){

        var leaderboard = JSON.parse(xmlhttp.response);       // js Objekt aus jsonfile
        var headrow = document.getElementById("Headrow").parentElement; //Damit auf das tr verwiesen wird

        if (leaderboard === null || leaderboard.length === 0) {
            document.getElementById("leaderboard").innerHTML("Fehler beim laden des Leaderboards!");
            return;
        }
        for (var i=0; i < leaderboard.length; i++){
            var tr = document.createElement("tr");
            var td1 = document.createElement("td");
            var td2 = document.createElement("td");
            var td3 = document.createElement("td");

            headrow.append(tr);

            tr.appendChild(td1);
            td1.innerHTML =leaderboard[i].points;

            tr.appendChild(td2);
            td2.innerHTML= leaderboard[i].username;

            tr.appendChild(td3);
            td3.innerHTML= leaderboard[i].id;
        }



    })
    xmlhttp.open("GET", url, true);
    xmlhttp.send();

  }

  