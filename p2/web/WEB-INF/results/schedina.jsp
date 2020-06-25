<%@ page import="model.Scommessa" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Franco
  Date: 13/04/2020
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Schedina"/>
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
        <h2>Schedina </h2>
        <h3>${notifica}</h3>


        <c:if test="${schedina.size() gt 0}">
        <table id="partite">
            <tr>
                <th>Squadra1</th>
                <th>Squadra2</th>
                <th>Data</th>
                <th>Ora</th>
                <th>Esito</th>
                <th>Quota</th>
            </tr>
            <c:set var="totquote" value="1" scope="page"></c:set>
            <c:forEach items="${schedina}" var="scommessa">
                <tr>
                    <td>${scommessa.p.idsquadra1}</td>
                    <td>${scommessa.p.idsquadra2}</td>
                    <td>${scommessa.p.data}</td>
                    <td>${scommessa.p.ora}</td>
                    <c:choose>
                        <c:when test="${scommessa.esito=='1'}">
                            <td>1</td>
                            <td id="${scommessa.p.id}_1" >${scommessa.p.quota1}</td>
                            <c:set var="totquote" value="${totquote*scommessa.p.quota1}"></c:set>
                        </c:when>
                        <c:when test="${scommessa.esito=='2'}">
                            <td>X</td>
                            <td id="${scommessa.p.id}_1" >${scommessa.p.quota2}</td>
                            <c:set var="totquote" value="${totquote*scommessa.p.quota2}"></c:set>
                        </c:when>
                        <c:when test="${scommessa.esito=='3'}">
                            <td>2</td>
                            <td id="${scommessa.p.id}_1" >${scommessa.p.quota3}</td>
                            <c:set var="totquote" value="${totquote*scommessa.p.quota3}"></c:set>
                        </c:when>

                    </c:choose>
                    <td><a href="EliminaEvento?id=${scommessa.p.id}">Elimina</a></td>
                </tr>
            </c:forEach>
        </table>
        <form method="get" action="ScommettiSchedina">
        Importo <input id="importo" name="importo" type="number" value="2" min="2" onchange="calcolaPotenziale()">
            <input type="submit" value="Scommetti">
        </form>

        <span id="vincita"></span>
        </c:if>
        <c:if test="${schedina.size()==0}">
            <span>Schedina vuota</span>
        </c:if>
    </div>
</div>
<script>
    function calcolaPotenziale(input) {
        var importo=document.getElementById("importo").value;
        var quote=${totquote};
        if(importo>=2)
        {

            var totale=Math.round(importo*quote*100)/100;
            document.getElementById("vincita").innerHTML="Vincita potenziale: "+totale+" euro";
        }
        else{
            document.getElementById("vincita").innerHTML="Valore minimo 2 euro";
        }
    };
    document.getElementsByTagName("body")[0].onload = calcolaPotenziale();
</script>

<jsp:include page="footer.jsp"/>