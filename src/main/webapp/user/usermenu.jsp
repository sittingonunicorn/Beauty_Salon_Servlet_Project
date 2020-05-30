<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style type="text/css">
        <%@include file="/static/styles.css" %>
    </style>
</head>
<body>
<header>
    <div class="nav">
        <nav>
            <button type="submit" class="btn btn-outline-info" onclick="location.href='?sessionLocale=en'">
                <fmt:message key="lang.en"/>
            </button>
            <button type="submit" class="btn btn-outline-info" onclick="location.href='?sessionLocale=ua'">
                <fmt:message key="lang.ua"/>
            </button>
            <button type="submit" class="btn btn-info" onclick="location.href='${pageContext.request.contextPath}/app/logout'">
                <fmt:message key="button.logout"/>
            </button>
        </nav>
        <p>    ${sessionScope.user.email}</p>
    </div>
    <div>
        <h2 class="h2">Beauty Salon</h2>
    </div>
</header>
<nav class="menu">
    <ul class="hr">
        <li><a href="${pageContext.request.contextPath}/app/user/servicetypes"><fmt:message
                key="link.create.new.appointment"/></a></li>
        <li><a href="${pageContext.request.contextPath}/app/user/archive"><fmt:message
                key="link.archive.appointments"/></a></li>
    </ul>

</nav>
<nav class="navbar fixed-bottom navbar-light bg-info">
    <div class="navbar-text" style="color: white">
        <fmt:message key="footer.name"/>
    </div>
    <div class="navbar-text" style="color: white">
        <fmt:message key="footer.rights.reserved"/>
    </div>
</nav>
</body>
</html>
