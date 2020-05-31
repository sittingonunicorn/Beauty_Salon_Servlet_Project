<head>
    <%@ include file="usermenu.jsp" %>
    <title>Beauty Salon Choose Service type</title>
</head>
<body>
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
    <table  class="table table-hover">
        <tbody>
        <c:forEach items="${requestScope.beautyservices}" var="b">
            <tr>
                <td></td>
                <td>
                    <a href="${pageContext.request.contextPath}/app/user/masters/${requestScope.professionId}/${b.id}">
                            ${b.name}
                        <input type="hidden" value="${b.id}" name="beautyserviceId">
                        <input id="csrfToken" name="csrfToken" type="hidden" value="${sessionScope.csrfToken}" />
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
