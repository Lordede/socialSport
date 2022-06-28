<%@ page contentType="text/html; charset=UTF-8" %>
    <!DOCTYPE html>
    <html>
    <!-- Erstellt von Cem Durmus -->

    <head>
        <base href="${pageContext.request.requestURI}" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index_dark.css">
        <%@ include file="html/fragments/footerHEAD.jspf" %>

            <title>
                Social Sport
            </title>
            <meta charset="UTF-8">
            <meta name="description" content="Ihre one stop Sport Plattform">
            <meta name="keywords" content="Sport, Gym, social media, sports">
            <meta name="author" content="Cem Durmus">

    </head>

    <body class="font_t">
        <header class="centered">
            <h1>Social Sport</h1>
            <div class="linksContainer centered">
                <div class="links">
                    <p>Bereits Mitglied?</p>
                    <form>
                        <button formaction="html/login.html">Login</button>
                    </form>
                </div>
                <div class="links">
                    <p>Noch kein Mitglied?</p>
                    <form>
                        <button formaction="html/registration.html">Registrieren</button>
                    </form>
                </div>

            </div>

        </header>
        <article>
            <h1 class="centered">Willkommen auf Ihrer One-Stop-Sportplattform</h1>
            <div class="photoBox">
                <div class="zwei_box" id="col1">
                    <p>
                        <img src="img/victor-freitas-WvDYdXDzkhs-unsplash.jpg" alt="Kreuzheben.jpg" width="720"
                            height="480">
                    </p>
                </div>
                 <div id="col_b1"></div>
                <div class="zwei_box" id="col2">
                    <p>
                        <img src="img/samuel-girven-VJ2s0c20qCo-unsplash.jpg" alt="Hanteln" width="320" height="480">
                    </p>
                </div>
            </div>
            <div>
                <h2 class="text_block">Genug vor der Couch gesessen! Jetzt kannst Du Sport machen und deine Freunde
                    teilhaben lassen!</h2>
            </div>
            <div class="lowerBox">

                <div class="drei_box">
                    <h3>Warum Social Sport?</h3>
                    <img src="img/edgar-chaparro-sHfo3WOgGTU-unsplash.jpg" alt="Klimmzüge.jpg" width="324" height="480">
                    <p class="text_block">
                        <br> Verwende Social Sport, um deinen Trainingserfolg zu verfolgen und immer zu wissen wie viel
                        Gewicht du hebst.
                        Damit kannst du deinen Erfolg genau kontrollieren und ebenfalls motiviert an Dir weiterarbeiten.
                    </p>
                </div>
                <div class="drei_box">
                    <h3>Was ist daran die soziale Komponente?</h3>
                    <img src="img/logan-weaver-lgnwvr-9D_rUDe7xvA-unsplash.jpg" alt="Freunde.jpg" width="324"
                        height="480">
                    <p class="text_block">
                        <br> Du erhälst je nach absolvierter Trainingseinheit Punkte, welche du mit deinen Freunden
                        teilen kannst oder auch bei besonders großem Erfolg 
                        auf dem globalen Leaderboard von Social Sport erscheinen können und dich somit mit anderen Mitgliedern
                        messen.
                    </p>
                </div>
                <div class="drei_box">
                    <h3>An wen richtet sich diese Website?</h3>
                    <img src="img/max-libertine-Pi4MOqVb85c-unsplash.jpg" alt="Kämpferin.jpg" width="324" height="480">
                    <p class="text_block">
                        <br> Ganz unabhängig von Deinem Level, - ob bluter Anfänger oder eiserner Bodybuilder - wirst
                        Du dich hier wohl fühlen.
                        Also probier es aus und lass deine Freunde an deinem Trainingserfolg teilhaben.
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
                    Aus diesem Grund schufen wir diese Plattform, welche wir auch selber nutzen und davon profitieren.
                </p>
            </div>
        </footer>
            <%@ include file="html/fragments/footer.jspf" %>
    </body>

    </html>