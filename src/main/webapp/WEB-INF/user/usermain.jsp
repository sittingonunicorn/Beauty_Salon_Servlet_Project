<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="${sessionScope.lang}"/>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>User Main</title>
</head>
<body>
<ul>
    <li><a href="?sessionLocale=en"><fmt:message key="lang.en"/></a></li>
    <li><a href="?sessionLocale=ua"><fmt:message key="lang.ua"/></a></li>
</ul>
<h1>Hello User!</h1>
<p>${sessionScope.user.name}</p>
<a href="${pageContext.request.contextPath}/app/logout"><fmt:message key="button.logout"/></a>
</body>
</html>