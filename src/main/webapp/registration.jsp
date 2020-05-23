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
    <title>Registration</title>
</head>
<body>
<h2><fmt:message key="registration"/></h2>
<div class="alert">
    <c:if test="${user_exists != null}">
        <div class="alert alert-danger" role="alert">User already exists</div>
    </c:if>
</div>
<form method="post" action="${pageContext.request.contextPath}/app/registration">
    <div class="form-group">
        <%--@declare id="email"--%>
        <label for="email">
            <input required type="text" class="form-control" name="email" placeholder="Email">
        </label>
    </div>
    <div class="form-group">
        <label for="password">
            <input required type="password" class="form-control" id="password"
                   placeholder=<fmt:message key="placeholder.password"/> name="password"></label>
    </div>
    <div class="form-group">
        <label for="name">
            <input type="text" class="form-control" id="name" placeholder=<fmt:message key="placeholder.name.en"/>
                   name="name"></label>
    </div>
    <div class="form-group">
        <label for="nameUkr">
            <input type="text" class="form-control" id="nameUkr" placeholder=<fmt:message key="placeholder.name.ukr"/>
                   name="nameUkr"></label>
    </div>
    <button type="submit" class="btn btn-default"><fmt:message key="button.registration"/></button>
</form>
</body>
</html>