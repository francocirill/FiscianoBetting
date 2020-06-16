<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><%@taglib prefix="c"
                                         uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Azioni Partita"/>
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
            <c:choose>
                <c:when test="${operazione=='aggiungi'}">  <!-- Aggiungi partita-->
                    <h1>Aggiungi partita</h1>
                    <h5>Riempi tutti i campi</h5>
                    <h5>${notifica}</h5>
                    <form name="partita" action="AdminPartita" method="get" onsubmit="return checkSquadre()">
                        <label>Data AAAA-MM-GG</label><br>
                        <input type="text" name="data" ><br>
                        <label>Ora HH:MM:SS</label><br>
                        <input type="text" name="ora" ><br>
                        <label for="camps">Campionato</label><br>

                        <select name="camps" id="camps" onchange="loadSquadre(this)">
                            <option value=""></option>
                            <c:forEach items="${campionati}" var="campionato">
                                <option value="${campionato.id}">${campionato.nome}</option>
                            </c:forEach>
                        </select><br>
                        <label>Squadra1 </label><br>
                        <select name="squadra1" id="squadra1" >
                        </select><br>
                        <label>Squadra2 </label><br>
                        <select name="squadra2" id="squadra2" >
                        </select><br>
                        <label>Quota1 </label><br>
                        <input type="number" name="quota1" min="1.01" step="0.01"><br>
                        <label>Quota2 </label><br>
                        <input type="number" name="quota2" min="1.01" step="0.01"><br>
                        <label>Quota3 </label><br>
                        <input type="number" name="quota3" min="1.01" step="0.01"><br>
                        <input id="operazione" name="operazione" type="submit" value="aggiungi" >
                    </form>
                </c:when>
                <c:otherwise >
                    <h1>Modifica/Elimina partita</h1>
                    <h5>${notifica}</h5>
                    <c:if test="${partita!=null}">
                        <form name="partita" action="AdminPartita" method="get" >
                            <input type="hidden" name="id" value="${partita.id}">
                            <label>Data AAAA-MM-GG</label><br>
                            <input type="text" name="data" value="${partita.data}"><br>
                            <label>Ora HH:MM:SS</label><br>
                            <input type="text" name="ora" value="${partita.ora}"><br>
                            <label>Squadra1 ${partita.idsquadra1}</label><br>
                            <label>Squadra2 ${partita.idsquadra2}</label><br>
                            <label>Quota1 </label><br>
                            <input type="number" name="quota1" min="1.01" step="0.01" value="${partita.quota1}"><br>
                            <label>Quota2 </label><br>
                            <input type="number" name="quota2" min="1.01" step="0.01" value="${partita.quota2}"><br>
                            <label>Quota3 </label><br>
                            <input type="number" name="quota3" min="1.01" step="0.01" value="${partita.quota3}"><br>
                            <input  name="operazione" type="submit" value="modifica" >
                            <input  name="operazione" type="submit" value="elimina" >
                        </form>
                    </c:if>
                </c:otherwise>

            </c:choose>

        </section>
    </div>
</div>
<script>
    function loadSquadre(sel) {
        var xhttp = new XMLHttpRequest();
        //xhttp.responseType = 'json';
        var campionato=sel.options[sel.selectedIndex].value;
        console.log(campionato);
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {

                var myArr = JSON.parse(this.responseText);
                var txt="'";
                myArr.forEach(function (value) {
                    txt+="<option value='"+value+"'>"+value+"</option>";

                })
                txt+="'";
                console.log("txt "+txt);
                document.getElementById("squadra1").innerHTML =txt;
                document.getElementById("squadra2").innerHTML =txt;
            }
        };
        xhttp.open("GET", "SquadraAjax?campionato="+campionato, true);
        xhttp.send();
    };

    function checkSquadre() {

            var x = document.forms["partita"]["squadra1"].value;
            var y = document.forms["partita"]["squadra2"].value;
            console.log(x,y);
            if (x == y) {
                alert("Scegliere squadre diverse");
                return false;
            }
    };
</script>
<%@include file="footer.jsp"%>
