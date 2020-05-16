<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="${sessionScope.lang}"/>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
          integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
          crossorigin="anonymous">
    <style type="text/css">
        <%@ include file="/WEB-INF/user/styles.css" %>
    </style>
    <title>Choose time</title>
</head>
<body>

<%--<c:forEach var="entry" items="${myMap}">--%>
<%--    Key: <c:out value="${entry.key}"/>--%>
<%--    Value: <c:out value="${entry.value}"/>--%>
<%--</c:forEach>--%>

<jsp:useBean id="dateTime" scope="request" type="java.util.Map"/>
<c:forEach items="${dateTime}" var="entry">
    <form action="${pageContext.request.contextPath}/app/user/saveappointment" role="form">
        <div class="panel-body">
            <div class="treeHTML">
                <div><h6>${entry.key}</h6>
                    <details>
                        <summary></summary>
<%--                        <c:forEach items="${entry.value}" var="seance">--%>
<%--                            <jsp:useBean id="time" scope="request" type="java.util.List"/>--%>
<%--                            <c:if test="${time.contains(seance)}">--%>
<%--                                <label class="radio-label">--%>
<%--                                    <input class="btn btn-outline-info" type="submit" pattern="HH:mm"--%>
<%--                                           value="${seance}" name="appointmentTime"/>--%>
<%--                                </label></c:if>--%>
<%--                            <c:if test="${!time.contains(seance)}">--%>
<%--                                <label class="radio-label">--%>
<%--                                    <input class="btn btn-outline-info" type="submit" pattern="HH:mm"--%>
<%--                                           value="${seance}" name="appointmentTime" disabled/>--%>
<%--                                </label></c:if>--%>
<%--                        </c:forEach>--%>
                        <jsp:useBean id="time" scope="request" type="java.util.List"/>
                        <c:forEach items="${time}" var="seance">
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


<%--        </div>--%>
<%--            </div>--%>
<%--            <input type="hidden" value="${day}" name="day"/>--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</c:forEach>--%>
<%--<jsp:useBean id="days" scope="request" type="java.util.List"/>--%>
<%--<c:forEach items="${days}" var="day">--%>
<%--    <form action="${pageContext.request.contextPath}/app/user/saveappointment" role="form">--%>
<%--        <h6>${day}</h6>--%>
<%--    </form>--%>
<%--</c:forEach>--%>
<%--<jsp:useBean id="time" scope="request" type="java.util.List"/>--%>
<%--<c:forEach items="${time}" var="seance">--%>
<%--    <h6>${seance}</h6>--%>
<%--</c:forEach>--%>

</body>
</html>


<%--<jsp:useBean id="days" scope="request" type="java.util.List"/>--%>
<%--<c:forEach items="${days}" var="day">--%>
<%--    <form action="${pageContext.request.contextPath}/app/user/saveappointment" role="form">--%>
<%--        <ul>--%>
<%--            <li>--%>
<%--                <div>--%>
<%--                    <div>--%>
<%--                        <div><h4>${day}</h4></div>--%>
<%--                    </div>--%>
<%--                    <div>--%>
<%--                        <jsp:useBean id="time" scope="request" type="java.util.List"/>--%>
<%--                        <c:forEach var="seance" items="${time}">--%>
<%--                            <label>--%>
<%--                                <div>--%>
<%--                                    <input class="radio-input" type="submit"--%>
<%--                                           value=${time} name="timeOrder"/>${time}--%>
<%--                                </div>--%>
<%--                            </label>--%>
<%--                        </c:forEach>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--        <input type="hidden" value="${day}" name="dateOrder"/>--%>
<%--    </form>--%>
<%--    <hr align="center" width="100%" style="border-color: lightgray"/>--%>
<%--</c:forEach>--%>
<%--<jsp:useBean id="days" scope="request" type="java.util.List"/>--%>
<%--<c:forEach items="${days}" var="date">--%>
<%--    <form action="${pageContext.request.contextPath}/app/user/saveappointment" role="form">--%>
<%--        <ul class="media-list">--%>
<%--            <li class="media">--%>
<%--                <div class="media-body">--%>
<%--                    <div class="medi-heading">--%>
<%--                        <div class="autor"><h4>${date}</h4></div>--%>
<%--                    </div>--%>
<%--                    <div class="media-text text-justify">--%>
<%--                        <jsp:useBean id="time" scope="request" type="java.util.List"/>--%>
<%--                        <c:forEach var="time" items="${time}">--%>
<%--                            <c:set var="count" value="0"/>--%>
<%--                            <jsp:useBean id="busySchedule" scope="request" type="java.util.List"/>--%>
<%--                            <c:forEach var="busy" items="${busySchedule}">--%>
<%--                                <jsp:useBean id="appointment" scope="request" type="net.ukr.lina_chen.model.entity.Appointment"/>--%>
<%--                                <c:if test="${time eq busy.time && date eq busy.date--%>
<%--                                    && appointment.master.id eq busy.master.id }">--%>
<%--                                    <label class="radio-label" style="text-decoration: line-through;">--%>
<%--                                        <div class="radio1">--%>
<%--                                            <input class="radio-input" type="submit"--%>
<%--                                                   value=${time} name="timeOrder" disabled/>${time}--%>
<%--                                        </div>--%>
<%--                                    </label>--%>
<%--                                    <c:set var="count" value="1"/>--%>
<%--                                </c:if>--%>
<%--                            </c:forEach>--%>
<%--                            <c:if test="${count <1}">--%>
<%--                                <label class="radio-label">--%>
<%--                                    <div class="radio1">--%>
<%--                                        <input class="radio-input" type="submit"--%>
<%--                                               value=${time} name="timeOrder"/>${time}--%>
<%--                                    </div>--%>
<%--                                </label>--%>
<%--                            </c:if>--%>
<%--                        </c:forEach>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--        <input type="hidden" value="${date}" name="dateOrder"/>--%>
<%--    </form>--%>
<%--</c:forEach>--%>