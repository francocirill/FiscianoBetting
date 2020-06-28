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
    <jsp:param name="pageTitle" value="Chi siamo"/>
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
        <h2>FiscianoBetting </h2>
        <p>
            Progetto sviluppato per il corso di TSW presso l'universita' degli studi di Salerno
            da : Cirillo Franco e Domenico D'Alessandro
        </p>

    </div>
</div>
<jsp:include page="footer.jsp"/>
