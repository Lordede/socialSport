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

function senseEvent(event)
{
    let eventTrigger = event.target.className;
    let button = document.getElementsByClassName(eventTrigger);
    let parentElem = button.parentElement;
    let tagName = parentElem.getElementsByTagName("h2");
    dbCall(tagName.innerHtml);
}

function dbCall(exerciseName)
{
    let request = new XMLHttpRequest();
    request.open("Post", "../FavoriteExerciseServlet");
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("name="+exerciseName);
}