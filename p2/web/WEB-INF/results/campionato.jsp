<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Franco
  Date: 13/04/2020
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${campionato.nome}"/>
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
        <h1>${campionato.nome}</h1>
        <p>${campionato.descrizione}</p>
        <h2>Eventi </h2>
        <%/*
        <c:forEach items="${partite}" var="partita">
        <form action="carrello" method="post">
                ${partita.idsquadra1},${partita.idsquadra2},${partita.data},${partita.ora},
                ${partita.quota1}
            <input type="submit" name="Quota1" value="Quota1">
                ${partita.quota2}
            <input type="submit" name="Quota2" value="Quota2">
                ${partita.quota3}
            <input type="submit" name="Quota3" value="Quota3">
            <input type="hidden" value="${partita.id}">
            </c:forEach>*/%>
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
                    <td>${partita.quota1}</td>
                    <td>${partita.quota2}</td>
                    <td>${partita.quota3}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="footer.jsp"/>
