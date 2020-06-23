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
    <jsp:param name="pageTitle" value="Schedine Giocate"/>
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
        <h2>Schedine giocate </h2>
        <c:forEach items="${schedinegiocate}" var="schedina">
            <div style="border: 1px solid black;padding: 10px;">
                <c:if test="${schedina.username!=null}">
                    Username: ${schedina.username}
                </c:if>
            <pre>Eventi:<br>${schedina.testo}<br>Importo: ${schedina.importo} euro<br>Vincita potenziale: ${schedina.vincita} euro<br>Data: ${schedina.data}<br>Ora: ${schedina.ora}</pre><br>
            </div>




        </c:forEach>
    </div>
</div>
<jsp:include page="footer.jsp"/>