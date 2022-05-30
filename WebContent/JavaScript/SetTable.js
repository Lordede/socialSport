var setData = []; //Daten vom Set

function loadTableData(setData) //Content injetion
{
    const tableBody = document.getElementById('name_der_betreffenden_tabelle_eintragen'); //get specific Table
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
} 