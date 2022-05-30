function insertExercise()
{
    var insertableBlock;
    var setData = "${set}";
    var exerciseName = "${exercise.name}";
    var muscleGroup = "${exercise.muscleGroup"
    
    insertableBlock = document.createElement('div');
    insertableBlock.innerHTML = '<h2>' + exerciseName + '</h2>' +
                                '<h3>'+'('+ muscleGroup +')'+'</h3>' +
                                SetTable.loadTableData(setData);
                                
}


