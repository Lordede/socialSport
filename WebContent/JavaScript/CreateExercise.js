/*Creates the exercise with table dynamically */
function insertExercise()
{
    var counter = 1;
    var insertableBlock;
    var setData = "${set}";
    var exerciseName = "${exercise.name}";
    var muscleGroup = "${exercise.muscleGroup"
    var img = "${exercise.img}"
    var parent = document.getElementById("exerciseContainer");

    insertableBlock = document.createElement('div');
    insertableBlock.setAttribute("id", "exercise" + counter(counter))
    insertableBlock.innerHTML = '<h2>' + exerciseName + '</h2>' +
                                '<h3>'+'('+ muscleGroup +')'+'</h3>' +
                               loadTable;
    parent.appendChild(insertableBlock);
                                
                                
}
var setData = []; //Daten vom Set

function loadTable() //Content injetion
{
    var counter = 3;
    var exerciseTable = document.createElement('table');
    var parent = document.getElementById('exercise');

    exerciseTable.setAttribute("id","exerciseTable" + counter(counter)); //get specific Table
    let dataHtml = ''; //append html data as string

    window.onload = () =>  //initialisierung des windowobjects wobei die tableData (aus html) geladen wird
    
    {
        loadTableData(setData);
    }
    
    for (let set of setData)
    {
        dataHtml += '<tr><td>${set.no}</td><td>${set.rep}</td><td>${set.kg}</tr>';
    }
    tableBody.innerHTML = dataHtml; //initialisierung default Belegung DOM
    parent.appendChild('exercise');
} 

function fillSet(setData)
{

}
function counter(counter)
{
    counter++;
}

function addExercise(){

    var article = document.createElement("article")
    var h2 = document.createElement("h2")
    var table = document.createElement("table")
    var tr = document.createElement("tr")
    var th = document.createElement("th")
    var td = document.createElement("td")
    
    //Innerhalb von Exercises einen neuen <article> anlegen
    exercises.appendChild(article)
    article.setAttribute("class", "exercise elements")
    
    //Innerhalb von dem neuen <article> eine neue <h2> anlegen 
    article.appendChild(h2)
    h2.innerText = "Test" // Muss man noch schaun, wie man da drauf zugreifen kann
    
    //Innerhalb der neuen <h2> einen <table> anlegen
    h2.appendChild(table)
    table.setAttribute("class", "exerciseTable")
    
    //geht das so einfach? -> Wäre schön da das ja eh nur statisch ist
    table.innerHTML=("<tr><th>Satz</th><th>KG</th><th>Wiederholungen</th></tr>")
  
    }
