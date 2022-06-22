//@author Hubertus Seitz
// https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_sort_table_number
//----------------------------------------
// function sortTable() {
//     var table, rows, switching, i, x, y, shouldSwitch;
//     table = document.getElementById("leaderboardTable");
//     switching = true;
//     /*Make a loop that will continue until
//     no switching has been done:*/
//     while (switching) {
//       //start by saying: no switching is done:
//       switching = false;
//       rows = table.rows;
//       /*Loop through all table rows (except the
//       first, which contains table headers):*/
//       for (i = 1; i < (rows.length - 1); i++) {
//         //start by saying there should be no switching:
//         shouldSwitch = false;
//         /*Get the two elements you want to compare,
//         one from current row and one from the next:*/
//         x = rows[i].getElementsByTagName("TD")[0];
//         y = rows[i + 1].getElementsByTagName("TD")[0];
//         //check if the two rows should switch place:
//         if (Number(x.innerHTML) < Number(y.innerHTML)) {
//           //if so, mark as a switch and break the loop:
//           shouldSwitch = true;
//           break;
//         }
//       }
//       if (shouldSwitch) {
//         /*If a switch has been marked, make the switch
//         and mark that a switch has been done:*/
//         rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
//         switching = true;
//       }
//     }
//   }
  //----------------------------------------

  function loadLeaderboard(){ //laden des Leaderboards

    var url = "../JoinServlet?getLeaderboard";
    
    var xmlhttp = new XMLHttpRequest();

    xmlhttp.addEventListener("load" , function(){

        var leaderboard = JSON.parse(xmlhttp.response);       // js Objekt aus jsonfile
        //var table = document.getElementById("leaderboardTable").children[0].children[0]; //Damit auf das tr verwiesen wird
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

  