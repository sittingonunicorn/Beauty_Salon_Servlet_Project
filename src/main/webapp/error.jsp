<!--suppress ALL -->
<html>
<head>
    <%@ include file="/parts/menu.jsp" %>
    <title>Beauty Salon Error page</title>
</head>
<body>
<c:choose>
<c:when test="${param.already_logged}">
<div class="alert">
    <div class="alert alert-danger" role="alert"><fmt:message key="message.error.already.logged.in"/> </div>
    </c:when>
    <c:otherwise><h5><fmt:message key="message.error"/></h5>
    </c:otherwise>
    </c:choose>
</body>
</html>