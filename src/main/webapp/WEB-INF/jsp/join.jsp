<%@ include file="../layout/taglib.jsp" %>
<form:form commandName="dto" cssClass="form-group registraionForm col-sm-6">
    <!-- setup-form-container -->
    <h4 class="form-group">
        <strong>Create your personal account</strong>
    </h4>

    <spring:hasBindErrors name="dto">
        <div class="alert alert-danger"><spring:message code="label.user.join.page.text.error"/></div>
    </spring:hasBindErrors>

    <div class="form-group has-feedback">
        <label for="name"><spring:message code="label.user.name"/></label>
        <form:input path="name" cssClass="form-control" placeholder="GreG A."/>
        <spring:bind path="name">
            <c:if test="${not empty status.errorMessages}">
                <form:errors path="name" cssClass="alert-danger"/>
            </c:if>
            <c:if test="${empty status.errorMessages}">
                <div class="text-muted info-user"><spring:message code="label.user.name.info"/></div>
            </c:if>
        </spring:bind>
        <span class="glyphicon form-control-feedback " aria-hidden="true"></span>
    </div>
    <div class="form-group has-feedback">
        <label for="email"><spring:message code="label.user.email"/></label>
        <form:input path="email" cssClass="form-control" placeholder="1@mail.ru"/>
        <spring:bind path="email">
            <c:if test="${empty status.errorMessages}">
                <div class="text-muted info-user"><spring:message code="label.user.email.info"/></div>
            </c:if>
            <c:if test="${not empty status.errorMessages}">
                <form:errors path="email" cssClass="alert-danger"/>
            </c:if>
        </spring:bind>
        <span class="glyphicon  form-control-feedback"></span>
    </div>
    <div class="form-group has-feedback">
        <label for="password"><spring:message code="label.user.password"/></label>
        <form:password path="password" cssClass="form-control" placeholder="Easy to remember, hard to guess"/>
        <spring:bind path="password">
            <c:if test="${empty status.errorMessages}">
                <div class="text-muted info-user"><spring:message code="label.user.password.info"/></div>
            </c:if>
            <c:if test="${not empty status.errorMessages}">
                <form:errors path="password" cssClass="alert-danger"/>
            </c:if>
        </spring:bind>
        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
    </div>
    <div class="form-group has-feedback">
        <label for="password_again"><spring:message code="label.user.passwordVerification"/></label>
        <input type="password" name="password_again" id="password_again" class="form-control">
        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
    </div>
    <div class="form-group text-center">
        <button type="submit" class="btn btn-primary btn-success">
            <spring:message code="label.user.join.submit.button"/>
        </button>
    </div>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <!-- /.setup-form-container -->
</form:form>
<script type="text/javascript">
    $(function () {
        var $elements = $("input[type!='submit'], textarea, select");
        $elements.each(function () {
            if ($(this).val().trim() == '') {
                $(this).css('background-color', '#fbfbfb ');
            }
        });

        $elements.focus(function () {
            $(this).css('background-color', '#fff');
        });
        $elements.blur(function () {
            if ($(this).val().trim() == '') {
                $(this).css('background-color', '#fbfbfb');
            }
        });
    });
    $(document).ready(function () {
        $('.registraionForm').validate({
            onkeyup: false,
            focusCleanup: false,
            errorClass: "text-warning",
            errorElement: 'strong',
            rules: {
                name: {
                    required: true,
                    remote: {
                        url: "<spring:url value='/signup_check/username' />",
                        type: "post",
                        data: {
                            username: function () {
                                return $("#name").val();
                            }
                        }
                    }
                },
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    minlength: 5

                },
                password_again: {
                    required: true,
                    minlength: 5,
                    equalTo: '#password'
                }
            },
            highlight: function (element) {
                $(element).closest('.form-group').find(':first-child').addClass('text-danger');

                $(element).closest('.form-group')
                        .removeClass('has-success')
                        .addClass('has-error');

                $(element).closest('.form-group')
                        .find('span')
                        .removeClass('glyphicon-ok')
                        .addClass('glyphicon-remove');

                $(element).closest('.form-group').find('.info-user').addClass('hide');

            },
            success: function (element) {
                $(element).closest('.form-group').find('label').removeClass('text-danger');

                $(element).closest('.form-group')
                        .removeClass('has-error')
                        .addClass('has-success');

                $(element).closest('.form-group')
                        .find('span')
                        .addClass('glyphicon-ok')
                        .removeClass('glyphicon-remove');

                $(element).closest('.form-group').find('.info-user').removeClass('hide');
            }

        })
    })
</script>

