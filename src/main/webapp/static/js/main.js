/**
 * Created by greg on 19.05.15.
 */
$(function () {
    $('#todo-list').todoList({
        templateInputGroup: inputGroupTemplate,
        templateTask: taskTemplate,
        templateCompletedTasks:taskDoneTemplate,
        modalWindowDestroyTaskId:'destroyTaskModal',
        modalWindowDestroyButtonDestroyId:'delete'
    });
});
