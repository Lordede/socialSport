<!DOCTYPE html>
<html>

<head>
    <base href="${pageContext.request.requestURI}" />
    <meta charset="UTF-8">
    <meta name="description" content="Speichern Sie Ihre Traings hier">
    <meta name="keywords" content="Sport, Training, Sommerfigur, Trainingserfolg">
    <meta name="author" content="Cem Durmus">

    <script src="../JavaScript/TriggerExerciseAdditon.js" type="text/javascript"></script>
</head>

<body>
    <header>
        <div id="toggleExerciseAddition">
            <input type="hidden" id="searchBar">
            <button id="addButton" value="addButton">�bung hinzuf�gen</button>
            <template id="searchResults">
                <div>
                    <div class="searchExerciseName" exercise-name></div>
                    <div class="searchMuscleGroup" exercise-group></div>
                </div>
            </template>
           <div id="searchResultContainer"></div>
            <script>
                var button = document.getElementById("addButton");
                document.addEventListener("DOMContentLoaded", init);

                function init() {
                    //document.getElementById("addButton").addEventListener('click', toggleExerciseSearchbar);
                    button.addEventListener('click', toggleExerciseSearchbar);
                    // document.ElementByName('addButton').submit();
                    console.log("html spricht an");
                }

            </script>
        </div>
    </header>
    <main>
        <div id="exerciseContainer">

        </div>
    </main>
    <footer>
    </footer>
    <!--         <script src="../JavaScript/TriggerExerciseAdditon.js">var button = document.querySelector(".addButton");button.addEventListener('click', toggleExerciseSearchbar);</script> -->
</body>

</html>