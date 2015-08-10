<%@ include file="../layout/taglib.jsp" %>
<style>
    body{
        background: url('../../static/images/gpp.png');
    }

    .form-signin {
        padding: 11px;
        margin: 0 auto;
    }

    .form-signin .form-signin-heading, .form-signin .checkbox {
        margin-bottom: 10px;
    }

    .form-signin .checkbox {
        font-weight: normal;
    }

    .form-signin .form-control {
        position: relative;
        font-size: 16px;
        height: auto;
        padding: 7px;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    .form-signin .form-control:focus {
        z-index: 2;
    }

    .form-signin input[type="text"],
    .form-signin input[type="password"] {
        padding: 8px 25px 8px 8px;
        border-radius: 3px;
        margin-bottom: 10px;
        width: 100%;
        box-sizing: border-box;
        font-size: 13px;
    }

    .account-wall {
        display: none;
        background: white;
        border: 0 none;
        border-radius: 8px;
        box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
        padding: 5px 10px;
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

    .account-wall .action-button:hover, #msform .action-button:focus {
        box-shadow: 0 0 0 2px white, 0 0 0 3px #27AE60;
    }

    .center {
        margin: 10% auto;
    }


    /*headings*/
    .fs-title {
        font-size: 65px;
        text-transform: uppercase;
        color: #2C3E50;
        margin-bottom: 10px;
        text-align: center;
        font-weight: bold;
        letter-spacing: -1px;
        line-height: 1;

    }

    .fs-subtitle {
        font-weight: normal;
        font-size: 12px;
        color: #666;
        margin-bottom: 20px;
        text-align: center;
        padding-bottom: 10px;
        border-bottom: 1px solid #e0e0e0;
    }

    .login-title {
        color: #555;
        font-size: 13px;
        font-weight: 400;
        display: block;
    }

    .new-account {
        font-size: 15px;
        display: block;
        margin-top: 10px;
    }

    a.new-account {
        text-decoration: none;
        outline-style: none;
    }

    /*///////////////*/

    span.input-text {
        position: relative;
        display: block;
        clear: both;
    }

    .reset-input {
        position: absolute;
        top: 70%;
        right: 5%;
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

</style>
<div class="container">
    <div class="row center">
        <div class="col-md-6 col-md-offset-3">
            <h2 class="form-signin-heading fs-title">
                <spring:message code="spring.todo.list.title"/>
            </h2>
            <h4 class="fs-subtitle">
                <spring:message code="label.user.login.page.text.info"/>
            </h4>
        </div>
        <div class="col-md-4 col-md-offset-4">
            <div class="account-wall">
                <c:url var="loginUrl" value="/login"/>
                <form action="${loginUrl}"
                      method="post"
                      class="form-signin ">

                    <span class="input-text">
                        <label for="name" class="login-title">
                            <spring:message code="label.user.login.input.name.text"/>
                        </label>
                    <input type="text"
                           id="name"
                           name="username"
                           class="form-control"
                           required
                           autofocus
                            tabindex="1">

                        <button type="button" class="close reset-input">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </span>

                     <span class="input-text">
                    <label for="password" class="login-title">
                        <spring:message code="label.user.login.input.password"/>
                    </label>
                    <input type="password"
                           id="password"
                           name="password"
                           class="form-control"
                           required
                            tabindex="2"/>
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>

                         <button type="button" class="close reset-input">
                             <span aria-hidden="true">&times;</span>
                         </button>
                    </span>

                    <c:if test="${param.error != null}">
                        <div class="alert alert-warning alert-danger" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close" >
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <p>Invalid username / password</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <p class="alert alert-danger">You have been logged out.</p>
                    </c:if>

                    <div class="checkbox">
                        <label class="login-title">
                            <input type="checkbox" id="remember-me" name="remember-me" tabindex="3"/> Remember Me?
                        </label>
                    </div>

                    <button class="btn btn-lg btn-primary btn-block action-button" type="submit" tabindex="4">
                        <spring:message code="label.user.login.submit.button"/>
                    </button>
                </form>
            </div>
            <a href="/join" class="text-center new-account" tabindex="4">Create an account</a>
        </div>
    </div>
</div>