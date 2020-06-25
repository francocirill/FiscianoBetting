<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><%@taglib prefix="c"
                                         uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Azioni Squadra"/>
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
            <h1>Modifica Squadra</h1>
            <h3>${squadra.id}</h3>
            <h5>${notifica}</h5>
            <form name="squadra" action="AdminSquadra?id=${squadra.id}" method="get" >
                <input type="hidden" name="id" value="${squadra.id}">
                <label>Punteggio</label><br>
                <input type="number" name="punteggio" min="0" value="${squadra.punteggio}"><br>
                <label>Gol Fatti</label><br>
                <input type="number" name="golfatti" min="0" value="${squadra.golfatti}"><br>
                <label>Gol Subiti</label><br>
                <input type="number" name="golsubiti" min="0" value="${squadra.golsubiti}"><br>
                <input id="aggiorna" name="aggiorna" type="submit" value="Aggiorna" >
            </form>
                <%@include file="footer.jsp"%>