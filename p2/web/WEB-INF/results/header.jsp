<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Franco
  Date: 05/05/2020
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FiscianoBetting - ${param.pageTitle}</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/pg.css?ts=<?=time()?>&quot"> <!--altrimenti non si aggiorna -->
    <link rel="stylesheet" type="text/css" href="./css/dropdown.css?ts=<?=time()?>&quot">
    <link rel="stylesheet" type="text/css" href="./css/partite.css?ts=<?=time()?>&quot">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="js/ricerca.js"></script>

</head>
<body>


<!-- Header -->
<header>
    <h1>FiscianoBetting</h1>
    <p>Tutti i migliori eventi <b>sportivi</b></p>
</header>

<ul>
    <li><a href=".">Home</a></li>
    <li><a href="#">Calcio</a></li>
    <li><a href="#">Classifiche</a></li>
    <li><a href="RegistrazioneForm">Registrati</a></li>
    <li><a href="#">Chi siamo</a></li>
    <li class="dropdown">
        <a href="LoginForm" class="dropbtn">Area Personale</a>
        <div class="dropdown-content">
            <a href="#">Link 1</a>
            <a href="#">Link 2</a>
            <form action="Login" method="post">
                <label for="username">login</label>
                <input type="text" id="username" name="username">
                <label for="password">password</label>
                <input type="password" id="password" name="password">
                <input type="submit">
            </form>
        </div>
    </li>
    <li class="ricerca">
        <form action="Ricerca" method="get">
            <input class="ricerca" type="text" name="q" list="ricerca-datalist" placeholder="Ricerca" onkeyup="" />
            <datalist id="ricerca-datalist"></datalist>
        </form>
    </li>
</ul>

