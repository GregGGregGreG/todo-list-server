<%@ include file="../layout/taglib.jsp" %>
<style>
    body {
        background: url('../../static/images/gpp.png');
    }

    .account-wall {
        background: white;
        border: 0 none;
        border-radius: 8px;
        box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
        padding: 20px 30px;
        box-sizing: border-box;
        margin-top: 5%;
    }

    /*buttons*/
    .account-wall .action-button {
        /*width: 100px;*/
        /*background: #27AE60;*/
        font-weight: bold;
        color: white;
        border: 0 none;
        /*border-radius: 1px;*/
        cursor: pointer;
        padding: 10px 5px;
        /*margin: 10px 5px;*/
    }

    /*headings*/
    .fs-title {
        font-size: 18px;
        text-transform: uppercase;
        color: #2C3E50;
        margin-bottom: 10px;
        text-align: center;
        font-weight: bold;
        letter-spacing: 1px;
        line-height: 1;

    }

    .fs-subtitle {
        font-weight: normal;
        font-size: 12px;
        color: #666;
        margin-bottom: 8px;

        /*padding-bottom: 10px;*/
        /*border-bottom: 1px solid #e0e0e0;*/
    }

    .form-signin input[type="text"],
    .form-signin input[type="password"] {
        padding: 18px 25px 18px 8px;
        /*border: 1px solid #ccc;*/
        border-radius: 3px;
        margin-bottom: 5px;
        width: 100%;
        box-sizing: border-box;
        /*font-family: montserrat;*/
        /*color: #2C3E50;*/
        font-size: 13px;
    }

    .account-wall .action-button:hover, #msform .action-button:focus {
        box-shadow: 0 0 0 2px white, 0 0 0 3px #27AE60;
    }

    span.input-text {
        position: relative;
        display: block;
        clear: both;
    }

    .reset-input {
        position: absolute;
        top: 43px;
        right: 10px;
        -webkit-transform: translateY(-50%);
        -moz-transform: translateY(-50%);
        -ms-transform: translateY(-50%);
        -o-transform: translateY(-50%);
        transform: translateY(-50%);
        /*opacity: 0;*/
        display: none;
        color: #b3b3b3;
        z-index: 9999;
        outline-style: none;
    }

    .info-glyphicon {
        position: absolute;
        /*right: 5px;*/
    }

</style>
<div class="col-sm-6 col-md-offset-3">
    <h4 class="form-group fs-title ">
        <strong>Create your personal account</strong>
    </h4>
    <form:form commandName="dto" cssClass="form-group registraionForm account-wall form-signin">
        <!-- setup-form-container -->
        <c:if test="${param.success eq true}">
            <div class="alert alert-success"><spring:message code="label.user.join.page.text.success"/></div>
        </c:if>

        <spring:hasBindErrors name="dto">
            <div class="alert alert-danger"><spring:message code="label.user.join.page.text.error"/></div>
        </spring:hasBindErrors>

        <c:if test="${empty param.success}">
            <div class="form-group has-feedback">
            <span class="input-text">
                <label for="name">
                    <spring:message code="label.user.name"/>
                </label>

                <form:input path="name"  cssClass="form-control" placeholder="GreG A." tabindex="1"/>

                <button type="button" class="close reset-input">
                    <span aria-hidden="true">&times;</span>
                </button>

                <span class="glyphicon form-control-feedback info-glyphicon" aria-hidden="true"></span>
            </span>

                <spring:bind path="name">
                    <c:if test="${not empty status.errorMessages}">
                        <form:errors path="name" cssClass="alert-danger"/>
                    </c:if>
                    <c:if test="${empty status.errorMessages}">
                        <div class="text-muted fs-subtitle"><spring:message code="label.user.name.info"/></div>
                    </c:if>
                </spring:bind>

            </div>
            <div class="form-group has-feedback">
            <span class="input-text">
                <label for="email">
                    <spring:message code="label.user.email"/>
                </label>

                <form:input path="email" cssClass="form-control" placeholder="1@mail.ru" tabindex="2"/>

                <button type="button" class="close reset-input">
                    <span aria-hidden="true">&times;</span>
                </button>
                   <span class="glyphicon form-control-feedback info-glyphicon" aria-hidden="true"></span>
            </span>

                <spring:bind path="email">
                    <c:if test="${empty status.errorMessages}">
                        <div class="text-muted fs-subtitle ">
                            <spring:message code="label.user.email.info"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty status.errorMessages}">
                        <form:errors path="email" cssClass="alert-danger"/>
                    </c:if>
                </spring:bind>

            </div>
            <div class="form-group has-feedback">
            <span class="input-text">
                 <label for="password">
                     <spring:message code="label.user.password"/>
                 </label>

                 <form:password path="password" cssClass="form-control" placeholder="Easy to remember, hard to guess"
                           tabindex="3"/>

                <button type="button" class="close reset-input">
                    <span aria-hidden="true">&times;</span>
                </button>
                  <span class="glyphicon form-control-feedback info-glyphicon" aria-hidden="true"></span>
            </span>

                <spring:bind path="password">
                    <c:if test="${empty status.errorMessages}">
                        <div class="text-muted fs-subtitle ">
                            <spring:message code="label.user.password.info"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty status.errorMessages}">
                        <form:errors path="password" cssClass="alert-danger"/>
                    </c:if>
                </spring:bind>

            </div>
            <div class="form-group has-feedback">
             <span class="input-text">
                 <label for="password_again">
                     <spring:message code="label.user.passwordVerification"/>
                 </label>

                 <input type="password" name="password_again" id="password_again" class="form-control" tabindex="4"/>

                 <button type="button" class="close reset-input">
                     <span aria-hidden="true">&times;</span>
                 </button>

                 <span class="glyphicon form-control-feedback info-glyphicon" aria-hidden="true"></span>
             </span>
                <button type="button" class="close reset-input">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="form-group text-center">
                <button type="submit" class="btn btn-lg btn-block btn-success action-button" tabindex="5">
                    <spring:message code="label.user.join.submit.button"/>
                </button>
            </div>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <!-- /.setup-form-container -->
        </c:if>
    </form:form>
</div>
