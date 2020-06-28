<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>FiscianoBetting - ${param.pageTitle}</title>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="./foto/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="./css/pg.css?ts=<?=time()?>&quot"> <!--altrimenti non si aggiorna -->
    <link rel="stylesheet" type="text/css" href="./css/elementi.css?ts=<?=time()?>&quot">
    <link rel="stylesheet" type="text/css" href="./css/partite.css?ts=<?=time()?>&quot">
    <link rel="stylesheet" type="text/css" href="./css/responsive.css?ts=<?=time()?>&quot">
    <script src="js/responsive.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />

</head>
<body>
<header>
    <div>

        <h1>FiscianoBetting <img src="./foto/i2.png" class="logo"></h1>


    </div>
    <p>Tutti i migliori eventi <b>sportivi</b></p>

    <c:if test="${utente != null}">
        <p style="float:right;padding-right:25px;">Benvenuto ${utente.nome}</p>
    </c:if>
</header>
<div class="topnav" id="myTopnav">
    <a href="." >Home</a>
    <a href="SchedinaBott">Schedina</a>
    <div class="dropdown">
        <button class="dropbtn">Classifiche
        </button>
            <div class="dropdown-content">
                <c:forEach items="${campionati}" var="campionato">
                    <a href="ClassificaServlet?id=${campionato.id}&nome=${campionato.nome}">${campionato.nome}</a>
                </c:forEach>
            </div>
    </div>
    <c:if test="${utente==null}">
        <a href="RegistrazioneForm">Registrati</a>
    </c:if>
    <a href="ChiSiamo">Chi siamo</a>
    <div class="dropdown">
        <button class="dropbtn">Area Personale
            <!--<i class="fa fa-caret-down"></i>-->
        </button>
        <div class="dropdown-content">
        <c:choose>
            <c:when test="${utente == null}">
                <form action="Login" method="post">
                    <label for="username">login</label>
                    <input type="text" id="username" name="username">
                    <label for="password">password</label>
                    <input type="password" id="password" name="password">
                    <input type="submit">
                </form>
            </c:when>
            <c:otherwise>

                <c:if test="${utente.admin}">
                    <div class="log">Admin ${utente.nome}</div>
                    <a href="AdminPartitaForm">Aggiungi Partita</a>
                    <a href="AdminSchedine">Schedine</a>
                    <a href="AdminUtenti">Utenti</a>
                    <hr style="margin:0px;">
                </c:if>
                <c:if test="!${utente.admin}">
                    <div class="log">Benvenuto ${utente.nome}</div>
                </c:if>
                <a href="ProfiloServlet">Profilo</a>
                <a href="MostraSchedine">Le mie schedine</a>
                <form action="Logout">
                    <input type="submit" value="Logout">
                </form>
            </c:otherwise>
        </c:choose>
        </div>
    </div>
    <a href="Ricerca">Ricerca</a>
    <a href="javascript:void(0);" class="icon" onclick="myFunction()">&#9776;</a>
</div>


