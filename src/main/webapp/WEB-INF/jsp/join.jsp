<%@ include file="../layout/taglib.jsp" %>

<form:form commandName="dto" cssClass="form-group ">
    <!-- setup-form-container -->
    <h4 class="form-group">
        <strong>Create your personal account</strong>
    </h4>
    <c:if test="${param.success eq true}">
        <div class="alert alert-success"><spring:message code="label.user.registration.page.text"/></div>
    </c:if>

    <div class="form-group">
        <label for="name"><spring:message code="label.user.name"/></label>
        <form:input path="name" cssClass="form-control"/>
        <form:errors path="name"/>
    </div>
    <div class="form-group">
        <label for="email"><spring:message code="label.user.email"/></label>
        <form:input path="email" cssClass="form-control"/>
        <form:errors path="email"/>
    </div>
    <div class="form-group">
        <label for="password"><spring:message code="label.user.password"/></label>
        <form:password path="password" cssClass="form-control"/>
        <form:errors path="password"/>
    </div>
    <div class="form-group">
        <label for="password_again"><spring:message code="label.user.passwordVerification"/></label>
        <input type="password" name="password_again" id="password_again" class="form-control">
    </div>
    <div class="form-group text-center">
        <button type="submit" class="btn btn-primary btn-success">
            <spring:message code="label.user.registration.submit.button"/>
        </button>
    </div>
    <!-- /.setup-form-container -->
</form:form>
