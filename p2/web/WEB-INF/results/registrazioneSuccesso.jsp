<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Registrazione avvenuta con successo."/>
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
            <h2>Registrazione avvenuta con successo.</h2>
        </section>
    </div>
</div>
<%@include file="footer.jsp"%>