<html>
<head>
    <%@ include file="adminmenu.jsp" %>
    <title><fmt:message key="link.comments"/></title>
</head>
<body>
<table class="table table-hover">
    <tr class="table-info">
        <c:forEach items="${requestScope.masters}" var="master">
            <th><a href="${pageContext.request.contextPath}/app/admin/comments?masterId=${master.id}">${master.name}</a>
            </th>
        </c:forEach></tr>
</table>
<table class="table table-hover">
    <thead>
    <tr>
        <th><fmt:message key="var.beautyservice"/></th>
        <th><fmt:message key="var.date"/></th>
        <th><fmt:message key="var.time"/></th>
        <th><fmt:message key="var.username"/></th>
        <th><fmt:message key="var.mastername"/></th>
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
        <td>${a.masterName}</td>
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
                    <a  class="page-link"
                        href="?masterId=${pageContext.request.getParameter("masterId")}&page=${page - 1}">${page}</a>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</nav>
</body>
</html>
