<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="${sessionScope.lang}"/>
<html>
<head>
    <title>Appointment's saved</title>
</head>
<body>
<p><fmt:message key="message.appointment.created"/></p>
<jsp:useBean id="appointment" scope="request" type="net.ukr.lina_chen.model.dto.AppointmentDTO"/>
<p><fmt:message key="message.master">
    <fmt:param value="${appointment.masterName}"/>
</fmt:message></p>
<p><fmt:message key="message.beautyservice">
    <fmt:param value="${appointment.beautyService}"/>
</fmt:message></p>
<p><fmt:message key="message.date">
    <fmt:param value="${appointment.date}"/>
</fmt:message></p>
<p><fmt:message key="message.time">
    <fmt:param value="${appointment.time}"/>
</fmt:message></p>

</body>
</html>
