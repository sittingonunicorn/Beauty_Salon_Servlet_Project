<html>
<head>
    <%@ include file="../parts/menu.jsp" %>
    <title>Comments for Master</title>
</head>
<body>
<c:choose>
    <c:when test="${empty requestScope.archive}">
        <fmt:message key="no.comments"/>
    </c:when>
    <c:otherwise>
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="var.beautyservice"/></th>
                <th><fmt:message key="var.date"/></th>
                <th><fmt:message key="var.time"/></th>
                <th><fmt:message key="var.username"/></th>
                <th><fmt:message key="var.comment"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.archive}" var="a">
            <tr>
                <td>${a.beautyService}</td>
                <td>${a.date}</td>
                <td>${a.time}</td>
                <td>${a.userName}</td>
                <td>${a.comment}
                </td>
                </c:forEach>
            </tr>
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <c:if test="${requestScope.pageNumbers.size() != 0}">
                <ul class="pagination">
                    <c:forEach items="${requestScope.pageNumbers}" var="page">
                        <li class="page-item">
                            <a class="page-link" href="?page=${page - 1}">${page}</a>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </nav>
    </c:otherwise></c:choose>
</body>
</html>
