<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="${sessionScope.lang}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
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
