<!DOCTYPE html>
<html lang="de">
    <head>
    <base href="${pageContext.request.requestURI}"  /> <!-- Ganz wichtig damit die jsp auch logisch da liegt wo sie isch im Dateisystem befindet -->
           <title>
            Üœbung
        </title>
        <meta charset="UTF-8">
        <meta name="description" content="Ihre one stop Sport Plattform">
        <meta name="keywords" content="Sport, Gym, social media, sports">
        <meta name="author" content="Cem Durmus">
    </head>
    <body>
<%--    <%@ include file="fragments/authenticationCheck.jspf"%>  //Uncomment after merging LoginWall2.0 --%> 
        <header>
            <h1>Profil Bearbeiten</h1>
        </header>
        <article>
            <h2>Übung erstellen: </h2>
            <form class="ChangeUsername" action="../ExerciseServlet" method="post" enctype="multipart/form-data" accept-charset="utf-8">
            <div class="name">														
                <p>Name der Üœbung:</p>
        		<input type="text" name="exerciseName" size="30" maxlength="35"><br>
             </div> 
            <div>
            <fieldset>
            <legend>Muskelgruppe wählen: </legend>
            <input type="checkbox" name="muscleGroup" value="Beinmuskulatur" id="c1">
            <label for="c1">Beinmuskulatur</label>
            <input type="checkbox" name="muscleGroup" value="RÃ¼ckenmuskulatur" id="c2">
            <label for="c2">Rückenmuskulatur</label>
            <input type="checkbox" name="muscleGroup" value="Armmuskulatur" id="c3">
            <label for="c3">Armmuskulatur</label>
            <input type="checkbox" name="muscleGroup" value="Brustmuskulatur" id="c4">
            <label for="c4">Brustmuskulatur</label>
            <input type="checkbox" name="muscleGroup" value="Bauchmuskulatur" id="c5">
            <label for="c5">Bauchmuskulatur</label>
            </fieldset>
            <input type="file"
                         name="exerciseImage"
                         accept="image/*"
                         formaction="../ExerciseServlet"
                         formmethod="post">
            </div>
            <Button type="submit">Übung Erstellen</Button>
            <p> Profilbild ändern: </p>
                        
<!--                     	<button type="submit" -->
<!--                      	 name="changeImage" -->
<!--                      	 value="changeImage" -->
<!--                        	 formaction="../ExerciseServlet"  -->
<!--                     	 formmethod="post"> -->
<!--                     	 Bild ändern -->
<!--                      </button> -->
    	</Form>
    	<div>
    	<table>
    	<tr>
    	<th>Name der Übung</th>
    	<th>Muskelgruppe</th>
    	</tr>
    	<tr>
    	<td>${exercise.name}</td>
    	<td>${exercise.muscleGroup}</td>
    	</tr>
    	</table>
    	</div>
    		<div>
    		<h2>Satzauswahl: </h2>
    		<form action="../SetServlet" method="post" accept-charset="utf-8">
    		<label for="exerciseName">In Welchem Satz befindest du dich?</label>
    		<input type="text" name="exerciseName" size="30" maxlength="3"><br>
    		<label for="exerciseWeight">Wie viel Gewicht?</label>
    		<input type="text" name="exerciseWeight" size="30" maxlength="3"><br>
    		<label for="exerciseName">Wie viele Wiederholungen?</label>
    		<input type="text" name="exerciseName" size="3" maxlength="3"><br>
    		<Button type="submit" value="createSet" >Satz erstellen</Button>
    		</form>
    		</div>
    		<div>
    		<form action="../ExerciseServlet" method="post" enctype="multipart/form-data" accept-charset="utf-8">
                    
                </form>
    		</div>
        </article>
        <footer>
            <!--Generische Navigationsleiste-->
        </footer>
    </body>
</html>