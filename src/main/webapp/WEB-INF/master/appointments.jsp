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
<p><fmt:message key="message.master.schedule">
    <jsp:useBean id="master" scope="request" type="net.ukr.lina_chen.model.entity.Master"/>
    <fmt:param value="${master.user.name}"/>
</fmt:message></p>
<table>
    <thead>
    <tr>
        <th><fmt:message key="var.beautyservice"/></th>
        <th><fmt:message key="var.date"/></th>
        <th><fmt:message key="var.time"/></th>
        <th><fmt:message key="var.username"/></th>
        <th><fmt:message key="var.provided"/></th>
    </tr>
    </thead>
<tbody>
<jsp:useBean id="appointments" scope="request" type="java.util.List"/>
<c:forEach items="${appointments}" var="a">
    <tr>
<td>${a.beautyService.name}</td>
<td>${a.date}</td>
<td>${a.time}</td>
<td>${a.user.name}</td>
<td>${a.provided}</td>
    </tr>
</c:forEach>
</tbody>
</table>
</body>
</html>
