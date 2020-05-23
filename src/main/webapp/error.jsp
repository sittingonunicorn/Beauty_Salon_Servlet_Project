<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Error page</title>
</head>
<body>
<h2>Error page</h2>
<h4>
    <fmt:message key="chooseLocale"/>
</h4>
<ul>
    <li><a href="?sessionLocale=en"><fmt:message key="lang.en"/></a></li>
    <li><a href="?sessionLocale=ua"><fmt:message key="lang.ua"/></a></li>
</ul>
<a href="${pageContext.request.contextPath}/app/login"><fmt:message key="button.login"/></a><br>
<a href="${pageContext.request.contextPath}/app/registration"><fmt:message key="button.registration"/></a><br>
<a href="${pageContext.request.contextPath}/app/logout"><fmt:message key="button.logout"/></a>
</body>
</html>
