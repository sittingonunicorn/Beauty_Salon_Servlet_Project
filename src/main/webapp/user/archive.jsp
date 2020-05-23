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
            <td>${a.beautyService}</td>
            <td>${a.date}</td>
            <td>${a.time}</td>
            <td>${a.userName}</td>
            <td>${a.masterName}</td>
            <td><fmt:message key="bool.${a.provided}"/></td>
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