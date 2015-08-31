<%@ include file="../layout/taglib.jsp" %>
<div class="container"><c:url var="loginUrl" value="/login"/>
    <form action="${loginUrl}" method="post" class="form-signin">
        <h2 class="fs-title"><spring:message code="spring.todo.list.title"/></h2>
        <h4 class="fs-subtitle"><spring:message code="label.user.login.page.text.info"/></h4>

        <div class="form-group">
            <label for="username" class="input-title"><spring:message
                    code="label.user.login.input.name.text"/></label>

            <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                <input type="text" class="form-control" name="username" id="username" required autofocus tabindex="1"/>
                <button type="button" class="close reset-input">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="input-title"><spring:message
                    code="label.user.login.input.password"/></label>

            <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                <input type="password" class="form-control" name="password" id="password" required tabindex="2"/>
                <button type="button" class="close reset-input">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div><c:if test="${param.error != null}">
        <div class="alert alert-warning alert-danger" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <p>Invalid username / password</p>
        </div>
        </c:if><c:if test="${param.logout != null}">
        <p class="alert alert-danger">You have been logged out.</p>
        </c:if>
        <div class="checkbox">
            <label class="input-title">
                <input type="checkbox" id="remember-me" name="remember-me" tabindex="3"/> Remember Me?
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block action-button" type="submit" tabindex="4"><spring:message
                code="label.user.login.submit.button"/></button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <a href="/join" class="text-center new-account" tabindex="4">Create an account</a>
</div>
