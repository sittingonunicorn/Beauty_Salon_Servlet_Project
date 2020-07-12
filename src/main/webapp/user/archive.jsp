<html>
<head>
    <%@ include file="../parts/menu.jsp" %>
    <title><fmt:message key="message.user.appointments"/></title>
</head>

    <c:if test="${param.error}"><div class="alert">
        <div class="alert alert-danger" role="alert">
            <fmt:message key="message.error.appointments"/>
        </div>
    </div>
    </c:if>

<c:choose>
    <c:when test="${empty requestScope.archiveForUser}">
        <fmt:message key="no.archive"/>
    </c:when>
    <c:otherwise>
        <p><fmt:message key="message.user.appointments"/></p>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="var.beautyservice"/></th>
                <th scope="col"><fmt:message key="var.date"/></th>
                <th scope="col"><fmt:message key="var.time"/></th>
                <th scope="col"><fmt:message key="var.username"/></th>
                <th scope="col"><fmt:message key="var.mastername"/></th>
                <th scope="col"><fmt:message key="var.provided"/></th>
                <th scope="col"><fmt:message key="var.comment"/></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${requestScope.archiveForUser}" var="a">
                <tr class="table table-hover">

                    <td scope="row"><span>${a.beautyService}</span></td>
                    <td><span>${a.date}</span></td>
                    <td><span>${a.time}</span></td>
                    <td><span>${a.userName}</span></td>
                    <td><span>${a.masterName}</span></td>
                    <td><span><fmt:message key="bool.${a.provided}"/></span></td>
                    <td><span>${a.comment}</span>
                        <form action="${pageContext.request.contextPath}/app/user/comment" role="form"
                              method="get">
                            <c:if test="${a.comment==null}">
                                <div>
                                    <button type="submit" class="btn btn-default" name="appointmentId" value="${a.id}">
                                        <fmt:message key="message.leave.comment"/></button>
                                </div>
                            </c:if></form>
                    </td>

                </tr>
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
    </c:otherwise>
</c:choose>
</body>
</html>