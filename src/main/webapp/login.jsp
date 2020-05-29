<html>
<head>
    <%@ include file="commonmenu.jsp" %>
    <title>Login Page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="panel panel-default" style="margin-top:45px">
            <div class="panel-heading">
                <h3 class="panel-title"><fmt:message key="authentification"/></h3>
            </div>
            <div class="panel-body">
                <div class="info">
                    <c:if test="${param.logout}">
                        <div class="alert alert-info" role="alert"><fmt:message key="message.logged.out"/></div>
                    </c:if>
                </div>
                <div class="alert">
                    <c:if test="${param.error}">
                        <div class="alert alert-danger" role="alert">Invalid E-mail or Password!</div>
                    </c:if>
                </div>
                <form method="get" action="${pageContext.request.contextPath}/app/login">
                    <div class="form-group">
                        <label>
                            <input type="text" name="email" class="form-control" placeholder="Email">
                        </label><br/>
                    </div>
                    <div class="form-group">
                        <label>
                            <input type="password" name="password" class="form-control"
                                   placeholder=<fmt:message key="placeholder.password"/>>
                        </label><br/>
                    </div>
                    <button type="submit" class="btn btn-outline-info"
                            value="Login"><fmt:message key="button.login"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<%--<h3><fmt:message key="authentification"/></h3><br/>--%>

<%--<form method="get" action="${pageContext.request.contextPath}/app/login">--%>
<%--    <div class="alert">--%>
<%--        <c:if test="${param.logout}">--%>
<%--            <div class="alert alert-danger" role="alert"><fmt:message key="message.logged.out" /></div>--%>
<%--        </c:if>--%>
<%--    </div>--%>
<%--    <input type="text" name="email" placeholder="Email"><br/>--%>
<%--    <input type="password" name="password" placeholder=<fmt:message key="placeholder.password"/>><br/><br/>--%>
<%--    <button class="button" type="submit" value="Login"><fmt:message key="button.login"/></button>--%>

<%--</form>--%>
<%--<br/>--%>
<%--<a href="${pageContext.request.contextPath}/app/registration"><fmt:message key="button.registration"/></a><br>--%>

