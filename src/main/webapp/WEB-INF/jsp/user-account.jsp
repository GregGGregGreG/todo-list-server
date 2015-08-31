<%@include file="../layout/taglib.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-3 account-wall ">

            <a href="#" class="thumbnail">
                <img src="../../static/images/avatar.png" alt="...">
            </a>

            <div  class="td-subtitle">Name: <span id='user-name'>${user.name}</span> </div>

            <div class="td-subtitle">Join date: ${user.createdDate}</div>

            <div class="list-group">
                <a id="show-all-assigned-task" href="#" class="list-group-item"><span class="badge">0</span> Assigned
                    task</a>
                <a id="show-all-task" href="#" class="list-group-item"><span class="badge">0</span>All task</a>
            </div>
        </div>
        <div class="col-md-7">
            <div class="panel panel-default">
                <div class="panel-heading">Panel heading without title</div>
                <div id="tasks" class='list-group' ></div>
            </div>
        </div>
    </div>
</div>
