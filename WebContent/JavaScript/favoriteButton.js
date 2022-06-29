// Autor: Lukas Edmüller

/**
 * 
 * @param {event} event: getriggertes Event um die Übung auszuwählen zur favorisierung
 *                       welche anhand ihrer Überschrift herausgefiltert wird.
 */
function senseEvent(event) {
  let eventTrigger = event.target.className;
  let button = document.getElementsByClassName(eventTrigger);
  let parentElem = button.parentElement;
  let tagName = parentElem.getElementsByTagName("h2");
  dbCall(tagName.innerHtml);
}

//Anlage Exercise als Favorit
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
