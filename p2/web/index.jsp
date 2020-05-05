<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Franco
  Date: 13/04/2020
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="WEB-INF/results/header.jsp">
  <jsp:param name="pageTitle" value="HOME"/>
</jsp:include>
<!-- The flexible grid (content) -->
<div class="row">
  <div class="side">
    <h2>Campionati</h2>
    <c:forEach items="${campionati}" var="campionato">
      <p>
          ${campionato.nome}
      </p>
    </c:forEach>
  </div>
  <div class="main">
    <h2>Eventi </h2>
    <c:forEach items="${partite}" var="partita">
      <form action="metti nel carrello" method="post">
        <input type="submit" name="Quota1" value="Quota1">
        <input type="submit" name="Quota2" value="Quota2">
        <input type="submit" name="Quota3" value="Quota3">
        <input type="hidden" value="${partita.id}">
    </c:forEach>

  </div>
</div>
<jsp:include page="WEB-INF/results/footer.jsp"/>
