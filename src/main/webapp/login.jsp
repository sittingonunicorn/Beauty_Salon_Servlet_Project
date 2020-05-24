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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>

<h3><fmt:message key="authentification"/></h3><br/>

<form method="get" action="${pageContext.request.contextPath}/app/login">
    <div class="alert">
        <c:if test="${param.logout}">
            <div class="alert alert-danger" role="alert"><fmt:message key="message.logged.out" /></div>
        </c:if>
    </div>
    <input type="text" name="email" placeholder="Email"><br/>
    <input type="password" name="password" placeholder=<fmt:message key="placeholder.password"/>><br/><br/>
    <button class="button" type="submit" value="Login"><fmt:message key="button.login"/></button>

</form>
<br/>
<a href="${pageContext.request.contextPath}/app/registration"><fmt:message key="button.registration"/></a><br>

</body>
</html>