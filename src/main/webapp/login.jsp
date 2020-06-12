<html>
<head>
    <%@ include file="/commonmenu.jsp" %>
    <title>Login Page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="card bg-light mb-3" style="margin-left: auto;
        margin-right: auto; align-items: center">
            <div class="card-header">
                <h3><fmt:message key="authentication"/></h3>
            </div>
            <div class="card-body">
                <div class="info">
                    <c:if test="${param.logout}">
                        <div class="alert alert-info" role="alert"><fmt:message key="message.logged.out"/></div>
                    </c:if>
                </div>
                <div class="alert">
                    <c:if test="${param.error}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="message.error.invalid.email.password"/></div>
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

