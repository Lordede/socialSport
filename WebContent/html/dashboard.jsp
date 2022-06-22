<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Exercise Dashboard</title>
        <meta charset="utf-8">
        <meta name="author" content="Cem Durmus">
        <meta name="description" content="main page to have overview of avaible exercises on SocialSport">
        <meta name="keywords" content="Training Sport Exerices">

        <base href="${pageContext.request.requestURI}" /> <!-- Basis für relative Verweise -> Basis = Speicherort -->

        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap" rel="stylesheet">
        <!--         https://stackoverflow.com/questions/20490848/servlet-not-redirected-to-page-showing-non-styled-page -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard.css"> 
        
        <script src="../JavaScript/favoriteButton.js" type="text/javascript"></script>
        <%-- Hubertus --%>
        <script src="../JavaScript/CheckForCookie.js" ></script>
        <%@ include file="fragments/authenticationCheckHEAD.jspf" %>
        <%@ include file="fragments/navbarHEAD.jspf" %>
        <%-- -------- --%>
    </head>
    <body>
    <%@ include file="fragments/authenticationCheck.jspf" %> <%-- Überprüfung, ob User eingeloggt ist --%>
    <%@ include file="fragments/navbar.jspf" %>              <%-- Navbar --%>
    <%@ include file="fragments/noScript.jspf" %>            <%-- Auf deaktiviertes js prüfen --%>
    
        <header>
            <h1><strong>Dashboard</strong></h1>
        </header>
          
        <article>
            <div class="Armmuskulatur">
                <article class="col1">
                    <button data-exercise="Bizepscurls" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Bizepscurls</h2>
                    <img src="../img/ExerciseImages/bizeps_curls_kurzhantel-beidarmig.gif" alt="Bizepscurls mit Kurzhanteln " width="180" height="180">
                    <div class="text_Bizepscurls">
                        Bizepscurls ist eine der klassichen Übungen, wenn es um den Aufbau vom Bizeps geht, vorgemacht von Berühmtheiten Arnold Schwarznegga.
                        Dabei ist der <strong>schwierigkeitsgrad dieser Übung gering</strong> und kann somit auch von Leihen mit der Kurzhantel ausgeführt werden.
                        <h4>Beanspruchte Muskeln</h4>
                        Wie der Name schon sagt, wird dabei in erster Linie der Bizeps-Muskel(musculus biceps brachii) angesprochen. Unterstützend wirken dabei auch der Armbeuger(musculus branchii) und Oberarmspeichenmuskel(musculus brachioradialis).
                    </div>
                </article>
                <article class="col1">
                    <button data-exercise="Hammercurls" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Hammercurls</h2>
                    <img src="../img/ExerciseImages/hammer-curls-stehend-kurzhanteln.gif" alt="stehende Hammercurls" width="180" height="180">
                    <div class="text_Hammercurls">
                        Hammercurls gehört zu den beliebtesten Übungen beim Bizepstraining und ist somit gar nicht mehr weg zu denken bei Sportlern, die dicke Oberarme möchten.
                        Diese Übung gilt als eine <strong>anfängerfreundliche Übung</strong>, sie sollte jedoch nicht unterschätzt werden.
                        <h4>Beanspruchte Muskeln</h4>
                        Dabei wird vorallem Bizeps-Muskel(musculus biceps brachii) beansprucht. Unterstützend sind dabei der Armbeuger (musculus brachialis), Oberarmspeichenmuskel (musculus brachioradialis), der lange radiale Handstrecker (musculus extrensor carpi radialis longus) und zuletzt der kurze radiale Handstrecker (musculus extensor carpi radialis longus)
                    </div>
                </article>
                <article class="col1">
                    <button data-exercise="Strindrücken mit SZ-Stange" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Stirndrücken mit SZ-Stange</h2>
                    <img src="../img/ExerciseImages/french-press-sz.gif" alt="Strindrücken mit SZ-Stange" width="180" height="180">
                    <div class="text_Trizeps">
                        Stirndücken oder auch French Press, Nosebreaker, Skull Crushers bezeichnet hört sich anfangs zwar ziehmlich martialisch an, jedoch ist sie eine der effektivsten Trizepsübungen überhaupt.
                        Diese Übung gilt als <strong>nicht anfängerfreundliche</strong>, jedoch ist sie nach ein wenig Übung gut zu meistern.
                        <h4>Beanspruchte Muslkeln</h4>
                        Mit dieser Übung wird vorrainig die Trizepsmuskulatur(musculus triceps brachii) traininert, auch wird dem Knorrenmuskel(musculus anconaeus) ein Trainingsreiz gegeben. Unterstützend wirkt dabei der Handstrecker (musculus extensor carpi) und der gemeinsame Fingerstrecker (musculus extensor digitorum).
                    </div>
                </article>
            </div>
                <div class="row2">

                    <article class="col1_1">
                        <button data-exercise="Trizepsdrücken am Kabelturm" class="fav-button">
                            <i class="fa fa-star"></i>
                            <span>Übung merken</span>
                        </button>
                        <h2>Trizepsdrücken am Kabelturm</h2>
                        <img src="../img/ExerciseImages/trizeps-pushdown-kabelzug-seil.gif" alt="Trizepsdrücken am Kabelturm" width="180" height="180">
                        <div>
                            Trizepsdrücken am Kableturm zählt zu den must dos im Trizepstraining, damit zählt sie zu den bekanntesten und meist ausgeführten Übungen überhaupt.
                            Vorallem ist sie <strong>anfängerfreundlich</strong> deshalb sollte sie immer im Trizepstraining dabei sein. 
                            <h4>Beanspruchte Muskeln</h4>
                            Hauptsächlich wird damit der Trizeps-Muskel(musculus triceps brachii) und der Knorrenmuskel(musculus anconaeus) trainiert.
                            Dabei unterstützt der Handstrecker(musculus extensor carpi), der Fingerstrecker(musculus extensor capri) und zuletzt der Kleinfingerstrecker(musculus extensor digitorum)
                        </div>
                    </article>
                    <article class="col1_1">
                        <button data-exercise="Seitenheben mit Kurzhanteln" class="fav-button">
                            <i class="fa fa-star"></i>
                            <span>Übung merken</span>
                        </button>
                        <h2>Seitenheben mit Kurzhanteln</h2>
                        <img src="../img/ExerciseImages/seitheben-kurzhanteln-stehend.gif" alt="Seitenheben mit Kurzhanteln" width="180" height="180">
                        <div>
                            Das Seitheben erfreut sich im Fitnessstudio einer großen Beliebtheit, welche diese Isolationsübung auch wirklich verdient hat. Diese Übung kann auch in vielerlei Weisen ausgeführt werden (sitzend/stehend).
                            Das Anspruchsniveau gilt als<strong>eher gering</strong> und ist somit in jedem Trainingsniveau in das Training integrierbar. 
                            <h4>Beanspruchte Muskeln</h4>
                            Das Seitheben beansprucht die Deltamuskulatur(musculus deltoideus), sowie den mittleren Teil des Deltamuskels(musculus deltoideus pars acromialis). Unterstützen tut dabei der Trapezmuskel(musculus trapezius pars descendens).
                        </div>
                    </article>
                    <article class="col1_1">
                        <button data-exercise="Schulterdrücken sitzend mit Kurzhanteln" class="fav-button">
                            <i class="fa fa-star"></i>
                            <span>Übung merken</span>
                        </button>
                        <h2>Schulterdrücken sitzend mit Kurzhanteln</h2>
                        <img src="../img/ExerciseImages/schulterdruecken-kurzhanteln-sitzend.gif" alt="Schulterdrücken sitzend" width="180" height="180">
                        <div>
                            Das Schulterdrücken eignet sich super als Schulterübung und sie zählt zu den Verbund- und Grundübungen.
                            Diese Übung hat auch viele Variationen - mit Langhantel, im stehen.
                            Der Schwierigkeitsgrad ist dabei <strong>mittel</strong> somit geeignet für Fortgeschrittene und Profis.
                            <h4>Beanspruchte Muslkeln</h4>
                            Dies Übung spricht den Deltamuskel(musculus deltoideus) ganzheitlich an, d. h. der obere Teil des Kapuzenmuskels(musculus trapezius pars descendens) und den Trizeps (musculus triceps brachii).
                            Unterstützend wirkt dabei der Knorrenmuskel(musculus anconaeus) und der vordere Sägemuskel (musculus serratus anterior)
                        </div>
                    </article>
                </div>
            <div class="Brustmuskulatur">
                <article class="col1">
                    <button data-exercise="Bankdrücken Langhantel" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Bankdrücken Langhantel</h2>
                    <img src="../img/ExerciseImages/bankdruecken_langhantel.gif" alt="Bankdrücken" width="180" height="180">
                    <div>
                        Wer an Kraftsport denkt, hat direkt ein Bild im Kopf: das Bankdrücken, einer der bekanntesten, wenn nicht die bekannteste Übung überhaupt. 
                        Dabei kann man auch zwischen Kurzhantel und Langhantel variieren.
                        Erstaunlicherweise ist Anfangs eine saubere Ausführung schwierig zu meistern, weshalb diese Übung als <strong>mittelschwer</strong> einzustufen ist.
                        <h4>Beanspruchte Muskeln</h4>
                        Bei dieser Übung stärken wir vorallem unser großen Brustmuskel (musculus pectoralis major), sowie den Trizeps (musculus triceps brachii).
                        Zur unterstützenden Muskulatur gehören der vordere Teil deines Deltamuskels, der vordere Sägemuskel (musculus serratus anterior) und der Knorrenmuskel (musculus anconaeus).
                    </div>
                </article>
                <article class="col1">
                    <button data-exercise="Schrägbankdrücken" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Schrägbankdrücken</h2>
                    <img src="../img/ExerciseImages/bankdruecken_langhantel-schraegbank.gif" alt="Schrägbankdrücken" width="180" height="180">
                    <div>
                        Das Schrägbankdrücken ist auch eine sehr weitverbreitet Übung, jedoch ist die Ausführung sogar schwieriger als beim Flachbankdrücken,
                        jedoch kann man im allgemeinen sagen, dass der schwierigkeitsgrad hierbei auch <strong>im mittlerenbereich</strong> liegt. 
                        <h4>Beanspruchte Muskeln</h4>
                        Bei dieser Übung stärken wir vorallem unser großen Brustmuskel (musculus pectoralis major), sowie den Trizeps (musculus triceps brachii).
                        Der Unterschied zum Flachbankdrücken liegt jedoch hierbei darin, dass der Oberebereich des großen Brustmuskels hier im Fokus steht.
                        Zur unterstützenden Muskulatur gehören der vordere Teil deines Deltamuskels, der vordere Sägemuskel (musculus serratus anterior) und der Knorrenmuskel (musculus anconaeus).
                    </div>
                </article>
                <article class="col1">
                    <button data-exercise="Überzug liegend am Kabelturm" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Überzug liegend am Kabelturm</h2>
                    <img src="../img/ExerciseImages/kabelueberzuege.gif" alt="Überzug Kabelturm" width="180" height="180">
                    <div>
                        Diese Übung ist eine der effektivsten Brustübungen, wobei ein großer Vorteil dabei ist, dass man hier für keinen Trainingspartner braucht, d. h. auch dass die Verletzungsgefahr hierbei geringer ist als 
                        beim klassichen Bankdrücken.
                        Jedoch ist es anfangs schwer weshalb diese Übung als <strong>anspruchsvoll</strong> zu qualifizieren ist.
                        <h4>Beanspruchte Muskeln</h4>
                        Als Hauptmuskel wird hierbei der Sägemuskel (musculus serratus anterior) trainiert, wie auch der große Brustmuskel (musculus pectoralis major).
                        Unterstützt werden diese Muskeln dabei vom Latissimus (musculus latissiums dorsi) und auch der Trizeps (musculus triceps brachii)
                    </div>
                </article>
            </div>
            <div class="Rueckenmuskulatur">
                <article class="col1">
                    <button data-exercise="Latzug" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Latzug</h2>
                    <img src="../img/ExerciseImages/latziehen-enger-griff-mit-stange.gif" alt="Latzug" width="180" height="180">
                    <div>
                        Latzug ist eine der bekanntesten und effektivsten Übungen für die Rückenmuskulatur. Häufig triftt man dabei viele Variationen mit
                        variierenden Griffen(eng/breit).
                        Dies Übung ist auch als <strong>anfängerfreundlich</strong> zu erachten, weshlab sie jedermann empfohlen werden kann.
                        <h4>Beanspruchte Muskeln</h4>
                        Der Fokus dieser Übung liegt auf dem breiten Rückenmuskel (musculus latissimus dorsi), gefolgt von einem Reiz auf die 
                        unteren Fasern des Kapuzenmuskels (musculus trapezius pars ascendens)
                        sowie der Rautenmuskel (musculus rhomboideus).
                        Unterstützend wirkt dabei der Rückenstrecker (musculus erector spinae), Bizeps (musculus biceps brachii) und Armbeuger (musculus brachialis).
                    </div>
                </article>
                <article class="col1">
                    <button data-exercise="Gebeugtes Rudern mit Langhantel" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Gebeugtes Rudern mit Langhantel</h2>
                    <img src="../img/ExerciseImages/lh_rudern_vorgebeugt-untergriff.gif" alt="gebeugtes Rudern" width="180" height="180">
                    <div>
                        Gebeugtes Rudern mit Langhantel ist sehr effektive Übung in Punkto Rückenmuskulatur. Sie birgt jedoch ein hohes Verletzungspotenzial bei einer falschen Ausführung.
                        Jedoch hilft diese Übung die Fehlhaltung die durch die Arbeit am Rechner entsteht zu neutralisieren.
                        Das macht dies Übung <strong>anspruchsvoll</strong> und damit sollte sie im Trainingsplan von Fortgeschrittenen bis Profis aufgenommen werden.
                        <h4>Beanspruchte Muskeln</h4>
                        Hauptsächlich wird bei dieser Übung der breite Rückenmuskel (musculus latissimus dorsi) trainiert, dabei wird auch der hintere Teil des Deltamuskels (musculus deltoideus pars clavicularis) und der Kapuzenmuskel (musculus trapezius) Stimmuliert.
                        Unterstützen tut dabei der Bizeps (musculus biceps brachii) und der Armbeuger (musculus brachialis)
                    </div>
                </article>
                <article class="col1">
                    <button data-exercise="Rudern am Kabelturm" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Rudern am Kabelturm</h2>
                    <img src="../img/ExerciseImages/enges-rudern-am-kabelzug-v-griff.gif" alt="enges Rudern am Kabelzug" width="180" height="180">
                    <div>
                        Das Rudern am Kabelturm zählt zu den beliebtesten Übungen im Fitnessstudio, somit ist Sie auch in jedem Fitnessstudio umzusetzen.
                        Dabei gibt es verschiedene Griffe und Greifmöglichkeiten und das führt dazu, verschiedene Muskeln ansprechen zu können.
                        <h4>Beanspruchte Muskeln</h4>
                        Wie bei den meisten Rückenübungen wird hierbei der breite Rückenmuskel (musculus latissimus dorsi) trainiert, sowie die hinternen Schultern
                        (musculus deltoideus pars clavicularis).
                        Unterstützend wirkt dabei Kapuzenmuskel (musculus trapezius),  der Kapuzenmuskel (musculus trapezius), der  Bizeps (musculus biceps brachii) und Armbeuger (musculus brachialis).
                    </div>
                </article>
            </div>
            <div class="Beinmuskulatur">
                <article class="col1">
                    <button data-exercise="Beinpresse" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Beinpresse</h2>
                    <img src="../img/ExerciseImages/beinpresse-mit-beiden-beinen-hoch.gif" alt="Beinpresse" width="180" height="180">
                    <div>
                        Ist der Klassiker schlecht hin bei den Beiübungen und diese stellt auch eine der wenigen Übungen dar, die unsere Plattform 
                        an einer Maschine vorgestellt werden. 
                        Sie ist eine <strong>einfache</strong> Übung und kann somit von jedem Sportler/in vollführt werden.
                        <h4>Beanspruchte Muskeln</h4>
                        Der primäre Zielmuskel bei dieser Übung ist der vierköpfige Oberschenkel (musculus quadriceps femoris), der Beinbizeps (musculus biceps femoris) 
                        und der große Gesäßmuskel (musculus gluteus maximus). Unterstützung gibt es dabei durch den  Rückenstrecker (musculus erector spinae) und den dreiköpfige Adduktor (musculus adductor).
                    </div>
                </article>
                <article class="col1">
                    <button data-exercise="Ausfallschritte mit Langhantel" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Ausfallschritte mit Langhantel</h2>
                    <img src="../img/ExerciseImages/ausfallschritt_langhantel_seitlich.gif" alt="Ausfallschritte mit der Langhantel" width="180" height="180">
                    <div>
                        Ausfallschritte (engl. Lunges) sind auch aus dem Eigenkörpergewicht segment bekannt. Jedoch bringen hierbei Gewichte mehr freude in diese Übung,
                        weshalb diese als unversichtbar beim Beintraining gilt.
                        Bei dieser Übung ist es schwierig die Balance zu halten, weshalb sie der <strong>mittelschwer</strong> gilt.
                        <h4>Beanspruchte Muskeln</h4>
                        Zielmuskel bei dieser Übung ist der große Gesäßmuskel (musculus gluteus maximus) und der vierköpfige Oberschenkelmuskel (musculus quadriceps femoris)
                        Als unterstützende Muskulatur gilt der Beinbizeps (musculus biceps femoris).
                    </div>
                </article>
                <article class="col1">
                    <button data-exercise="Kniebeugen mit Langhantel" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Kniebeugen mit Langhantel</h2>
                    <img src="../img/ExerciseImages/43621301-Barbell-Squat-2-sec-Hold_Thighs_360.gif" alt="Kniebeugen mit Langhantel" width="180" height="180">
                    <div>
                        Auch ein weitverbreiter Klassiker unter den Beinübungen gelten Kniebeugen (engl.: Squads), welche unversichtbar in jedes Training aufgenommen werden müssen,
                        wo ein großer Fokus auf Kraft gelegt wird.
                        Diese Übung gilt als eher <strong>anspruchsvoll</strong>.
                        <h4>Beanspruchte Muskeln</h4>
                        Hauptmuskel dieser Übung ist der vierköpfigen Oberschenkelmuskel (musculus quadrices femoris), Beinbizeps (musculus biceps femoris) und der große Gesäßmuskel (musculus gluteus maximaus).
                        In eine Unterstützende Funktion tritt dabei der, Rückenstrecker (musculus erector spinae) und dem dreiköpfigen Adduktor (musculus adductor).
                    </div>
                </article>
            </div>
            <div class="Bauchmuskulatur">
                <article class="col1">
                    <button data-exercise="Situps auf Schrägbank" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Situps auf Schrägbank</h2>
                    <img src="../img/ExerciseImages/negative-sit-ups-auf-der-schraegbank-mit-gewicht.gif" alt="Negaitve Situps" width="180" height="180">
                    <div>
                        Situps sind weitverbreitet auch in der Gesellschaft bei Menschen ohne Fitnessexpertisen, jedoch wird bei dieser Übung das meiste aus dieser Übung geholt.
                        Denn hierbei wird sich auf eine Bank mit negativer Steigung gelegt, um mehr Beansprung in den Muskel zu bekommen. 
                        Dazu berufen wir uns darauf, ein Gewicht zu heben während der Übungsausführung, dadurch erhält dies Übungen einen <strong>mittleren</strong> Schwierigkeitsgrad.
                        <h4>Beanspruchte Muskeln</h4>
                        Die negativen Sit-Ups zielen auf das Training des geraden Bauchmuskels (musculus rectus abdominis) und des pyramidenförmigen Muskels (musculus pyramidalis) ab.
                        Zur unterstützenden Muskulatur gehört der schräge Bauchmuskel, der bei dieser Übung (musculus obliquus abdominis) ebenfalls gestärkt wird.
                    </div>
                </article>
                <article class="col1">
                    <button data-exercise="Crunsh Maschine" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Crunsh Maschine</h2>
                    <img src="../img/ExerciseImages/crunches-an-der-crunch-maschine-polster-vor-der-brust.gif" alt="Crunshes an Maschine" width="180" height="180">
                    <div>
                        Crunshes beanspruchen die Bauchmuskeln sehr stark sodass sie eine unvergessliche Übung für die Bauchmuskeln sind. Da es sich dabei um eine
                        geführte Übung handelt ist auch der Schwierigkeitsgrad als eher <strong>leicht</strong> einzustufen. 
                        <h4>Beanspruchte Muskeln</h4>
                        Bei dieser Übung wird hauptsächlich der gerade Bauchmuskel (musculus rectus abdominis), sowie der yramidenförmige Muskel (musculus pyramidalis) stimmuliert.
                        Unterstütz wird dabei durch den schrägen Bauchmuskel (musculus obliquus abdominis) gewährleistet.
                    </div>
                </article>
                <article class="col1">
                    <button data-exercise="Seitliches Oberkörperbeugen mit Kurzhanteln" class="fav-button">
                        <i class="fa fa-star"></i>
                        <span>Übung merken</span>
                    </button>
                    <h2>Seitliches Oberkörperbeugen mit Kurzhanteln</h2>
                    <img src="../img/ExerciseImages/seitliches-oberkoerperbeugen-mit-der-kurzhantel.gif" alt="Seitliches Oberkörperbeugen" width="180" height="180">
                    <div>
                        Seitliches Oberkörperbeugen ist ein must do beim Bauchtraining, da diese Übung auch im Alltag eine Unterstützende Wirkung hat und die Körperhaltung verbesser.
                        Das Anspruchsniveau ist dabei als <strong>mäßig</strong> zu betrachten, sollte somit mit ein bisschen Übung auch von Laien durchführbar sein.
                        <h4>Beanspruchte Muskeln</h4>
                        Vorrangig wird bei dieser Übung der schräge bzw. seitliche Bauchmuskel (musculus obliquus abdominis) trainiert.
                        Zur unterstützenden Muskulatur gehören der gerade Bauchmuskel (musculus rectus abdominis) und der pyramidenförmige Muskel (musculus pyramidalis).
                    </div>
                </article>
            </div>
        </article>
        <script>
            const favButtons = document.querySelectorAll("button");
            favButtons.forEach(button => button.addEventListener("click", event => {
                   console.log(button.dataset.exercise);
                   dbCall(button.dataset.exercise);
                })); 

            // Hubertus Seitz
            document.addEventListener("DOMContentLoaded", init);
           
            function init() {
               checkForCookie ()
            }
            // ---
        </script>
    </body>
</html>
