<!--suppress ALL -->
<html>
<head>
    <%@ include file="../parts/menu.jsp" %>
    <title>Beauty Salon Choose time</title>
</head>
<body>
<p><fmt:message key="message.chosen.master">
    <fmt:param value="${requestScope.master.name}"/>
</fmt:message></p>
<c:if test="${param.timeBusy}">
    <div class="alert alert-danger" role="alert">Time is busy</div>
</c:if>
<c:forEach items="${requestScope.dateTime}" var="entry">
    <form action="${pageContext.request.contextPath}/app/user/save" role="form">
        <input id="csrfToken" name="csrfToken" type="hidden" value="${sessionScope.csrfToken}" />
        <div class="panel-body">
            <div class="treeHTML">
                <div><h6>${entry.key}</h6>
                    <details>
                        <summary></summary>
                        <c:forEach items="${requestScope.workingHours}" var="seance">
                            <c:if test="${entry.value.contains(seance)}">
                                <label class="radio-label">
                                    <input class="btn btn-outline-info" type="submit" pattern="HH:mm"
                                           value="${seance}" name="appointmentTime"/>
                                </label></c:if>
                            <c:if test="${!entry.value.contains(seance)}">
                                <label class="radio-label">
                                    <input class="btn btn-outline-info" type="submit" pattern="HH:mm"
                                           value="${seance}" name="appointmentTime" disabled/>
                                </label></c:if>
                        </c:forEach>
                    </details>
                </div>
            </div>
            <input type="hidden" value="${entry.key}" name="appointmentDate"/>
        </div>
    </form>
</c:forEach>
</body>
</html>


