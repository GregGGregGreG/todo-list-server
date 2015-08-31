<%@ include file="../layout/taglib.jsp" %>
<div class="container">
    <h4 class="fs-title"><strong>Create your personal account</strong></h4>
    <c:if test="${param.success eq true}">
        <div class="alert alert-success"><spring:message code="label.user.join.page.text.success"/></div>
    </c:if>
    <c:if test="${empty param.success}">
    <form:form commandName="dto" cssClass="form-signin "><!-- setup-form-container --><spring:hasBindErrors name="dto">
        <div class="alert alert-danger"><spring:message code="label.user.join.page.text.error"/></div>
        </spring:hasBindErrors>
        <div class="form-group has-feedback">
            <label for="name"><spring:message
                    code="label.user.name"/></label>
            <form:input path="name"
                        cssClass="form-control"
                        placeholder="GreG A."
                        tabindex="1"/>
            <button type="button" class="close reset-input">
                <span aria-hidden="true">&times;</span>
            </button>
            <span class="glyphicon form-control-feedback info-glyphicon" aria-hidden="true"></span><spring:bind
                path="name">
            <c:if test="${not empty status.errorMessages}">
                <form:errors path="name" cssClass="alert-danger"/>
            </c:if>
            <c:if test="${empty status.errorMessages}">
            <div class="text-muted fs-subtitle"><spring:message code="label.user.name.info"/></div></c:if></spring:bind>
        </div>
        <div class="form-group has-feedback">
            <label for="email"><spring:message code="label.user.email"/></label>
            <form:input path="email"
                        cssClass="form-control"
                        placeholder="1@mail.ru"
                        tabindex="2"/>
            <button type="button" class="close reset-input">
                <span aria-hidden="true">&times;</span>
            </button>
            <span class="glyphicon form-control-feedback info-glyphicon" aria-hidden="true"></span><spring:bind
                path="email">
            <c:if test="${not empty status.errorMessages}">
                <form:errors path="email" cssClass="alert-danger"/>
            </c:if>
            <c:if test="${empty status.errorMessages}">
            <div class="text-muted fs-subtitle "><spring:message code="label.user.email.info"/></div></c:if></spring:bind>
        </div>
        <div class="form-group has-feedback">
            <label for="password"><spring:message code="label.user.password"/></label>
            <form:password path="password"
                           cssClass="form-control" placeholder="Easy to remember, hard to guess"
                           tabindex="3"/>
            <button type="button" class="close reset-input">
                <span aria-hidden="true">&times;</span>
            </button>
            <span class="glyphicon form-control-feedback info-glyphicon" aria-hidden="true"></span><spring:bind
                path="password">
            <c:if test="${not empty status.errorMessages}">
                <form:errors path="password" cssClass="alert-danger"/>
            </c:if>
            <c:if test="${empty status.errorMessages}">
            <div class="text-muted fs-subtitle "><spring:message code="label.user.password.info"/></div></c:if></spring:bind>
        </div>
        <div class="form-group has-feedback">
            <label for="password_again"><spring:message code="label.user.passwordVerification"/></label>
            <input type="password" name="password_again" id="password_again" class="form-control" tabindex="4"/>
            <button type="button" class="close reset-input">
                <span aria-hidden="true">&times;</span>
            </button>
            <span class="glyphicon form-control-feedback info-glyphicon" aria-hidden="true"></span>
            <button type="button" class="close reset-input"><span aria-hidden="true">&times;</span></button>
        </div>
        <div class="form-group text-center">
            <button type="submit" class="btn btn-lg btn-block btn-success action-button" tabindex="5"><spring:message code="label.user.join.submit.button"/></button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form><!-- /.setup-form-container --></c:if>
</div>