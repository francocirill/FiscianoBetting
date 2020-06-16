<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><%@taglib prefix="c"
                                         uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Login utente"/>
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
            <h1>Login utente</h1>
            <form name="login" action="Login" method="post">
                <label>Username</label><br>
                <input type="text" name="username" ><br>
                <label>Password </label><br>
                <input type="password" name="password" ><br>
                <input id="login" type="submit" value="login" >
            </form>
        </section>
    </div>
</div>
<%@include file="footer.jsp"%>
