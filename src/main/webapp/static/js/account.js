$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
$(document).ready(function () {
    $('#tasks').todoList({
        templateTaskList: templateTaskList,
        templateTaskListItem: templateTaskListItem,
        templateTaskListItemCompleted: templateTaskListItemCompleted,
        modalWindowDestroyTaskListItemId: 'destroyTaskModal',
        modalWindowDestroyButtonDestroyId: 'delete'
    });
});
