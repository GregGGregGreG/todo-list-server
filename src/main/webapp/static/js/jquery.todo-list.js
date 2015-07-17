(function ($) {
    var oneRowHeightTextArea = 34;
    var ENTER_KEY = 13;
    var monthNames = [
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    ];
    var removeFirstSpace = /^[ \t]*/gm;
    var removeDuplicateSpace = /[ \t]{2,}/gm;
    var removeBlankRows = /^\n{3,}/gm;
    var util = TodoListUtilService();

    function TodoListUtilService() {
        return {
            formatText: formatText,
            getDate: getDate,
            switchValueTask: switchValueTask
        };
        /**
         *Format text task.
         * Remove first and last space.
         * Remove duplicate space.
         * And replace <br>\n to \n
         *
         * @param str
         * @returns {string}
         */
        function formatText(text) {
            return text
                .trim()
                .replace(removeFirstSpace, '')
                .replace(removeDuplicateSpace, ' ')
                .replace(removeBlankRows, '\n')
                .replace(/\n/gm, "<br />\n");
        }

        /**
         * Create date creating task
         * in format:
         * 18 May 2015 | 15:00:59
         * @param date
         * @returns {string}
         */
        function getDate(date) {
            return date.getDate() + ' ' +
                monthNames[date.getMonth()] + ' ' +
                    //date.getMonth() + ' ' +
                date.getFullYear() + ' | ' +
                date.toLocaleTimeString();
        }

        function switchValueTask(from, to) {
            var str = !from.val() ? from.text() : formatText(from.val());
            //Use switch where value task from hover to static task
            if (!from.val()) {
                //to.val(str);
                to.val(str).height(from.height());
            } else {
                //Use switch where value task for save edit task
                to.html(str);
                //
                from.val(to.text()).height(to.height());
            }
        }
    }

    var defaults = {
        liId: '',
        inputId: 'input',
        btnAddId: 'btn-add',
        todoListId: 'task-list',
        dateAddingTaskId: 'date',
        taskId: 'task',
        textAreaEditTaskId: 'edit-task',
        btnDoneTaskId: 'btn-done',
        btnRemoveTaskId: 'btn-remove',
        btnUpdateTaskId: 'btn-update',
        messageUserId: 'message-user',
        modalWindowDestroyTaskId: '',
        modalWindowDestroyButtonDestroyId: '',

        inputGroupTemplate: "<div >" +
        "<textarea id='${inputId}'></textarea>" +
        "<button id='${btnAddId}'>Add</button></div>" +
        "<h3 id='${messageUserId}'>You Todo - list is empty! Please add task.</h3>" +
        "<ul id='${todoListId}'></ul>",

        taskTemplate: "<li id='${liId}'>" +
        "<div id='${taskId}'>{{html text}}</div>" +
        "<textarea id='${textAreaEditTaskId}' ></textarea>" +
        "<input id='${btnDoneTaskId}'  type='checkbox'/>" +
        "<button id='${btnUpdateTaskId}'>" + "<span class='glyphicon glyphicon-edit'></span>" + "</button>" +
        "<button id='${btnRemoveTaskId}' >Remove</button>" +
        "<span id='${dateAddingTaskId}'>${publishedDate}</span>" +
        "</li>",

        taskDoneTemplate: "<li id='${liId}' class='${liTodoClass}'>" +
        "<div id='${taskId}' >{{html text}}</div>" +
        "<input id='${btnDoneTaskId}' type='checkbox' checked/>" +
        "<span id='${btnRemoveTaskId}'></sp>" +
        "</li>"
    };

    function TodoList(element, options) {
        this.config = $.extend({}, defaults, options);
        this.element = element;
        this.init();
        this.bindEvents();
        this.showMessage();
    }

    //Init paramet todolist
    TodoList.prototype.init = init;
    //Set events on the buttons
    TodoList.prototype.bindEvents = bindEvents;
    //Create template for new task
    TodoList.prototype.createTaskHtml = createTaskHtml;
    //Create template from checkTask task
    TodoList.prototype.createDoneTaskHtml = createDoneTaskHtml;
    //
    TodoList.prototype.inputKeyUp = inputKeyUp;
    TodoList.prototype.updateKeyUp = updateKeyUp;

    //TodoList.prototype.add = add;
    //Add new task in todolist
    TodoList.prototype.add = add;
    TodoList.prototype.addNewTask = addNewTask;

    TodoList.prototype.checkTask = checkTask;
    TodoList.prototype.performTask = performTask;
    TodoList.prototype.unPerformTask = unPerformTask;


    TodoList.prototype.update = update;

    TodoList.prototype.destroy = destroy;

    TodoList.prototype.toggle = toggle;

    TodoList.prototype.showMessage = showMessage;
    TodoList.prototype.todoIsEmpty = todoIsEmpty;
    TodoList.prototype.getCurrentStateTask = getCurrentStateTask;

    TodoList.prototype.getAllTasks = getAllTasks;
    TodoList.prototype.updateTask = updateTask;
    TodoList.prototype.deleteTask = deleteTask;

    TodoList.prototype.newTodoResource = newTodoResource;

    /**
     * Create input group: input and button for adding task, empty task-list.
     */
    function init() {

        $.template("inputTemplate", this.config.inputGroupTemplate);
        $.tmpl("inputTemplate", this.config).appendTo(this.element);

        this.$messageUser = $('#' + this.config.messageUserId).hide();

        //Set focus input
        this.$input = $('#' + this.config.inputId).focus();
        //Set aotosize input field
        autosize($('#' + this.config.inputId));

        this.$btnAdd = $('#' + this.config.btnAddId).prop('disabled', true);
        this.$todoList = $('#' + this.config.todoListId);
        this.newTodoResource({
            type: 'GET',
            success: this.getAllTasks.bind(this)
        })
    }


    /**
     * Events for click buttons: Add task, Destroy task, Update task, Done task and hover for task.
     */
    function bindEvents() {
        this.$btnAdd.on('click', this.add.bind(this));
        this.$input.keyup(this.inputKeyUp.bind(this));
        this.$todoList
            .delegate('#' + this.config.textAreaEditTaskId, 'keyup', this.updateKeyUp.bind(this))
            .delegate('#' + this.config.btnDoneTaskId, 'click', this.checkTask.bind(this))
            .delegate('#' + this.config.btnUpdateTaskId, 'click', this.update.bind(this))
            .delegate('#' + this.config.btnRemoveTaskId, 'click', this.destroy.bind(this))
            .delegate('li', 'mouseenter mouseleave', this.toggle.bind(this));
    }

    /**
     * Add to todo list new task in first place with autosize contain
     * @param str
     */
    function add(str) {
        var text = ($.type(str) === 'string') ? str : this.$input.val();

        var task = {
            text: text
        };

        var query = {
            type: 'POST',
            data: task,
            success: this.addNewTask.bind(this)
        };

        this.newTodoResource(query);
    }

    function addNewTask(task) {
        console.log(JSON.stringify(task));
        this.createTaskHtml(task).prependTo(this.$todoList);

        $('[data-toggle="tooltip"]').tooltip({delay: {"show": 500, "hide": 100}, container: 'body'});

        autosize($('#' + this.config.textAreaEditTaskId));

        this.$input.val('');
        this.$input.css('height', oneRowHeightTextArea);
        this.$btnAdd.prop('disabled', true);
        this.showMessage();
    }

    /**
     * The task gets checkTask. It becomes the end of the list. Remove buttons
     * editing and creation date
     *
     * Set task checkTask.
     */
    function checkTask() {
        var state = this.getCurrentStateTask();

        var dto = {
            id: state.$li.attr('id')
        };

        dto.isExecuted = state.$btnDoneTask.is(":checked");

        var query = {
            type: 'PUT',
            data: dto
        };

        query.success = dto.isExecuted ?
            this.performTask.bind(this)
            : this.unPerformTask.bind(this);

        this.newTodoResource(query);
    }

    /**
     * Set checkTask select task and by adding it to the todo-list end.
     *
     * @param str
     */
    function performTask(task) {

        this.$todoList.find("#" + task.id).remove();

        this.createDoneTaskHtml(task).appendTo(this.$todoList);

        this.$input.focus();
    }

    function unPerformTask(task) {

        this.$todoList.find("#" + task.id).remove();

        this.createTaskHtml(task).prependTo(this.$todoList);

        autosize($('#' + this.config.textAreaEditTaskId));

        this.$input.focus();
    }

    /**
     *
     * Update text task
     */
    function update() {
        var state = this.getCurrentStateTask();

        var dto = {
            id: state.$li.attr('id'),
            text: state.$editTask.val()
        };

        this.updateTask(dto, state);

        //util.switchValueTask(state.$editTask, state.$task);
        state.$btnUpdateTask.prop('disabled', true);
    }

    /**
     * Show modal window.Focus in delete button
     * Remove select task with slide toogle from todo-list.
     * Show message from user if is empty todo-list
     * Destroy task.
     * Hide modal window
     */
    function destroy() {
        var thisObj = this;
        var modalWindow = '#' + this.config.modalWindowDestroyTaskId;
        var buttonDelete = '#' + this.config.modalWindowDestroyButtonDestroyId;

        var $id = $(event.target).closest('li').attr("id");

        var query = {
            type: 'DELETE',
            url: "http://localhost:8080/api/todo/" + $id + ".html",
            success: this.deleteTask.bind(this)
        };

        //Show modal window
        $(modalWindow)
            .modal('show')
            .on('shown.bs.modal', function () {
                var modal = $(this);
                modal.find(buttonDelete).focus();
            })
            .one('click', buttonDelete, function () {
                thisObj.newTodoResource(query);
            });
    }

    /**
     * Event for text area set task when editing.
     * If you hover over the text of the content is copied to the task tekst area and becomes editable
     */
    function updateKeyUp(e) {
        var state = this.getCurrentStateTask();

        var currentText = state.$task.text();
        var inputText = state.$editTask.val().trim();
        // disabled update button
        state.$btnUpdateTask.prop('disabled', true);

        if (inputText === '') {
            state.$btnUpdateTask.prop('disabled', true);
            return;
        }

        if (currentText != inputText) {
            state.$btnUpdateTask.prop('disabled', false);
        }

        if (e.ctrlKey && e.keyCode === ENTER_KEY) {
            this.update();
        }
    }

    /**
     * Event for input text field.
     * Create new task if user click ctr+enter.
     * @param e
     */
    function inputKeyUp(e) {
        if (!$(event.target).val().trim()) {
            this.$btnAdd.prop('disabled', true);
        } else if (e.ctrlKey && e.keyCode === ENTER_KEY) {
            this.add($(event.target).val());
            this.$btnAdd.prop('disabled', true);
        } else {
            this.$btnAdd.prop('disabled', false);
        }
    }

    /**
     * Create li with task , button checkTask, update ,and destroy.
     * Adding for date creating.
     * @param str
     * @returns  {html element li}
     */
    function createTaskHtml(task) {
        this.config.text = util.formatText(task.text);
        this.config.publishedDate = task.publishedDate;
        this.config.liId = task.id;
        $.template("taskTemplate", this.config.taskTemplate);
        return $.tmpl("taskTemplate", this.config);
    }

    /**
     * Create li with task,button destroy and checkTask.
     * @param str
     * @returns {html element li}
     */
    function createDoneTaskHtml(task) {
        this.config.text = util.formatText(task.text);
        this.config.liId = task.id;
        $.template("taskDoneTemplate", this.config.taskDoneTemplate);
        return $.tmpl("taskDoneTemplate", this.config);
    }

    function toggle(event) {
        var state = this.getCurrentStateTask();
        var eventType = event.type;

        if (state.$btnDoneTask.is(":checked")) {
            state.$btnRemoveTask.toggle(eventType === 'mouseenter');
            state.$btnDoneTask.toggle(eventType === 'mouseenter');
        } else {
            util.switchValueTask(state.$task, state.$editTask);

            state.$editTask.toggle(eventType === 'mouseenter');
            state.$task.toggle(eventType === 'mouseleave');

            state.$btnUpdateTask.prop('disabled', true);

            state.$btnRemoveTask.toggle(eventType === 'mouseenter');
            state.$btnDoneTask.toggle(eventType === 'mouseenter');
            state.$btnUpdateTask.toggle(eventType === 'mouseenter');
        }
    }

    /**
     * Get current state task.
     * Find current task,edit task,btn update,remove,checkTask.
     */
    function getCurrentStateTask() {
        return {
            $li: $(event.target).closest('li'),
            $task: $(event.target).closest('li').find('#' + this.config.taskId),
            $editTask: $(event.target).closest('li').find('#' + this.config.textAreaEditTaskId),
            $btnUpdateTask: $(event.target).closest('li').find('#' + this.config.btnUpdateTaskId),
            $btnDoneTask: $(event.target).closest('li').find('#' + this.config.btnDoneTaskId),
            $btnRemoveTask: $(event.target).closest('li').find('#' + this.config.btnRemoveTaskId)
        };
    }

    /**
     * Show message for users about that todo-list is Empty!
     */
    function showMessage() {
        this.$input.focus();
        this.todoIsEmpty() ? this.$messageUser.slideDown(600) : this.$messageUser.hide();
    }

    /**
     * Return true if todo-list is empty.If list doesn't empty return false.
     * @returns {boolean}
     */
    function todoIsEmpty() {
        return this.$todoList.children().length === 0;
    }

    function getAllTasks(taskList) {
        var thisObj = this;
        console.log(taskList);
        $.each(taskList, function (i, task) {

            if (!task.isExecuted) {
                thisObj.createTaskHtml(task).prependTo(thisObj.$todoList);

                autosize($('#' + thisObj.config.textAreaEditTaskId));
            } else {
                thisObj.createDoneTaskHtml(task).appendTo(thisObj.$todoList);
            }
        });
        this.showMessage();
    }

    //Ajax request update task by id
    function updateTask(dto, state) {
        console.log('updateDto request: ' + JSON.stringify(dto));

        var thisObj = this;
        $.ajax({
            type: 'PUT',
            url: "http://localhost:8080/api/todo.html",
            headers: {
                'Content-Type': 'application/json'
            },
            dataType: "json",
            data: JSON.stringify(dto),
            success: function (update) {

                console.log('updateDto response:' + JSON.stringify(update));

                state.$editTask.val(update.text);

                util.switchValueTask(state.$editTask, state.$task);
            },
            error: function () {
                console.log("Error update task!")
            }
        });
    }


    function deleteTask(task) {
        var thisObj = this;

        console.log(JSON.stringify(task));

        //var $li = this.$todoList.find("#" + task.id);

        this.$todoList.find("#" + task.id)
            .slideToggle(300, function () {
                $(this).remove();
                thisObj.showMessage();
            });


        //this.showMessage();

    }

    // New ajax request from api
    function newTodoResource(options) {
        $.ajax({
            type: options.type,
            url: options.url || "http://localhost:8080/api/todo.html",
            headers: {
                'Content-Type': 'application/json'
            },
            dataType: "json",
            data: JSON.stringify(options.data),
            success: options.success,
            error: function (jqXHR) {
                var message = $("<div>")
                    .message({
                        type: "error",
                        message: jqXHR.responseJSON.detail
                    });
                $('main').prepend(message);

                console.log(jqXHR.responseText);
            }
        });
    }

    $.fn.todoList = function (options) {
        var element = this.first();
        new TodoList(element, options);
        return element;
    }
}(jQuery));