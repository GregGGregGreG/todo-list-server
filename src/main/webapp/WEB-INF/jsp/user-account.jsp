<%@include file="../layout/taglib.jsp" %>
<div class="tab-content">
    <div class="row">
        <div class="col-md-3">
            <a href="#" class="thumbnail">
                <img src="../../static/images/avatar.png" alt="...">
            </a>
            <h4>Name: ${user.name}</h4>

            <h4>Email: ${user.email}</h4>

            <h4>Created date: ${user.createdDate}</h4>

            <%--<c:forEach items="${user.roles}" var="role">--%>
                <%--<h3>Role: ${role.name}</h3>--%>

                <%--<h3>Id: ${role.id}</h3>--%>
            <%--</c:forEach>--%>
        </div>
        <div class="col-md-7">
            <div id="todo-list"></div>
        </div>
    </div>
</div>
<div class="modal fade bs-example-modal-sm" id="destroyTaskModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <h5 class="modal-title" id="myModalLabel"><spring:message code="label.todo.destroyTaskModal.title"/></h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal"><spring:message code="label.todo.destroyTaskModal.buttonClose.title"/></button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="delete"><spring:message code="label.todo.destroyTaskModal.buttonDelete.title"/></button>
            </div>
        </div>
    </div>
</div>
<script id="templateInputGroup" type="text/x-jquery-tmpl">
    <div class='form-group input-group'>
        <textarea id='${'${'}inputId}' class='form-control' placeholder='<spring:message code="label.todo.input.placeholder"/>'rows='1'/></textarea>
        <button id='${'${'}btnAddId}' class='btn btn-success btn-sm '><strong><spring:message code="label.todo.buttonAdd"/></strong></button>
    </div>
    <h3 id='${'${'}messageUserId}' class='text-center desc' style="display: none;"><spring:message code="label.todo.messageFromUser"/><br>
    <small><spring:message code="label.todo.messageFromUser.action"/> <strong class='text-primary'><spring:message code="label.todo.messageFromUser.hotkey"/></strong></small></h3>
    <ul id='${'${'}todoListId}' class='${'${'}todoListClass}'></ul>

</script>
<script id="templateTask" type="text/x-jquery-tmpl">
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
<script id="templateCompletedTasks" type="text/x-jquery-tmpl">
    <li id='${'${'}liId}'  class='col-md-12 list-group-item' style='color: #999999'>
        <div id='${'${'}taskId}' class='task-done'>{{html text}}</div>
        <input id='${'${'}btnDoneTaskId}'  type='checkbox' checked/>
        <span id='${'${'}btnRemoveTaskId}' class='glyphicon glyphicon-remove' data-toggle='tooltip' data-placement='top' title='Delete task'></span>
    </li>

</script>
<script>
    $(function () {
        $('#todo-list').todoList({
            templateInputGroup: templateInputGroup,
            templateTask: templateTask,
            templateCompletedTasks: templateCompletedTasks,
            modalWindowDestroyTaskId: 'destroyTaskModal',
            modalWindowDestroyButtonDestroyId: 'delete'
        });
    });
</script>

