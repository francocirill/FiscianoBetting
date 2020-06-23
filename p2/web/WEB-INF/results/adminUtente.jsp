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
    <jsp:param name="pageTitle" value="Admin Utenti"/>
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
        <h2>Utenti </h2>
        <table id="partite">
            <tr>
                <th>Username</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Admin</th>
            </tr>
            <c:forEach items="${utenti}" var="u">
                <tr>
                    <td>${u.username}</td>
                    <td>${u.nome}</td>
                    <td>${u.email}</td>

                    <c:choose>
                        <c:when test="${u.admin}">
                            <td>si</td>
                        </c:when>
                        <c:otherwise>
                            <td>no</td>
                        </c:otherwise>
                    </c:choose>
                        <td>
                            <a href="EliminaUtente?id=${u.id}">Elimina</a>
                        </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="footer.jsp"/>
