//Validate form field
$(document).ready(function () {
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    var validator = $('.registraionForm').validate({
        onkeyup: false,
        onfocusOut: true,
        focusInvalid: false,
        errorClass: "text-warning error-validation",
//            errorElement: 'strong',
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
            $(element).closest('.form-group').find(':first-child').addClass('text-danger');

            $(element).closest('.form-group')
                .removeClass('has-success')
                .addClass('has-error');

            $(element).closest('.form-group')
                .find(' span .glyphicon.form-control-feedback')
                .removeClass('glyphicon-ok')
                .addClass('glyphicon-exclamation-sign');

            $(element).closest('.form-group').find('.fs-subtitle ').addClass('hide');
        },
        success: function (element) {
            $(element).closest('.form-group').find(':first-child').removeClass('text-danger');

            $(element).closest('.form-group')
                .removeClass('has-error')
                .addClass('has-success');

            $(element).closest('.form-group')
                .find('span .glyphicon.form-control-feedback')
                .addClass('glyphicon-ok')
                .removeClass('glyphicon-exclamation-sign');

            $(element).closest('.form-group').find('.fs-subtitle').removeClass('hide');
            $(element).closest('.form-group').find('.error-validation').remove();
        }
    });

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
    // show reset button imput
    $('span.input-text input')
        .focus(function () {
            //set position info-glyphicon
            $(this).closest('span').find('.info-glyphicon').animate({
                right: "+20px"
            }, 300);

            $(this).closest('span').find('.reset-input').slideDown();
        }).blur(function () {
            //set position info-glyphicon
            $(this).closest('span').find('.info-glyphicon').animate({
                right: "5px"
            }, 300);

            var $span = $(this).closest('span');

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
    });

    // bind key CTRL+ENTER from valid filed
    $(".registraionForm").keydown(function (event) {
        if (event.ctrlKey && event.keyCode == 13) {
            (event.target).blur();
            (event.target).focus();
        }
    });



    // input field color
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
});
