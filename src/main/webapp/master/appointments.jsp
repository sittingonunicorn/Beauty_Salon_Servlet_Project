<html>
<head>
    <%@ include file="masternenu.jsp"%>
    <title>Appointments for Master</title>
</head>
<body>
<p><fmt:message key="message.master.schedule">
    <fmt:param value="${requestScope.master.name}"/>
</fmt:message></p>

<table  class="table table-hover">
    <thead>
    <tr>
        <th><fmt:message key="var.beautyservice"/></th>
        <th><fmt:message key="var.date"/></th>
        <th><fmt:message key="var.time"/></th>
        <th><fmt:message key="var.username"/></th>
        <th><fmt:message key="var.provided"/></th>
        <th>Set Provided</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.appointments}" var="a">
    <tr>
        <td>${a.beautyService}</td>
        <td>${a.date}</td>
        <td>${a.time}</td>
        <td>${a.userName}</td>
        <td><fmt:message key="bool.${a.provided}"/></td>
        <td>
            <form method="post" action="${pageContext.request.contextPath}/app/master/provide">
                <input id="csrfToken" name="csrfToken" type="hidden" value="${sessionScope.csrfToken}" />
                <div class="row-fluid practice" style="width: 99%; height: 100%;">
                    <button type="submit" class="btn btn-default" name="appointmentId" value="${a.id}">
                        <fmt:message key="message.provided"/>
                    </button>
                </div>
            </form>
        </td>
        </c:forEach>
    </tbody>
</table>
<nav aria-label="Page navigation example">
    <c:if test="${requestScope.pageNumbers.size() != 0}">
        <ul class="pagination">
            <c:forEach items="${requestScope.pageNumbers}" var="page">
                <li class="page-item">
                    <a  class="page-link" href="?page=${page - 1}">${page}</a>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</nav>
</body>
</html>
