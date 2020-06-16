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
    <jsp:param name="pageTitle" value="Classifica"/>
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
        <h2>Classifica ${nome}</h2>
        <table id="partite">
            <tr>
                <th>Posizione</th>
                <th>Nome</th>
                <th>Punteggio</th>
                <th>Gol fatti</th>
                <th>Gol subiti</th>
            </tr>
            <c:set var="pos" value="${1}"/>
            <c:forEach items="${classifica}" var="squadra">

                <tr>
                    <td>${pos}</td>
                    <td>${squadra.id}</td>
                    <td>${squadra.punteggio}</td>
                    <td>${squadra.golfatti}</td>
                    <td>${squadra.golsubiti}</td>
                    <c:if test="${utente.admin}">
                        <td>
                            <a href="AdminSquadraForm?id=${squadra.id}">Modifica</a>
                        </td>
                    </c:if>

                </tr>
                <c:set var="pos" value="${pos+1}"/>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="footer.jsp"/>
