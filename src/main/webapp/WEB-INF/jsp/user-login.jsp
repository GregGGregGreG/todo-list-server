<%@ include file="../layout/taglib.jsp" %>
<style>
    .form-signin {
        padding: 13px;
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

    .form-signin input[type="text"] {
        margin-bottom: 10px;
        border-bottom-left-radius: 0;
        border-bottom-right-radius: 0;
    }

    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }

    .account-wall {
        margin: 10px auto;
        padding: 0px 0px 0px 0px;
        border-radius: 15px;
        background-color: #f7f7f7;
        -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    }

    .center {
        margin: 10% auto;
    }

    .login-title {
        color: #555;
        font-size: 18px;
        font-weight: 400;
        display: block;
    }

    .profile-img {
        width: 96px;
        height: 96px;
        margin: 0 auto 10px;
        display: block;
        -moz-border-radius: 50%;
        -webkit-border-radius: 50%;
        border-radius: 50%;
    }

    .need-help {
        margin-top: 10px;
    }

    .new-account {
        display: block;
        margin-top: 10px;
    }

    .info {
        color: #111;
        font-family: 'Open Sans', sans-serif;
        font-size: 16px;
        font-weight: 300;
        line-height: 32px;
        text-align: center;
        border-bottom: 1px solid #e0e0e0;
    }

    .todos {
        margin: 10px;
        color: #111;
        font-family: 'Helvetica Neue', sans-serif;
        font-size: 75px;
        font-weight: bold;
        letter-spacing: -1px;
        line-height: 1;
        text-align: center;
    }
</style>
<div class="container">
    <div class="row center">
        <div class="col-md-6 col-md-offset-3">
            <h2 class="form-signin-heading todos">
                <spring:message code="spring.todo.list.title"/>
            </h2>
            <h4 class="info">
                <spring:message code="label.user.login.page.text.info"/>
            </h4>
        </div>
        <div class="col-md-4 col-md-offset-4">

            <div class="account-wall">
                <c:url var="loginUrl" value="/login"/>
                <form action="${loginUrl}"
                      method="post"
                      class="form-signin ">
                    <c:if test="${param.error != null}">
                        <p class="alert alert-danger">Invalid username / password</p>
                    </c:if>

                    <c:if test="${param.logout != null}">
                        <p class="alert alert-danger">You have been logged out.</p>
                    </c:if>
                    <label for="name">
                        <spring:message code="label.user.login.input.name.text"/>
                    </label>
                    <input type="text"
                           id="name"
                           name="username"
                           class="form-control"
                           required autofocus/>
                    <label for="password">
                        <spring:message code="label.user.password"/>
                    </label>
                    <input type="password"
                           id="password"
                           name="password"
                           class="form-control"
                           required>
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        <spring:message code="label.user.login.submit.button"/>
                    </button>
                </form>
            </div>
            <a href="/join" class="text-center new-account">Create an account </a>
        </div>
    </div>
</div>
<script>
    //Set footer style
    $(function () {
        $('html').css({
            'position': 'relative',
            'min-height': '100%'
        });
        /* Margin bottom by footer height */
        $('body').css({
            'margin-bottom': '60px'
        });
        $('footer').css({
            'position': 'absolute',
            'bottom': '10px',
            'width': '100%'
        });
    })
</script>