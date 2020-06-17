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
    <jsp:param name="pageTitle" value="${ricerca}"/>
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
        <form method="get" action="Ricerca">
            <input type="text" name="testo" placeholder="ricerca" required>
            <input type="submit" value="Ricerca">
        </form>
        <c:if test="${partite!=null}">
        <h1>Risultati per ${ricerca}</h1>

        <table id="partite">
            <tr>
                <th>Squadra1</th>
                <th>Squadra2</th>
                <th>Data</th>
                <th>Ora</th>
                <th>1</th>
                <th>X</th>
                <th>2</th>
            </tr>
            <c:forEach items="${partite}" var="partita">
                <tr>
                    <td>${partita.idsquadra1}</td>
                    <td>${partita.idsquadra2}</td>
                    <td>${partita.data}</td>
                    <td>${partita.ora}</td>
                    <td id="${partita.id}_1" onclick="schedina(this,'${partita.id}','1')">${partita.quota1}</td>
                    <td id="${partita.id}_2" onclick="schedina(this,'${partita.id}','2')">${partita.quota2}</td>
                    <td id="${partita.id}_3" onclick="schedina(this,'${partita.id}','3')">${partita.quota3}</td>
                    <c:if test="${utente.admin}">
                        <td>
                            <a href="AdminPartitaForm?operazione=modifica&id=${partita.id}">Modifica</a>
                        </td>
                        <td>
                            <a href="AdminPartitaForm?operazione=elimina&id=${partita.id}">Elimina</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        </c:if>
    </div>
</div>
<script>

    function colora() {
        //se è presente nella  schedina della sessione imposta arancione
        //rimuove tutti i colori
        var arr=document.getElementsByTagName("td");
        for (i = 0; i < arr.length; i++) {
            arr[i].removeAttribute("style");
        }


        //scorre l'array e colora le scommesse della sessione
        <%
          List<Scommessa> sched = (List<Scommessa>) session.getAttribute("schedina");
        %>
        <% if(sched!=null){
            for(Scommessa scommessa : sched){%>
        document.getElementById(<%=scommessa.getP().getId()%>+"_"+<%=scommessa.getEsito()%>).style["background-color"]='orange';
        <%}%><%}%>


    };
    function schedina(td,id,esito) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                //aggiorna colori
                if(esito!='1')
                    document.getElementById(id+"_"+"1").removeAttribute("style");
                if(esito!='2')
                    document.getElementById(id+"_"+"2").removeAttribute("style");
                if(esito!='3')
                    document.getElementById(id+"_"+"3").removeAttribute("style");
                if(td.style["background-color"]=='orange')
                {
                    td.removeAttribute("style");
                }
                else
                {
                    td.style["background-color"]='orange';
                }
            }
        };
        xhttp.open("GET", "SchedinaAjax?id="+id+"&esito="+esito, true); //salva scommessa nella sessione
        xhttp.send();


    };
    //aggiorna colori quando carica la pagina
    document.getElementsByTagName("body")[0].onload = colora();

</script>
<jsp:include page="footer.jsp"/>
