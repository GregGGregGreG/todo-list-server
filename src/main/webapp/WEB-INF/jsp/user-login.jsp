<%@ include file="../layout/taglib.jsp" %>
<style>
    .form-signin {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
    }

    .form-signin .form-signin-heading,
    .form-signin .checkbox {
        margin-bottom: 10px;
    }

    .form-signin .checkbox {
        font-weight: normal;
    }

    .form-signin .form-control {
        position: relative;
        height: auto;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        padding: 10px;
        font-size: 16px;
    }

    .form-signin .form-control:focus {
        z-index: 2;
    }

    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
<c:url var="loginUrl" value="/login"/>
<form action="${loginUrl}"
      method="post"
      class="form-signin">
    <c:if test="${param.error != null}">
        <p class="alert alert-danger">Invalid username / password</p>
    </c:if>

    <c:if test="${param.logout != null}">
        <p class="alert alert-danger">You have been logged out.</p>
    </c:if>

    <h2 class="form-signin-heading">
        <spring:message code="label.user.login.page.text"/>
    </h2>

    <input type="text"
           id="name"
           name="username"
           class="form-control"
           placeholder='<spring:message code="label.user.name"/>'
           required autofocus/>
    <input type="password"
           id="password"
           name="password"
           class="form-control"
           placeholder='<spring:message code="label.user.password"/>'
           required>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

    <button class="btn btn-lg btn-primary btn-block" type="submit">
        <spring:message code="label.user.login.submit.button"/>
    </button>
</form>

<%--<form:form method="POST" commandName="user" action="/login" class="form-signin">--%>
    <%--<c:if test="${param.error != null}">--%>
        <%--<p class="alert alert-danger">Invalid username / password</p>--%>
    <%--</c:if>--%>

    <%--<c:if test="${param.logout != null}">--%>
        <%--<p class="alert alert-danger">You have been logged out.</p>--%>
    <%--</c:if>--%>

    <%--<h2 class="form-signin-heading">--%>
        <%--<spring:message code="label.user.login.page.text"/>--%>
    <%--</h2>--%>

    <%--<form:input path="name"--%>
                <%--type="text"--%>
                <%--id="username"--%>
                <%--name="username"--%>
                <%--class="form-control"--%>
                <%--placeholder='<spring:message code="label.user.name"/>'/>--%>

    <%--<form:input path="password"--%>
                <%--type="password"--%>
                <%--id="password"--%>
                <%--name="password"--%>
                <%--class="form-control"--%>
                <%--placeholder='<spring:message code="label.user.password"/>'/>--%>

    <%--<button class="btn btn-lg btn-primary btn-block" type="submit">--%>
        <%--<spring:message code="label.user.login.submit.button"/>--%>
    <%--</button>--%>
<%--</form:form>--%>