//Validate form field
$(document).ready(function () {
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    $('.form-signin').validate({
        onkeyup: false,
        onfocusOut: true,
        focusInvalid: false,
        errorClass: "text-warning error-validation",
        rules: {
            name: {
                required: true,
                remote: {
                    url: "/signup_check/username",
                    type: "post",
                    data: {
                        username: function () {
                            return $("#name").val();
                        }
                    },
                    dataFilter: function (data) {
                        var json = JSON.parse(data);
                        if (json.status) {
                            return "true";
                        }
                        return JSON.stringify(json.error);
                    }
                }
            },
            email: {
                required: true,
                remote: {
                    url: "/signup_check/email",
                    type: "post",
                    data: {
                        email: function () {
                            return $("#email").val();
                        }
                    },
                    dataFilter: function (data) {
                        var json = JSON.parse(data);
                        if (json.status) {
                            return '"true"';
                        }
                        return JSON.stringify(json.error);
                    }
                }
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
            var $form = $(element).closest('.form-group');

            $form
                .removeClass('has-success')
                .addClass('has-error');

            $form.find(':first-child').addClass('text-danger');

            $form
                .find('span.glyphicon.form-control-feedback')
                .removeClass('glyphicon-ok')
                .addClass('glyphicon-exclamation-sign');

            $form.find('.fs-subtitle ').addClass('hide');
        },
        success: function (element) {
            var $form = $(element).closest('.form-group');

            $form
                .removeClass('has-error')
                .addClass('has-success');

            $form.find(':first-child').removeClass('text-danger');

            $form
                .find('span.glyphicon.form-control-feedback')
                .addClass('glyphicon-ok')
                .removeClass('glyphicon-exclamation-sign');

            $form.find('.fs-subtitle').removeClass('hide');
            $form.find('.error-validation').remove();
        }
    });

    // show reset button imput
    var animateTimeResetButton = 300;
    $('.form-group input')
        .focus(function () {
            var $form = $(this).closest('.form-group');
            //set position info-glyphicon
            $form.find('.info-glyphicon').animate({
                right: "+20px"
            }, animateTimeResetButton);

            $form.find('.reset-input').slideDown();
        }).blur(function () {
            var $form = $(this).closest('.form-group');
            //set position info-glyphicon
            $form.find('.info-glyphicon').animate({
                right: "5px"
            }, animateTimeResetButton);

            $form.find('.reset-input').slideUp();
        });

    //reset-input action
    $('.reset-input').on('click', function () {
        //find input
        var $input = $(this).closest('.form-group').find('input');

        if ($input.val() == '') {
            $input.blur();
        } else {
            $input.val('');
            $input.focus();
        }
    });

    // bind key CTRL+ENTER from valid filed
    $(".form-signin").keydown(function (event) {
        if (event.ctrlKey && event.keyCode == 13) {
            (event.target).blur();
            (event.target).focus();
        }
    });

    // input field color
    $(function () {
        var $elements = $("input[type!='submit'], textarea, select");

        $elements.focus(function () {
            $(this).css('background-color', '#fff');
        });

        $elements.blur(function () {
            if ($(this).val().trim() == '') {
                $(this).css('background-color', '#fbfbfb');
            }
        });
    });
});
