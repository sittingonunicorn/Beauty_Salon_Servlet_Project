<!--suppress ALL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
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
            <button type="submit" class="btn btn-info"
                    onclick="location.href='${pageContext.request.contextPath}/app/logout'">
                <fmt:message key="button.logout"/>
            </button>
        </nav>
    </div>
    <div>
        <h2 class="h2">Beauty Salon</h2>
    </div>
</header>
<nav class="menu">
    <ul class="hr">
        <c:choose>
            <c:when test="${sessionScope.role eq 'ADMIN'}">
                <div class="dropdown" style="float: left">
                    <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message
                            key="menu.admin"/>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/app/admin/comments"
                        ><fmt:message key="link.comments"/></a>
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/app/admin/appointments"><fmt:message
                                key="link.master.appointments"/></a>
                    </div>
                </div>
                <div class="dropdown" style="margin-left:160px">
                    <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message
                            key="menu.user"/>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/app/user/servicetypes"
                        ><fmt:message key="link.create.new.appointment"/></a>
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/app/user/archive"><fmt:message
                                key="link.archive.appointments"/></a>
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/app/user/appointments"><fmt:message
                                key="link.user.appointments"/></a>
                    </div>
                </div>
            </c:when>
            <c:when test="${sessionScope.role eq 'MASTER'}">
                <div class="dropdown" style="float: left">
                    <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message
                            key="menu.master"/>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/app/master/comments"
                        ><fmt:message key="link.comments"/></a>
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/app/master/appointments"><fmt:message
                                key="link.master.appointments"/></a>
                    </div>
                </div>
                <div class="dropdown" style="margin-left:160px">
                    <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message
                            key="menu.user"/>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/app/user/servicetypes"
                        ><fmt:message key="link.create.new.appointment"/></a>
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/app/user/archive"><fmt:message
                                key="link.archive.appointments"/></a>
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/app/user/appointments"><fmt:message
                                key="link.user.appointments"/></a>
                    </div>
                </div>
            </c:when>
            <c:when test="${sessionScope.role eq 'USER'}">
                <div class="dropdown" >
                    <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message
                            key="menu.user"/>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/app/user/servicetypes"
                        ><fmt:message key="link.create.new.appointment"/></a>
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/app/user/archive"><fmt:message
                                key="link.archive.appointments"/></a>
                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/app/user/appointments"><fmt:message
                                key="link.user.appointments"/></a>
                    </div>
                </div>
            </c:when>
        </c:choose>
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>
