$(document).ready(function () {
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    //Config todo-list
    $('#tasks').todoList({
        templateTaskList: templateTaskList,
        templateTaskListItem: templateTaskListItem,
        templateTaskListItemCompleted: templateTaskListItemCompleted,
        modalWindowDestroyTaskListItemId: 'destroyTaskModal',
        modalWindowDestroyButtonDestroyId: 'delete'
    });

    //Config add from user dropdown menu
    $(".user-dropdown-menu")
        .delegate('#from-user', 'click', function () {
            var value = $(this).text();
            $(this).replaceWith('<input ' +
                'id="testAjax" ' +
                'type="text" ' +
                'class="form-control" ' +
                'placeholder="Search for..."' +
                ' value=' + value + '>');
        })
        .delegate('#testAjax', 'keyup', function () {
            var value = $('#testAjax').val();

            if (value == '')value = ' ';

            var user = {
                name: value
            };

            $.ajax({
                type: 'post',
                url: "http://localhost:8080/api/all_search",
                headers: {
                    'Content-Type': 'application/json'
                },
                dataType: "json",
                data: JSON.stringify(user),
                success: function (userlist) {
                    $(".dropdown-menu").empty();
                    $.each(userlist, function (i, user) {
                        $(".dropdown-menu").append("<li>" + user.name + "</li>");
                    });

                    userlist.length == 0
                        ? $(".dropdown-menu").hide()
                        : $(".dropdown-menu").show();
                },

            })
        })
        .delegate('.dropdown-menu li', 'click', function () {
            var value = $(this).text();
            //$('#testAjax').val(value);
            $('#testAjax').replaceWith('<div id="from-user">' + value + '</div>');

            $(".dropdown-menu").hide();
        });
});





