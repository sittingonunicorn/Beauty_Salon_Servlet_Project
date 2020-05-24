<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="${sessionScope.lang}"/>
<html>
<head>
    <title>Appointments for Master</title>
</head>
<body>
<p><fmt:message key="message.master.schedule">
    <fmt:param value="${requestScope.master.name}"/>
</fmt:message></p>

<table>
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
                <div class="row-fluid practice" style="width: 99%; height: 100%;">
                    <button type="submit" name="appointmentId" value="${a.id}">
                        <fmt:message key="message.provided"/>
                    </button>
                </div>
            </form>
        </td>

        </c:forEach>
    </tbody>
</table>

</body>
</html>
