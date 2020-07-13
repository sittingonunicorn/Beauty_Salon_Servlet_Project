<!--suppress ALL -->
<html>
<head>
    <%@ include file="../parts/menu.jsp" %>
    <title>Beauty Salon Leave the comment</title>
</head>
<body>
<table class="table table-hover">
    <jsp:useBean id="appointment" scope="request" type="net.ukr.lina_chen.model.dto.ArchiveAppointmentDTO"/>
    <thead>
    <tr>
        <th><fmt:message key="var.beautyservice"/></th>
        <th><fmt:message key="var.date"/></th>
        <th><fmt:message key="var.time"/></th>
        <th><fmt:message key="var.username"/></th>
        <th><fmt:message key="var.mastername"/></th>
        <th><fmt:message key="var.provided"/></th>
        <th><fmt:message key="var.comment"/></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${appointment.beautyService}</td>
        <td>${appointment.date}</td>
        <td>${appointment.time}</td>
        <td>${appointment.userName}</td>
        <td>${appointment.masterName}</td>
        <td><fmt:message key="bool.${appointment.provided}"/></td>
        <td>
            <form action="${pageContext.request.contextPath}/app/user/comment" role="form"
                  method="post">
                <input id="csrfToken" name="csrfToken" type="hidden" value="${sessionScope.csrfToken}" />
                <div class="form-group">
                <textarea class="form-control" id="newComment"
                          name="newComment" placeholder="<fmt:message key="var.comment"/>"></textarea>
                    <input type="hidden" value="${appointment.id}" name="appointmentId">
                </div>
                <button type="submit" class="btn btn-default"><fmt:message key="var.comment"/></button>
            </form>
    </tr>
    </td>
    </tbody>
</table>
</body>
</html>
