<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Franco
  Date: 13/04/2020
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Profilo"/>
</jsp:include>
<!-- The flexible grid (content) -->
<div class="row">
    <div class="side">
        <h2>Campionati</h2>
        <c:forEach items="${campionati}" var="campionato">
            <a href="CampionatoServlet?id=<c:out value="${campionato.id}"/>"><c:out
                    value="${campionato.nome}" /></a>
        </c:forEach>
    </div>
    <div class="main">
        <section>
            <h1>Modifica campi utente</h1>
            <h5>Se non vuoi cambiare la password lasciare vuoto il campo relativo</h5>
            <form name="modifica" action="ModificaProfilo" method="post">
                <label>Username (almeno 6 caratteri, solo lettere e numeri, non utilizzato da altri utenti)</label><br>
                <input type="text" name="username" oninput="validaUsername()" value="${utente.username}"><br>
                <label>Nome (solo lettere e spazi)</label><br>
                <input type="text" name="nome" oninput="validaNome()" value="${utente.nome}"><br>
                <label>Email ( diversa da quella di utenti esistenti)</label><br>
                <input type="text" name="email" oninput="validaEmail()"  value="${utente.email}"><br>
                <label>Password (almeno 8 caratteri, deve contenere: una lettera maiuscola, una minuscola, un numero)</label><br>
                <input type="password" name="password" oninput="validaPassword()"><br>
                <label>Password (conferma)</label><br>
                <input type="password" name="passwordConferma" oninput="validaPassword()"><br>
                <input id="modifica" type="submit" value="Modifica" disabled><span id="registramimessaggio"></span>
            </form>
        </section>
    </div>
</div>
<script>
    var borderOk = '2px solid #080';
    var borderNo = '2px solid #f00';
    var usernameOk = true;
    var passwordOk = true;
    var nomeOk = true;
    var emailOk = true;

    function validaUsername() {
        var input = document.forms['modifica']['username'];
        if (input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+$/)) {
            // verifica se esiste un utente con lo stesso username
            var xmlHttpReq = new XMLHttpRequest();
            xmlHttpReq.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200
                    && this.responseText == '<ok/>') {
                    usernameOk = true;
                    input.style.border = borderOk;
                } else {
                    input.style.border = borderNo;
                    usernameOk = false;
                }
                cambiaStatoRegistrami();
            }
            xmlHttpReq.open("GET", "VerificaUsername?username="
                + encodeURIComponent(input.value), true);
            xmlHttpReq.send();
        } else {
            input.style.border = borderNo;
            usernameOk = false;
            cambiaStatoRegistrami();
        }
    }

    function validaPassword() {
        var inputpw = document.forms['modifica']['password'];
        var inputpwconf = document.forms['modifica']['passwordConferma'];
        var password = inputpw.value;
        if (password.length >= 8 && password.toUpperCase() != password
            && password.toLowerCase() != password && /[0-9]/.test(password)) {
            inputpw.style.border = borderOk;

            if (password == inputpwconf.value) {
                inputpwconf.style.border = borderOk;
                passwordOk = true;
            } else {
                inputpwconf.style.border = borderNo;
                passwordOk = false;
            }
        } else {
            inputpw.style.border = borderNo;
            inputpwconf.style.border = borderNo;
            passwordOk = false;
        }
        if(password=="" && inputpwconf.value=="")
        {
            inputpw.style.border = borderOk;
            inputpwconf.style.border = borderOk;
            passwordOk = true;
        }
        cambiaStatoRegistrami();
    }

    function validaNome() {
        var input = document.forms['modifica']['nome'];
        if (input.value.trim().length > 0
            && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
            input.style.border = borderOk;
            nomeOk = true;
        } else {
            input.style.border = borderNo;
            nomeOk = false;
        }
        cambiaStatoRegistrami();
    }

    function validaEmail() {
        var input = document.forms['modifica']['email'];
        if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)) {
            input.style.border = borderOk;
            emailOk = true;
        } else {
            input.style.border = borderNo;
            emailOk = false;
        }
        cambiaStatoRegistrami();
    }

    function cambiaStatoRegistrami() {
        if (usernameOk && passwordOk && nomeOk && emailOk) {
            document.getElementById('modifica').disabled = false;
            document.getElementById('registramimessaggio').innerHTML = '';
        } else {
            document.getElementById('modifica').disabled = true;
            document.getElementById('registramimessaggio').innerHTML = 'Verifica che tutti i campi siano in verde.';
        }
    }
</script>

<jsp:include page="footer.jsp"/>
