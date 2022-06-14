<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="de">
    <head>
        <title>Exercise Dashboard</title>
        <meta charset="utf-8">
        <meta name="author" content="Cem Durmus">
        <meta name="description" content="main page to have overview of your Training and the leaderbord">
        <meta name="keywords" content="Training Sport Leaderboard">
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&family=Open+Sans:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="../css/layout.css">
<!--         https://stackoverflow.com/questions/20490848/servlet-not-redirected-to-page-showing-non-styled-page -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navbar.css" />
        <base href="${pageContext.request.requestURI}" /> <!-- Basis f�r relative Verweise -> Basis = Speicherort -->
    </head>
    <body>
    <%@ include file="fragments/authenticationCheck.jspf" %> <%-- �berpr�fung, ob User eingeloggt ist --%>
    <nav>
    		<ul>
                <li><a class="active" href="./dashboard.jsp">Dashboard</a></li>
                <li><a href="./trainingsmenu.jsp">Training</a></li>
                <li><a href="./leaderboard.jsp">Leaderboard</a></li>
                <li><a href="./accountSetting.jsp">Account Settings</a></li>
                <li><a href="./exercise.jsp">Übung erstellen</a></li>
  				<li class="logout"><a class="active" href="#about">Logout</a></li>
			</ul> 
    	</nav>
        <header>
            <h1><strong>Dashboard: </strong></h1>
            <input type="search" 
            name="suche"
            
            formaction="get"
            formmethod="">
        </header>
        <article>
            <div class="Armmuskulatur">
                <article class="Bizepscurls">
                    <h2>Bizepscurls</h2>
                    <img src="" alt="">
                    <div class="text_Bizepscurls">
                        Bizepscurls ist eine der klassichen Übungen, wenn es um den Aufbau vom Bizeps geht, vorgemacht von Berühmtheiten Arnold Schwarznegga.
                        Dabei ist der <strong>schwierigkeitsgrad dieser Übung gering</strong> und kann somit auch von Leihen mit der Kurzhantel ausgeführt werden.
                        <h4>Beanspruchte Muskeln</h4>
                        Wie der Name schon sagt, wird dabei in erster Linie der Bizeps-Muskel(musculus biceps brachii) angesprochen. Unterstützend wirken dabei auch der Armbeuger(musculus branchii) und Oberarmspeichenmuskel(musculus brachioradialis).
                    </div>
                </article>
                <article class="Hammercurls">
                    <h2>Hammercurls</h2>
                    <img src="" alt="">
                    <div class="text_Hammercurls">
                        Hammercurls gehört zu den beliebtesten Übungen beim Bizepstraining und ist somit gar nicht mehr weg zu denken bei Sportlern, die dicke Oberarme möchten.
                        Diese Übung gilt als eine <strong>anfängerfreundliche Übung</strong>, sie sollte jedoch nicht unterschätzt werden.
                        <h4>Beanspruchte Muskeln</h4>
                        Dabei wird vorallem Bizeps-Muskel(musculus biceps brachii) beansprucht. Unterstützend sind dabei der Armbeuger (musculus brachialis), Oberarmspeichenmuskel (musculus brachioradialis), der lange radiale Handstrecker (musculus extrensor carpi radialis longus) und zuletzt der kurze radiale Handstrecker (musculus extensor carpi radialis longus)
                    </div>
                </article>
                <article class="Trizepsdruecken_SZ">
                    <h2>Strindrücken mit SZ-Stange</h2>
                    <img src="" alt="">
                    <div class="text_Trizeps">
                        Stirndücken oder auch French Press, Nosebreaker, Skull Crushers bezeichnet hört sich anfangs zwar ziehmlich martialisch an, jedoch ist sie eine der effektivsten Trizepsübungen überhaupt.
                        Diese Übung gilt als <strong>nicht anfängerfreundliche</strong>, jedoch ist sie ein wenig Übung gut zu meistern.
                        <h4>Beanspruchte Muslkeln</h4>
                        Mit dieser Übung wird vorrainig die Trizepsmuskulatur(musculus triceps brachii) traininert, auch wird dem Knorrenmuskel(musculus anconaeus) ein Trainingsreiz gegeben. Unterstützend wirkt dabei der Handstrecker (musculus extensor carpi) und der gemeinsame Fingerstrecker (musculus extensor digitorum).
                    </div>
                </article>
                <article class="Trizepsdruecken_kabel">
                    <h2>Trizepsdrücken am Kabelturm</h2>
                    <img src="" alt="">
                    <div>
                        Trizepsdrücken am Kableturm zählt zu den must dos im Trizepstraining, damit zählt sie zu den bekanntesten und meist ausgeführten Übungen überhaupt.
                        Vorallem ist sie <strong>anfängerfreundlich</strong> deshalb sollte sie immer im Trizepstraining dabei sein. 
                        <h4>Beanspruchte Muskeln</h4>
                        Hauptsächlich wird damit der Trizeps-Muskel(musculus triceps brachii) und der Knorrenmuskel(musculus anconaeus) trainiert.
                        Dabei unterstützt der Handstrecker(musculus extensor carpi), der Fingerstrecker(musculus extensor capri) und zuletzt der Kleinfingerstrecker(musculus extensor digitorum)
                    </div>
                </article>
                <article class="Seitenheben">
                    <h2>Seitenheben mit Kurzhanteln</h2>
                    <img src="" alt="">
                    <div>
                        Das Seitheben erfreut sich im Fitnessstudio einer großen Beliebtheit, welche diese Isolationsübung auch wirklich verdient hat. Diese Übung kann auch in vielerlei Weisen ausgeführt werden (sitzend/stehend).
                        Das Anspruchsniveau gilt als<strong>eher gering</strong> und ist somit in jedem Trainingsniveau in das Training integrierbar. 
                        <h4>Beanspruchte Muskeln</h4>
                        Das Seitheben beansprucht die Deltamuskulatur(musculus deltoideus), sowie den mittleren Teil des Deltamuskels(musculus deltoideus pars acromialis). Unterstützen tut dabei der Trapezmuskel(musculus trapezius pars descendens).
                    </div>
                </article>
                <article class="Schulterdruecken_Kh">
                    <h2>Schulterdrücken sitzend mit Kurzhanteln</h2>
                    <img src="" alt="">
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
                <article class="Bankdruecken">
                    <h2>Bankdrücken Langhantel</h2>
                    <img src="" alt="">
                    <div>
                        Wer an Kraftsport denkt, hat direkt ein Bild im Kopf: das Bankdrücken, einer der bekanntesten, wenn nicht die bekannteste Übung überhaupt. 
                        Dabei kann man auch zwischen Kurzhantel und Langhantel variieren.
                        Erstaunlicherweise ist Anfangs eine saubere Ausführung schwierig zu meistern, weshalb diese Übung als <strong>mittelschwer</strong> einzustufen ist.
                        <h4>Beanspruchte Muskeln</h4>
                        Bei dieser Übung stärken wir vorallem unser großen Brustmuskel (musculus pectoralis major), sowie den Trizeps (musculus triceps brachii).
                        Zur unterstützenden Muskulatur gehören der vordere Teil deines Deltamuskels, der vordere Sägemuskel (musculus serratus anterior) und der Knorrenmuskel (musculus anconaeus).
                    </div>
                </article>
                <article class="Schrägbankdruecken">
                    <h2>Schrägbankdrücken</h2>
                    <img src="" alt="">
                    <div>
                        Das Schrägbankdrücken ist auch eine sehr weitverbreitet Übung, jedoch ist die Ausführung sogar schwieriger als beim Flachbankdrücken,
                        jedoch kann man im allgemeinen sagen, dass der schwierigkeitsgrad hierbei auch <strong>im mittlerenbereich</strong> liegt. 
                        <h4>Beanspruchte Muskeln</h4>
                        Bei dieser Übung stärken wir vorallem unser großen Brustmuskel (musculus pectoralis major), sowie den Trizeps (musculus triceps brachii).
                        Der Unterschied zum Flachbankdrücken liegt jedoch hierbei darin, dass der Oberebereich des großen Brustmuskels hier im Fokus steht.
                        Zur unterstützenden Muskulatur gehören der vordere Teil deines Deltamuskels, der vordere Sägemuskel (musculus serratus anterior) und der Knorrenmuskel (musculus anconaeus).
                    </div>
                </article>
                <article class="Brust_Kabel_ueberzug">
                    <h2>Überzug liegend am Kabelturm</h2>
                    <img src="" alt="">
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
                <article class="Latzug_breit">
                    <h2>Beanspruchte Muskeln</h2>
                    <img src="" alt="">
                    <div>
                        <h4>Beanspruchte Muskeln</h4>
                    </div>
                </article>
                <article class="rudern_Langhantel">
                    <h2>Gebeugtes Rudern mit Langhantel</h2>
                    <img src="" alt="">
                    <div>
                        <h4>Beanspruchte Muskeln</h4>
                    </div>
                </article>
                <article class="rudern_kabelturm">
                    <h2>Rudern am Kabelturm</h2>
                    <img src="" alt="">
                    <div>
                        <h4>Beanspruchte Muskeln</h4>
                    </div>
                </article>
            </div>
            <div class="Beinmuskulatur">
                <article class="Beinpresse">
                    <h2>Beinpresse</h2>
                    <img src="" alt="">
                    <div>
                        <h4>Beanspruchte Muskeln</h4>
                    </div>
                </article>
                <article class="Ausfallschritte">
                    <h2>Ausfallschritte mit Langhantel</h2>
                    <img src="" alt="">
                    <div>
                        <h4>Beanspruchte Muskeln</h4>
                    </div>
                </article>
                <article class="Kniebeugen">
                    <h2>Kniebeugen mit Langhantel</h2>
                    <img src="" alt="">
                    <div>
                        <h4>Beanspruchte Muskeln</h4>
                    </div>
                </article>
            </div>
            <div class="Bauchmuskulatur">
                <article class="Situps_schraegbank">
                    <h2>Situps auf Schrägbank</h2>
                    <img src="" alt="">
                    <div>
                        <h4>Beanspruchte Muskeln</h4>
                    </div>
                </article>
                <article class="Crunshes_maschine">
                    <h2>Crunsh Maschine</h2>
                    <img src="" alt="">
                    <div>
                        <h4>Beanspruchte Muskeln</h4>
                    </div>
                </article>
                <article class="seitliches_oberkoerperbeugen_kh">
                    <h2>Seitliches Oberkörperbeugen mit Kurzhanteln</h2>
                    <img src="" alt="">
                    <div>
                        <h4>Beanspruchte Muskeln</h4>
                    </div>
                </article>
            </div>
        </article>
    </body>
</html>
