<head>
    <%@ include file="../parts/menu.jsp" %>
    <title>Beauty Salon Choose Service type</title>
</head>
<body>
<div class="alert">
    <c:if test="${param.error}">
        <div class="alert alert-danger" role="alert">
            <fmt:message key="message.error.appointment.provided"/>
        </div>
    </c:if>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="var.beautyservicesType"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.servicetypes}" var="s">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/app/user/servicetypes?professionId=${s.id}">
                        ${s.beautyservicesType}
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${requestScope.beautyservices!=null}">
    <table class="table table-hover">
        <tbody>
        <c:forEach items="${requestScope.beautyservices}" var="b">
            <tr>
                <td></td>
                <td>
                    <a href="${pageContext.request.contextPath}/app/user/masters?professionId=${requestScope.professionId}&beautyserviceId=${b.id}">
                            ${b.name}
                        <input id="csrfToken" name="csrfToken" type="hidden" value="${sessionScope.csrfToken}"/>
                    </a>
                </td>
                <td>${b.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
