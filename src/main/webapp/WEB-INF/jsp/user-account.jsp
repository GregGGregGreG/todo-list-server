<%@include file="../layout/taglib.jsp" %>
<div class="tab-content">
    <div class="row">
        <div class="col-md-3">
            <a href="#" class="thumbnail">
                <img src="../../static/images/avatar.png" alt="...">
            </a>
            <h4>Name: ${user.name}</h4>

            <h4>Email: ${user.email}</h4>

            <c:forEach items="${user.roles}" var="role">
                <h3>Role: ${role.name}</h3>

                <h3>Id: ${role.id}</h3>
            </c:forEach>
        </div>
        <div class="col-md-7">
            <div id="todo-list"></div>
        </div>
        <div class="col-md-2 text-right">
            <div class="list-group affix">
                <a href="#" class="list-group-item">My tasks</a>
                <a href="#" class="list-group-item">Scheduled Tasks</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade bs-example-modal-sm" id="destroyTaskModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <h5 class="modal-title" id="myModalLabel">Are you sure you want to delete this task?</h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="delete">Delete</button>
            </div>
        </div>
    </div>
</div>
<script id="inputGroupTemplate" type="text/x-jquery-tmpl">
    <div class='form-group input-group'>
        <textarea id='${'${'}inputId}' class='form-control' placeholder='What do you have in mind this time?'rows='1'/></textarea>
        <button id='${'${'}btnAddId}' class='btn btn-success btn-sm '><strong>ADD</strong></button>
    </div>
    <h3 id='${'${'}messageUserId}' class='text-center desc'>You Todo - list is empty! Please add task.<br>
    <small>Enter a new task, and then click Add or <strong class='text-primary'>Ctrl+Enter</strong></small></h3>
    <ul id='${'${'}todoListId}' class='${'${'}todoListClass}'></ul>

</script>
<script id="taskTemplate" type="text/x-jquery-tmpl">
    <li id='${'${'}liId}' class='col-md-12 list-group-item '>
        <div id='${'${'}taskId}'>{{html text}}</div>
        <textarea id='${'${'}textAreaEditTaskId}' rows='1'></textarea>
        <input id='${'${'}btnDoneTaskId}' type='checkbox'/>
        <button id='${'${'}btnUpdateTaskId}' class='btn btn-default'><span class='glyphicon glyphicon-edit'></span>
        </button>
         <span id='${'${'}btnRemoveTaskId}' class='glyphicon glyphicon-remove' data-toggle='tooltip' data-placement='top' title='Delete task'></span>
        <span id='${'${'}dateAddingTaskId}'>${'${'}publishedDate}</span>
    </li>

</script>
<script id="taskDoneTemplate" type="text/x-jquery-tmpl">
    <li id='${'${'}liId}'  class='col-md-12 list-group-item' style='color: #999999'>
        <div id='${'${'}taskId}' class='task-done'>{{html text}}</div>
        <input id='${'${'}btnDoneTaskId}'  type='checkbox' checked/>
        <span id='${'${'}btnRemoveTaskId}' class='glyphicon glyphicon-remove' data-toggle='tooltip' data-placement='top' title='Delete task'></span>
    </li>

</script>
<script>
    $(function () {
        $('#todo-list').todoList({
            inputGroupTemplate: inputGroupTemplate,
            taskTemplate: taskTemplate,
            taskDoneTemplate: taskDoneTemplate,
            modalWindowDestroyTaskId: 'destroyTaskModal',
            modalWindowDestroyButtonDestroyId: 'delete'
        });
    });
</script>

