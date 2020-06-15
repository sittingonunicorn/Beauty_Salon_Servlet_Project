<html>
<head>
    <%@ include file="/commonmenu.jsp" %>
    <title>Main page</title></head>
<body>

<h2>
    <fmt:message key="welcome"/>
</h2>
<div class="alert">
    <c:if test="${requestScope.unauthorized != null}">
        <div class="alert alert-danger" role="alert">${requestScope.unauthorized}</div>
    </c:if>
</div>
<p><fmt:message key="message.task"/> </p>
</body>
</html>
