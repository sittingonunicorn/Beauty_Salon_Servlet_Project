<!--suppress ALL -->
<html>
<head>
    <%@ include file="../parts/menu.jsp" %>
    <title>Beauty Salon Appointments for User</title>
</head>
<body>
<div class="content-inside">
    <c:choose>
        <c:when test="${empty requestScope.appointments}">
            <fmt:message key="no.appointments"/>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="message.user.upcoming.appointments"/></p>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th><fmt:message key="var.beautyservice"/></th>
                    <th><fmt:message key="var.date"/></th>
                    <th><fmt:message key="var.time"/></th>
                    <th><fmt:message key="var.mastername"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.appointments}" var="a">
                <tr>
                    <td>${a.beautyService}</td>
                    <td>${a.date}</td>
                    <td>${a.time}</td>
                    <td>${a.masterName}</td>
                    </c:forEach>
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

</div>
</body>
</html>

