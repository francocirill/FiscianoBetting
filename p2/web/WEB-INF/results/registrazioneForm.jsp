<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><%@taglib prefix="c"
                                         uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Registrazione utente"/>
</jsp:include>
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
    <h1>Registrazione utente</h1>
    <h5>Riempi tutti i campi</h5>
    <form name="registrazione" action="Registrazione" method="post">
        <label>Username (almeno 6 caratteri, solo lettere e numeri, non utilizzato da altri utenti)</label><br>
        <input type="text" name="username" oninput="validaUsername()"><br>
        <label>Password (almeno 8 caratteri, deve contenere: una lettera maiuscola, una minuscola, un numero)</label><br>
        <input type="password" name="password" oninput="validaPassword()"><br>
        <label>Password (conferma)</label><br>
        <input type="password" name="passwordConferma" oninput="validaPassword()"><br>
        <label>Nome (solo lettere e spazi)</label><br>
        <input type="text" name="nome" oninput="validaNome()"><br>
        <label>Email (diversa da quella di utenti esistenti)</label><br>
        <input type="text" name="email" oninput="validaEmail()"><br>
        <input id="registrami" type="submit" value="Registrami" disabled><span id="registramimessaggio"></span>
    </form>
</section>
</div>
</div>
<script>
    var borderOk = '2px solid #080';
    var borderNo = '2px solid #f00';
    var usernameOk = false;
    var passwordOk = false;
    var nomeOk = false;
    var emailOk = false;

    function validaUsername() {
        var input = document.forms['registrazione']['username'];
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
        var inputpw = document.forms['registrazione']['password'];
        var inputpwconf = document.forms['registrazione']['passwordConferma'];
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
        cambiaStatoRegistrami();
    }

    function validaNome() {
        var input = document.forms['registrazione']['nome'];
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
        var input = document.forms['registrazione']['email'];
        if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)) {
            // verifica se esiste un utente con la stessa email
            var xmlHttpReq = new XMLHttpRequest();
            xmlHttpReq.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200
                    && this.responseText == '<ok/>') {
                    input.style.border = borderOk;
                    emailOk = true;
                } else {
                    input.style.border = borderNo;
                    emailOk = false;
                }
                cambiaStatoRegistrami();
            }
            xmlHttpReq.open("GET", "VerificaEmail?email="
                + encodeURIComponent(input.value), true);
            xmlHttpReq.send();
        } else {
            input.style.border = borderNo;
            emailOk = false;
            cambiaStatoRegistrami();
        }

    }

    function cambiaStatoRegistrami() {
        if (usernameOk && passwordOk && nomeOk && emailOk) {
            document.getElementById('registrami').disabled = false;
            document.getElementById('registramimessaggio').innerHTML = '';
        } else {
            document.getElementById('registrami').disabled = true;
            document.getElementById('registramimessaggio').innerHTML = 'Verifica che tutti i campi siano in verde.';
        }
    }
</script>
<%@include file="footer.jsp"%>

