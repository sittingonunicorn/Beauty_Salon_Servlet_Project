<head>
    <%@ include file="usermenu.jsp" %>
    <title>Choose master</title>
</head>
<body>
<table class="table table-hover" style="max-width: 25rem;">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="var.mastername"/></th>
        <th><fmt:message key="message.workinghours"/> </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.masters}" var="m">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/app/user/time?masterId=${m.id}">
                        ${m.name}
                    <input type="hidden" value="${m.id}" name="masterId">
                    <input id="csrfToken" name="csrfToken" type="hidden" value="${sessionScope.csrfToken}" />
                </a>
            </td>
            <td><fmt:message key="message.master.work.time">
                <fmt:param value="${m.timeBegin}"/>
                <fmt:param value="${m.timeEnd}"/>
            </fmt:message> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
