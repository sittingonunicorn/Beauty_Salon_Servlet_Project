<!--suppress ALL -->
<html>
<head>
    <%@ include file="/commonmenu.jsp" %>
    <title>Beauty Salon Main page</title></head>
<body>

<div class="alert">
    <c:if test="${requestScope.unauthorized != null}">
        <div class="alert alert-danger" role="alert">${requestScope.unauthorized}</div>
    </c:if>
</div>
<h5><fmt:message key="message.task"/> </h5>
</body>
</html>
