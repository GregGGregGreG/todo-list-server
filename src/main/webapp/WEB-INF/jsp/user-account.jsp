<%@include file="../layout/taglib.jsp" %>
<style>
    .account-wall {
        background: white;
        border: 0 none;
        border-radius: 8px;
        box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
        padding: 20px 30px;
        box-sizing: border-box;
        margin-right: 20px;
        margin-bottom: 20px;
    }
</style>

<div>
    <div class="row">
        <div class="col-md-3 account-wall ">
            <a href="#" class="thumbnail">
                <img src="../../static/images/avatar.png" alt="...">
            </a>

            <p class="td-subtitle">Name: ${user.name}</p>

            <p class="td-subtitle">Join date: ${user.createdDate}</p>
        </div>
        <div class="col-md-7 account-wall">
            <div id="tasks"></div>
        </div>
    </div>
</div>
<div class="modal fade bs-example-modal-sm" id="destroyTaskModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <p class="modal-title" id="myModalLabel">
                    <spring:message code="label.todo.destroyTaskModal.title"/>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">
                    <spring:message code="label.todo.destroyTaskModal.buttonClose.title"/>
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="delete">
                    <spring:message code="label.todo.destroyTaskModal.buttonDelete.title"/>
                </button>
            </div>
        </div>
    </div>
</div>
<script id="templateTaskList" type="text/x-jquery-tmpl">
    <div class='form-group input-group'>
        <textarea id='${'${'}inputId}'
            class='form-control'
            placeholder='<spring:message code="label.todo.input.placeholder"/>'
            rows='1'/>
        </textarea>
        <div class="row">
            <div class="col-md-5">
                <button id='${'${'}btnAddItemId}'  class='btn btn-success btn-sm '>
                    <strong><spring:message code="label.todo.buttonAdd"/></strong>
                </button>
                <p class="td-input-subtitle">From user:</p>
            </div>
            <div class="col-md-7 user-dropdown-menu">
                 <input id="testAjax" class="form-control " placeholder="Search User">
                 <ul class="dropdown-menu dropdown-menu-left"></ul>
            </div>
        </div>
  </div>
    <h3 id='${'${'}messageUserId}' class='text-center desc' style="display: none;">
        <spring:message code="label.todo.messageFromUser"/><br>
        <small><spring:message code="label.todo.messageFromUser.action"/>
            <strong class='text-primary'>
                <spring:message code="label.todo.messageFromUser.hotkey"/>
            </strong>
        </small>
    </h3>
    <ul id='${'${'}taskListId}' class='${'${'}todoListClass}'></ul>
</script>
<script id="templateTaskListItem" type="text/x-jquery-tmpl">
    <li id='${'${'}tasksListItemId}' class='col-md-12 list-group-item '>
         <span class="mini-avatar-user-name">greg</span>
         <a>
           <img src="../../static/images/avatar.png" alt="..." class="mini-avatar-img">
         </a>

        <div id='${'${'}taskDeskId}'>{{html text}}</div>
        <textarea id='${'${'}editTaskDeskId}' rows='1'></textarea>

         <label>
            <input type="checkbox" id="${'${'}btnCheckTaskId}" class="tasks-list-cb">
            <span id="${'${'}btnCheckTaskId}" class="tasks-list-mark"></span>
         </label>

        <button id='${'${'}btnUpdateTaskId}' class='btn btn-default'><span class='glyphicon glyphicon-edit'></span>
        </button>
        <span id='${'${'}btnRemoveTaskId}' class='glyphicon glyphicon-remove' data-toggle='tooltip' data-placement='top' title='Delete task'></span>
        <span id='${'${'}taskListItemCreatedDateId}'>${'${'}publishedDate}</span>
    </li>
</script>
<script id="templateTaskListItemCompleted" type="text/x-jquery-tmpl">
    <li id='${'${'}tasksListItemId}'  class='col-md-12 list-group-item'>
         <span class="mini-avatar-user-name">greg</span>
         <a>
             <img src="../../static/images/avatar.png" alt="..." class="mini-avatar-img">
         </a>

         <label>
             <input type="checkbox" id="${'${'}btnCheckTaskId}" class="tasks-list-cb" checked>
             <span id="${'${'}btnCheckTaskId}" class="tasks-list-mark"></span>
             <div id='${'${'}taskDeskId}' class='task-done'>{{html text}}</div>
         </label>

        <span id='${'${'}btnRemoveTaskId}' class='glyphicon glyphicon-remove' data-toggle='tooltip' data-placement='top' title='Delete task'></span>
    </li>




</script>
