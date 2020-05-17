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
<p><fmt:message key="message.user.appointments"/></p>
<jsp:useBean id="archiveForUser" scope="request" type="java.util.List"/>
<c:forEach items="${archiveForUser}" var="a">
    <table class="table table-hover">
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
            <td>${a.beautyService.name}</td>
            <td>${a.date}</td>
            <td>${a.time}</td>
            <td>${a.user.name}</td>
            <td>${a.master.user.name}</td>
            <td>${a.provided}</td>
            <td>${a.comment}
                <form action="${pageContext.request.contextPath}/app/user/comment" role="form"
                method="get">
                    <c:if test="${a.comment==null}">
                        <div>
                            <button type="submit" name="appointmentId" value="${a.id}">
                                <fmt:message key="message.leave.comment"/></button>
                        </div>
                    </c:if></form>
            </td>
        </tr>
        </tbody>
    </table>
</c:forEach>
</body>
</html>
<%--<p th:utext="#{message.user.appointments}">Upcoming appointments</p>--%>

<%--<table class="table table-hover">--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th th:text="#{var.beautyservice}">Service</th>--%>
<%--        <th th:text="#{var.date}">Date</th>--%>
<%--        <th th:text="#{var.time}">Time</th>--%>
<%--        <th th:text="#{var.username}">User's name</th>--%>
<%--        <th th:text="#{var.provided}">Provided</th>--%>
<%--        <th th:text="#{var.comment}">Comment</th>--%>
<%--        <th></th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--    <tr th:if="${#lists.isEmpty(archiveAppointments)}">--%>
<%--        <td colspan="2"> No appointments available</td>--%>
<%--    </tr>--%>
<%--    <!--    /*@thymesVar id="servicetypes" type="List"*/-->--%>
<%--    <tr th:each="appointment : ${archiveAppointments}">--%>
<%--        <td>--%>
<%--            <span th:text="${appointment.getBeautyService().getName()}">Service</span>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <span th:text="${appointment.getDate()}">Date</span>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <span th:text="${appointment.getTime()}">Time</span>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <span th:text="${appointment.getUser().getName()}">User's name</span>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <span th:text="#{|bool.${appointment.isProvided()}|}">Provided</span>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <span th:text="${appointment.getComment()}">Time</span>--%>
<%--        </td>--%>
<%--        <td>--%>
<%--            <form th:action="@{/user/comment}"  th:if="${#strings.equals(appointment.getComment(), null)}">--%>
<%--                <div class="row-fluid practice" style="width: 99%; height: 100%;">--%>
<%--                    <button type="submit" name="appointmentId" th:value="${appointment.id}"--%>
<%--                            th:text="#{message.leave.comment}">--%>
<%--                        Leave a comment</button>--%>
<%--                </div>--%>
<%--            </form>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--    </tbody>--%>
<%--</table>--%>