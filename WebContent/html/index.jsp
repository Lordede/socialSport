<%@ page contentType="text/html; charset=UTF-8" %>
    <!DOCTYPE html>
    <!-- Erstellt von Cem Durmus -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index_dark.css">

    <head>
        <title>
            Social Sport
        </title>
        <meta charset="UTF-8">
        <meta name="description" content="Ihre one stop Sport Plattform">
        <meta name="keywords" content="Sport, Gym, social media, sports">
        <meta name="author" content="Cem Durmus">
        <base href="${pageContext.request.requestURI}" />
    </head>

    <body class="font_t">
<%@ include file="fragments/noScript.jspf" %>
        <header>
            <h1>Social Sport</h1>
            <nav>
                <a href="login.html">Login</a>
            </nav>
        </header>
        <article>
            <h1>Willkommen auf ihrer one stop Sport Plattform</h1>
            <div class="photoBox">
                <div class="zwei_box" id="col">
                    <p>
                        <img src="../img/victor-freitas-WvDYdXDzkhs-unsplash.jpg" alt="Kreuzheben.jpg" width="720"
                            height="480">
                    </p>
                </div>
                <div id="col"></div>
                <div id="col"></div>
                <div class="zwei_box" id="col">
                    <p>
                        <img src="../img/samuel-girven-VJ2s0c20qCo-unsplash.jpg" alt="Hanteln" width="320" height="480">
                    </p>
                </div>
            </div>
            <div>
                <p class="text_block">
                <h2>Genug vor der Couch gesessen, jetzt kannst du Sport machen und deine Freunde teilhaben lassen</h2>
                </p>
            </div>
            <div class="lowerBox">

                <div class="drei_box">
                    <h3>Warum Social Sport?</h3>
                    <img src="../img/edgar-chaparro-sHfo3WOgGTU-unsplash.jpg" alt="Klimmzüge.jpg" width="324"
                        height="480">
                    <p class="text_block">
                        <br> Verwende Social Sport um deinen Trainingserfolg zu verfolgen und immer zu wissen wie viel
                        Gewicht du hebst.
                        Damit kannst du deinen Erfolg genau kontrollieren und ebenfalls motiviert an dir weiter
                        arbeiten.
                    </p>
                </div>
                <div class="drei_box">
                    <h3>Was ist daran die soziale Komponente?</h3>
                    <img src="../img/logan-weaver-lgnwvr-9D_rUDe7xvA-unsplash.jpg" alt="Freunde.jpg" width="324"
                        height="480">
                    <p class="text_block">
                        <br> Da du je absolvierter Trainingseinheit Punkte erhälst, kannst du diese mit deinen Freunden
                        teilen oder auch bei besonders großem Erfolg auch
                        auf dem Globalen Leaderboard von Social Sport erscheinen und dich somit mit anderen Mitgliedern
                        messen.
                    </p>
                </div>
                <div class="drei_box">
                    <h3>An wen richtet sich diese Website?</h3>
                    <img src="../img/max-libertine-Pi4MOqVb85c-unsplash.jpg" alt="Kämpferin.jpg" width="324"
                        height="480">
                    <p class="text_block">
                        <br> Ganze unabhängig von deinem Level, - ob bluter Anfänger, oder eiserner Bodybuilder - wirst
                        du dich hier wohl fühlen.
                        Also probiers und lass deine Freunde an deinem Trainingserfolg teilhaben.
                    </p>
                </div>
            </div>
        </article>
        <footer>
            <div>
                <br>
                <h2>
                    Wer sind wir?
                </h2>
                <p class="text_block">
                    Als Fitness begeisterte Studenten wollten wir eine Plattform erschaffen, die es uns ermöglicht
                    während stressiger Phasen im Studium gegenseitige Motivation zu erhalten.
                    Also schufen wir diese Plattform, welche wir auch selber Nutzen und davon Profitieren.
                </p>
            </div>
        </footer>
    </body>

    </html>