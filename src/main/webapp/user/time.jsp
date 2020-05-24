<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="${sessionScope.lang}"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
          integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
          crossorigin="anonymous">
<%--    <style type="text/css">--%>
<%--        <%@ include file="/WEB-INF/user/styles.css" %>--%>
<%--    </style>--%>
    <title>Choose time</title>
    <link href="WEB-INF/static/styles.css" rel=«stylesheet«>
</head>
<body>
<p><fmt:message key="message.chosen.master">
    <fmt:param value="${requestScope.masterName}"/>
</fmt:message> </p>

<jsp:useBean id="dateTime" scope="request" type="java.util.Map"/>
<c:forEach items="${dateTime}" var="entry">
    <form action="${pageContext.request.contextPath}/app/user/save" role="form">
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