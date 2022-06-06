<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="../css/uebungen.css"> <!-- href nach konvertierung zu jsp anpassen -->
	<base href="${pageContext.request.requestURI}" /> <!-- Basis f�r relative Verweise -> Basis = Speicherort -->
    <meta charset="utf-8">
    <title>&Uumlbungen</title>
</head>

<body>
	<%@ include file="fragments/authenticationCheck.jspf" %> <%-- �berpr�fung, ob User eingeloggt ist --%>
    <header> Social Sport</header>

    <main>
        <!--TODO: Logik hinzufuegen-->

        <article>
            <h2>Bench Press</h2>
            <!-- Uebung 1 (Spaeter sollen dynamisch immer weitere Uebungen 
                 hinzugefuegt werden koennen. Jede Uebung neue table oder neuer article?)-->
            <table>
                <!-- Soll der Button der den Satz als fertig kennzeichnet teil der Tabelle sein
                     oder nicht? -->
                <tr>
                    <th>Satz</th>
                    <th>Beim letzten Mal</th>
                    <th>KG</th>
                    <th>Wiederholungen</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>120 x 5</td>
                    <td>BLANK</td>
                    <td>BLANK</td>
                    <td>&check;</td>
                </tr>
            </table>
        </article>
        <p><button> Satz hinzuf&uuml;gen</button></p>
        <!-- Soll eine Zeile zur Uebung hinzufügen
               ->Es soll unter jeder Uebung solch ein Button existieren -->
        <p><button>&Uuml;bung hinzuf&uuml;gen</button></p>
        <p><button>Workout abbrechen</button></p>

    </main>

    <nav>
        <!--TODO: Links hinzufuegen-->
        <ul>
            <li>Home</li>
            <li>Training</li>
            <li>Leaderboard</li>
        </ul>
    </nav>



</body>

</html>