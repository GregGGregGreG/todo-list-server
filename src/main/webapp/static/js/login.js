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
    }).blur(function () {
        var $span = $(this).closest('span');
        var $input = $span.find('input');

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
