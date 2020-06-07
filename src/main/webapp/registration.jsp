<html>
<head>
    <%@ include file="/parts/menu.jsp" %>
    <title>Registration</title>
</head>
<body>
<div class="content">
    <div class="content-inside">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-4">
                    <div class="panel panel-default" style="margin-top:45px">
                        <div class="panel-heading">
                            <h3 class="panel-title"><fmt:message key="registration"/></h3>
                        </div>
                        <div class="panel-body">
                            <div class="alert">
                                <c:if test="${requestScope.user_exists != null}">
                                    <div class="alert alert-danger" role="alert">User already exists</div>
                                </c:if>
                            </div>
                            <form method="post" action="${pageContext.request.contextPath}/app/registration">
                                <input id="csrfToken" name="csrfToken" type="hidden" value="${sessionScope.csrfToken}" />
                                <div class="form-group">
                                    <c:if test="${requestScope.wrongEmailFormat}">
                                        <div class="alert alert-danger" role="alert"><fmt:message
                                                key="wrongEmailFormat"/></div>
                                    </c:if>
                                    <%--@declare id="email"--%>
                                    <label for="email">
                                        <input required type="text" class="form-control" name="email"
                                               placeholder="Email">
                                    </label>
                                </div>
                                <div class="form-group">
                                    <c:if test="${requestScope.wrongPasswordFormat}">
                                        <div class="alert alert-danger" role="alert"><fmt:message
                                                key="wrongPasswordFormat"/></div>
                                    </c:if>
                                    <label for="password">
                                        <input required type="password" class="form-control" id="password"
                                               placeholder=
                                               <fmt:message key="placeholder.password"/> name="password"></label>
                                </div>
                                <div class="form-group">
                                    <c:if test="${requestScope.passwordsDontMatch}">
                                        <div class="alert alert-danger" role="alert"><fmt:message
                                                key="passwordsDontMatch"/></div>
                                    </c:if>
                                    <label for="confirmPassword">
                                        <input required type="password" class="form-control" id="confirmPassword"
                                               placeholder=
                                               <fmt:message key="password.confirm"/> name="confirmPassword"></label>
                                </div>
                                <div class="form-group">
                                    <c:if test="${requestScope.wrongNameFormat}">
                                        <div class="alert alert-danger" role="alert"><fmt:message
                                                key="wrongNameFormat"/></div>
                                    </c:if>
                                    <label for="name">
                                        <input type="text" class="form-control" id="name" placeholder=
                                        <fmt:message key="placeholder.name.en"/>
                                                name="name"></label>
                                </div>
                                <div class="form-group">
                                    <c:if test="${requestScope.wrongNameUkrFormat}">
                                        <div class="alert alert-danger" role="alert"><fmt:message
                                                key="wrongNameUkrFormat"/></div>
                                    </c:if>
                                    <label for="nameUkr">
                                        <input type="text" class="form-control" id="nameUkr" placeholder=
                                        <fmt:message key="placeholder.name.ukr"/>
                                                name="nameUkr"></label>
                                </div>
                                <button type="submit" class="btn btn-default"><fmt:message
                                        key="button.registration"/></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>