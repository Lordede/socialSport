// function senseClick()
// {
//     let exerciseNames = [
//         "Bizepscurls",
//         "Hammercurls",
//         "Trizepsdruecken_SZ",
//         "Trizepsdruecken_kabel",
//         "Trizepsdruecken_kabel",
//         "Seitenheben",
//         "Schulterdruecken_Kh",
//         "Bankdruecken",
//         "Schraegbankdruecken",
//         "Brust_Kabel_ueberzug",
//         "Rueckenmuskulatur",
//         "rudern_Langhantel",
//         "rudern_kabelturm",
//         "Beinpresse",
//         "Ausfallschritte",
//         "Kniebeugen",
//         "Situps_negativbank",
//         "Crunshes_maschine",
//         "seitliches_oberkoerperbeugen_kh",
//     ]
//     exerciseNames.forEach(exercise =>
//         {
//             let parentElem = document.querySelector("."+exercise);
//             let buttonFav = parentElem.querySelector(".fav-button");
//             let tagName = parentElem.getElementsByTagName("h2");
//             buttonFav.addEventListener("click", dbCall(tagName.innerHtml));
//         })
// }

function senseEvent(event) {
  let eventTrigger = event.target.className;
  let button = document.getElementsByClassName(eventTrigger);
  let parentElem = button.parentElement;
  let tagName = parentElem.getElementsByTagName("h2");
  dbCall(tagName.innerHtml);
}

//Anlage Exercise als Favorit
//GET Aufruf von Lukas Edmüller
function dbCall(exerciseName) {
  /*
    Ruft FavoriteExercise ab
    Wenn nicht vorhanden -> Response = false -> Post
    Wenn  vorhanden -> Response = true -> Alert 
  */
  let http = new XMLHttpRequest();
  http.open(
    "GET",
    "../FavoriteExerciseServlet?name=" +
      exerciseName +
      "&checkExisting=" +
      true,
    true
  );
  http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  http.onload = function () {
    let response = http.responseText;
    if (response === "false") {
      let request = new XMLHttpRequest();
      request.open("Post", "../FavoriteExerciseServlet");
      request.setRequestHeader(
        "Content-type",
        "application/x-www-form-urlencoded"
      );
      request.send("name=" + exerciseName);
    } else {
      alert("Übung ist bereits favorisiert!");
    }
  };
  http.send();
}
