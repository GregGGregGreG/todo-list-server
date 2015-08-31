<%@ include file="../layout/taglib.jsp" %>
<%--INPUT GROUP--%>
<script id="templateTaskList" type="text/x-jquery-tmpl">
<div class='input-group'>
    <textarea id='${'${'}inputId}' class='form-control' placeholder='<spring:message
        code="label.todo.input.placeholder"/>' rows='2'/>
    <div class="row">
        <div class="col-md-3">
            <button type="button" id='${'${'}btnAddItemId}' class="btn btn-success btn3d">
                <span class="glyphicon glyphicon-ok"></span>
                <strong><spring:message code="label.todo.buttonAdd"/></strong>
            </button>
        </div>

        <div class="user-dropdown-menu col-md-9">
            <div id="custom-search-input">
                <div class="input-group">
                    <input type="text" id="user-performer" name='user-performer' class="form-control" placeholder="Search User"/>
                    <span class="input-group-btn">
                         <button class="btn btn-info" type="button">
                            <i class="glyphicon glyphicon-search"></i>
                         </button>
                    </span>
                    <ul class="dropdown-menu dropdown-menu-left"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%--line separator--%>
<hr class="colorgraph">
<%--to-do list--%>
<ul id='${'${'}taskListId}' class='${'${'}todoListClass} list-group'></ul>
</script>
<%---------------------------------------------------------------------------%>
<%--Message about empty list--%>
<script id='templateMessageUser' type='text/x-jquery-tmpl'>
<h3 id='${'${'}messageUserId}' class='text-center desc' style="display: none;">
   <spring:message code="label.todo.messageFromUser"/><br>
   <small><spring:message code="label.todo.messageFromUser.action"/>
        <strong class='text-primary'><spring:message code="label.todo.messageFromUser.hotkey"/></strong>
   </small>
</h3>
</script>
<%---------------------------------------------------------------------------%>
<%--Task--%>
<script id='templateTaskListItem' type='text/x-jquery-tmpl'>
<li id='${'${'}tasksListItemId}' class='list-group-item'>
    <div class="row">
        <div class="col-xs-9 col-lg-10">
            <div class="input-group">
                <label>
                    <input type="checkbox" id="${'${'}btnCheckTaskId}" class="tasks-list-cb">
                    <span id="${'${'}btnCheckTaskId}" class="tasks-list-mark"></span>
                </label>
                <button id='${'${'}btnUpdateTaskId}' class='btn btn-default'>
                    <span class='glyphicon glyphicon-edit'></span>
                </button>
                <span class="input-group-btn">
                    <a>
                        <img src="../../static/images/avatar.png" alt="..." class="mini-avatar-img">
                    </a>
                </span>

                <span class='mini-avatar-user-name'>{{html created}}</span>
                <span class='user-perfomer-name'> {{html performer}}</span>

                <div id='${'${'}taskDeskId}'>{{html text}}</div>
                <textarea id='${'${'}editTaskDeskId}' rows='1'></textarea>
                <span id='${'${'}taskListItemCreatedDateId}'>${'${'}publishedDate}</span>
            </div><!-- /input-group -->
        </div><!-- /.col-lg-6 -->
        <span id='${'${'}btnRemoveTaskId}' class='glyphicon glyphicon-remove' data-toggle='tooltip' data-placement='top' title='Delete task'></span>
    </div><!-- /.row -->
</li><!-- /.li -->
</script>
<%---------------------------------------------------------------------------%>
<%--Task completed--%>
<script id="templateTaskListItemCompleted" type="text/x-jquery-tmpl">
    <li id='${'${'}tasksListItemId}'  class='list-group-item'>
         <span class="mini-avatar-user-name">{{html created}}</span>
         <a>
             <img src="../../static/images/avatar.png" alt="..." class="mini-avatar-img">
         </a>
         <div id='${'${'}taskDeskId}' class='task-done'>{{html text}}</div>

         <label>
             <input type="checkbox" id="${'${'}btnCheckTaskId}" class="tasks-list-cb" checked>
             <span id="${'${'}btnCheckTaskId}" class="tasks-list-mark"></span>
         </label>

        <span id='${'${'}btnRemoveTaskId}' class='glyphicon glyphicon-remove' data-toggle='tooltip' data-placement='top' title='Delete task'></span>
    </li>



</script>

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