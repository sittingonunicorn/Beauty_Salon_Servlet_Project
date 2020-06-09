<html>
<head>
    <%@ include file="/commonmenu.jsp" %>
    <title>Main page</title></head>
<body>

<h2>
    <fmt:message key="welcome"/>
</h2>
<div class="alert">
    <%--@elvariable id="unauthorized" type="java.lang.String"--%>
    <c:if test="${unauthorized != null}">
        <div class="alert alert-danger" role="alert">${unauthorized}</div>
    </c:if>
</div>
</body>
</html>
