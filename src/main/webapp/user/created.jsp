<!--suppress ALL -->
<html>
<head>
    <%@ include file="../parts/menu.jsp" %>
    <title>Beauty Salon Appointment's saved</title>
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
<p><fmt:message key="message.waiting.for.you"/></p>
</body>
</html>
