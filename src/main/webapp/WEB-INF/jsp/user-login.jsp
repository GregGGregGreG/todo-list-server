<%@ include file="../layout/taglib.jsp" %>
<style>

    /*custom font*/
    /*body{*/
    /*background: linear-gradient(#ebeff2 0%, #fff 100%);*/
    /*background-image: linear-gradient(rgb(235, 239, 242) 0%, rgb(255, 255, 255) 100%);*/
    /*background-position-x: initial;*/
    /*background-position-y: initial;*/
    /*background-size: initial;*/
    /*background-repeat-x: initial;*/
    /*background-repeat-y: initial;*/
    /*background-attachment: initial;*/
    /*background-origin: initial;*/
    /*background-clip: initial;*/
    /*background-color: initial;*/
    /*}*/

    /*body {*/
    /*/!*height: 100%;*!/*/
    /*/!*Image only BG fallback*!/*/
    /*/!*background: url('http://thecodeplayer.com/uploads/media/gs.png');*!/*/
    /*/!*background = gradient + image pattern combo*!/*/
    /*/!*background: linear-gradient(rgba(196, 102, 0, 0.2), rgba(155, 89, 182, 0.2)),*!/*/
    /*/!*url('http://thecodeplayer.com/uploads/media/gs.png');*!/*/
    /*font-family: montserrat, arial, verdana;*/
    /*/!*font-size: xx-small;*!/*/
    /*}*/

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
        /*border: 1px solid #ccc;*/
        border-radius: 3px;
        margin-bottom: 10px;
        width: 100%;
        box-sizing: border-box;
        /*font-family: montserrat;*/
        /*color: #2C3E50;*/
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
        font-size: 13px;
        color: #666;
        margin-bottom: 20px;
        text-align: center;
        padding-bottom: 10px;
        border-bottom: 1px solid #e0e0e0;
    }

    .login-title {
        color: #555;
        font-size: 14px;
        font-weight: 400;
        display: block;
    }

    .login-title.success {
        color: #4074cf;
    }

    .new-account {
        font-size: 15px;
        display: block;
        margin-top: 10px;
    }

    a.new-account {
        text-decoration: none;
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
                           required autofocus>

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
                           required/>
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>

                         <button type="button" class="close reset-input">
                             <span aria-hidden="true">&times;</span>
                         </button>
                    </span>

                    <c:if test="${param.error != null}">
                        <div class="alert alert-warning alert-danger" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <p>Invalid username / password</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <p class="alert alert-danger">You have been logged out.</p>
                    </c:if>

                    <button class="btn btn-lg btn-primary btn-block action-button" type="submit">
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
    });
    // show area login
    $(".account-wall").slideToggle("slow");

    // show reset button imput
    $('span.input-text input')
            .focus(function () {
                $(this).closest('span').find('.reset-input').slideDown();
                $(this).closest('span').find('label').addClass('success');
            }).blur(function () {
                var $span = $(this).closest('span');
                var $input = $span.find('input');

                if ($input.val() == '') {
                    $span.find('label').removeClass('success');
                }

                $span.find('.reset-input').slideUp();
            });

    //reset-input action
    $('.reset-input').on('click', function () {
        //find input
        var $input = $(this).closest('span').find('input');

        if ($input.val() == '') {
            $input.blur();
        } else {
            $input.val('');
            $input.focus();
        }
    })
</script>