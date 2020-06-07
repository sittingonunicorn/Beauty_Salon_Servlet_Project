<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<html>
<head>
    <title>Main</title>
</head>
<body>
<h3>this is <c:out value="${uri}"/></h3>
<h2>
    <fmt:message key="welcome" />
</h2>

<h2>
    <fmt:message key="chooseLocale" />
</h2>
<ul>
    <li><a href="?cookieLocale=en"><fmt:message key="lang.en" /></a></li>
    <li><a href="?cookieLocale=ua"><fmt:message key="lang.ua" /></a></li>
</ul>
</body>
</html>
