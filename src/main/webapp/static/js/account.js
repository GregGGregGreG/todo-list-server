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
        templateMessageUser: templateMessageUser,
        templateTaskListItem: templateTaskListItem,
        templateTaskListItemCompleted: templateTaskListItemCompleted,
        modalWindowDestroyTaskListItemId: 'destroyTaskModal',
        modalWindowDestroyButtonDestroyId: 'delete',
        btnShowAllTaskId: 'show-all-task',
        btnShowAllAssignedTaskId: 'show-all-assigned-task',

        todoApi: window.location.origin + "/api/todo",
        todoApiShowAssignedTask: window.location.origin + '/api/todo_assigned',
    });

    //Config add from user dropdown menu
    $(".user-dropdown-menu")
        .delegate('#user-performer', 'click', function () {
            var value = $(this).text();
            if (value != '') {
                $('#user-performer-label').remove();
                $(this).replaceWith('<input ' +
                    'id="user-performer" ' +
                    'type="text" ' +
                    'class="form-control" ' +
                    'placeholder="Search User"' +
                    'autofocus />');
            }
        }
    )
        .delegate('#user-performer', 'keyup', function () {
            var value = $('#user-performer').val();

            if (value == '')value = ' ';

            var user = {
                name: value
            };

            $.ajax({
                type: 'post',
                url: window.location.origin+"/api/all_search",
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
            $('#user-performer').replaceWith('<span id="user-performer-label">User performer : </span><span id="user-performer">' + value + '</span>');
            $('#user-dropdown-menu').css('')
            $(".dropdown-menu").hide();
        });
});





