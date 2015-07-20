<%@ include file="../layout/taglib.jsp" %>

<form:form commandName="user" cssClass="form-horizontal registraionForm">

    <c:if test="${param.success eq true}">
        <div class="alert alert-success">Registration successfull!</div>
    </c:if>

    <div class="form-group">
        <label for="name" class="col-sm-2 control-label"><spring:message code="label.user.name"/></label>

        <div class="col-sm-10">
            <form:input path="name" cssClass="form-control"/>
            <form:errors path="name"/>
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="col-sm-2 control-label"><spring:message code="label.user.email"/></label>

        <div class="col-sm-10">
            <form:input path="email" cssClass="form-control"/>
            <form:errors path="email"/>
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label"><spring:message code="label.user.password"/></label>

        <div class="col-sm-10">
            <form:password path="password" cssClass="form-control"/>
            <form:errors path="password"/>
        </div>
    </div>
    <div class="form-group">
        <label for="password_again" class="col-sm-2 control-label"><spring:message code="label.user.passwordVerification"/></label>

        <div class="col-sm-10">
            <input type="password" name="password_again" id="password_again" class="form-control">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-2">
            <input type="submit"
                   value=
                       '<spring:message code="label.user.registration.submit.button"/>'
                           class="btn btn-lg btn-primary" />
        </div>
    </div>
</form:form>
