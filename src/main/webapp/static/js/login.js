// show reset button imput
$('.form-group input')
    .focus(function () {
        $(this).closest('.form-group').find('.reset-input').slideDown();
    }).blur(function () {
        var $span = $(this).closest('.form-group');
        $span.find('.reset-input').slideUp();
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
