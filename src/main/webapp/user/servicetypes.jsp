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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" th:href="@{/user/styles.css}">
    <title>Beauty Salon Choose Service type</title>
</head>
<body>
<header>
    <div class="container">
        <div class="box">
            <div><h2>Beauty Salon</h2></div>
        </div>
    </div>
</header>
<table>
    <thead>
    <tr>
        <th scope="col"><fmt:message key = "var.beautyservicesType"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.servicetypes}" var="s">
    <tr>
        <td>
    <a href="${pageContext.request.contextPath}/app/user/beautyservices/${s.id}">
        ${s.beautyservicesType}
        <input type="hidden" value="${s.id}" name="professionId">
    </a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>