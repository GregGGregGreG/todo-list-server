(function ($) {
    var minHeightOneRowInput = 32;
    var ENTER_KEY = 13;
    var removeFirstSpace = /^[ \t]*/gm;
    var removeDuplicateSpace = /[ \t]{2,}/gm;
    var removeBlankRows = /^\n{3,}/gm;
    var util = TodoListUtilService();

    function TodoListUtilService() {
        return {
            formatText: formatText,
            switchValueTask: switchValueTask
        };
        /**
         *Format text task.
         * Remove first and last space.
         * Remove duplicate space.
         * And replace <br>\n to \n
         *
         * @param text
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

    var DEFAULTS = {
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

        templateInputGroup: "<div >" +
        "<textarea id='${inputId}'></textarea>" +
        "<button id='${btnAddId}'>Add</button></div>" +
        "<h3 id='${messageUserId}' style='display: none;'>You Todo - list is empty! Please add task.</h3>" +
        "<ul id='${todoListId}'></ul>",

        templateTask: "<li id='${liId}'>" +
        "<div id='${taskId}'>{{html text}}</div>" +
        "<textarea id='${textAreaEditTaskId}' ></textarea>" +
        "<input id='${btnDoneTaskId}'  type='checkbox'/>" +
        "<button id='${btnUpdateTaskId}'>" + "<span class='glyphicon glyphicon-edit'></span>" + "</button>" +
        "<button id='${btnRemoveTaskId}' >Remove</button>" +
        "<span id='${dateAddingTaskId}'>${publishedDate}</span>" +
        "</li>",

        templateCompletedTasks: "<li id='${liId}' class='${liTodoClass}'>" +
        "<div id='${taskId}' >{{html text}}</div>" +
        "<input id='${btnDoneTaskId}' type='checkbox' checked/>" +
        "<span id='${btnRemoveTaskId}'></sp>" +
        "</li>"
    };

    function TodoList(element, options) {
        this.config = $.extend({}, DEFAULTS, options);
        this.element = element;
        this.init();
        this.bindEvents();
    }

    TodoList.prototype.init = init;
    TodoList.prototype.bindEvents = bindEvents;

    TodoList.prototype.inputKeyUp = inputKeyUp;
    TodoList.prototype.updateKeyUp = updateKeyUp;

    TodoList.prototype.add = add;
    TodoList.prototype.checkTask = checkTask;
    TodoList.prototype.update = update;
    TodoList.prototype.destroy = destroy;

    TodoList.prototype.toggle = toggle;

    TodoList.prototype.addTaskList = addTaskList;
    TodoList.prototype.addNewTask = addNewTask;
    TodoList.prototype.performTask = performTask;
    TodoList.prototype.unPerformTask = unPerformTask;
    TodoList.prototype.updateTask = updateTask;
    TodoList.prototype.destroyTask = destroyTask;

    TodoList.prototype.getTemplateTask = getTemplateTask;
    TodoList.prototype.getTemplateCompletedTask = getTemplateCompletedTask;

    TodoList.prototype.getTodoList = getTodoList;
    TodoList.prototype.getInput = getInput;
    TodoList.prototype.getBtnAdd = getBtnAdd;
    TodoList.prototype.getMessageUser = getMessageUser;
    TodoList.prototype.showMessage = showMessage;
    TodoList.prototype.todoIsEmpty = todoIsEmpty;
    TodoList.prototype.getCurrentStateTask = getCurrentStateTask;

    TodoList.prototype.newTodoResource = newTodoResource;

    /**
     * Create input group: input and button for adding task, empty task-list.
     */
    function init() {
        $.template("inputGroup", this.config.templateInputGroup);
        $.tmpl("inputGroup", this.config).appendTo(this.element);
        //Disabled add button
        this.getBtnAdd().prop('disabled', true);
        //Set aotosize input field and focus
        autosize(this.getInput().focus());
        //Create request for filling tasks
        var query = {
            type: 'GET',
            success: this.addTaskList.bind(this)
        };
        //Send new request
        this.newTodoResource(query);
    }

    /**
     * Events for click buttons: Add task, Destroy task, Update task, Done task and hover for task and input.
     */
    function bindEvents() {
        this.getBtnAdd().on('click', this.add.bind(this));
        this.getInput().keyup(this.inputKeyUp.bind(this));
        this.getTodoList()
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
        var text = ($.type(str) === 'string') ? str : this.getInput().val();
        var dto = {text: text};
        var query = {
            type: 'POST',
            data: dto,
            success: this.addNewTask.bind(this)
        };
        this.newTodoResource(query);
    }

    /**
     * Update text task
     */
    function update() {
        var state = this.getCurrentStateTask();
        var dto = {
            id: state.$li.attr('id'),
            text: state.$editTask.val()
        };
        var query = {
            type: 'PUT',
            data: dto,
            success: this.updateTask.bind(this, state)
        };
        this.newTodoResource(query);
    }

    /**
     * Show modal window.Focus in delete button
     * Remove select task with slide toogle from todo-list.
     * Show message from user if is empty todo-list
     * Destroy task.
     * Hide modal window
     */
    function destroy() {
        var vm = this;
        var modalWindow = '#' + this.config.modalWindowDestroyTaskId;
        var buttonDelete = '#' + this.config.modalWindowDestroyButtonDestroyId;
        //Get current task id
        var $id = $(event.target).closest('li').attr("id");
        //Create query object
        var query = {
            type: 'DELETE',
            url: "http://localhost:8080/api/todo/" + $id,
            success: this.destroyTask.bind(this)
        };
        //Show modal window
        $(modalWindow)
            .modal('show')
            .on('shown.bs.modal', function () {
                var modal = $(this);
                modal.find(buttonDelete).focus();
            })
            .one('click', buttonDelete, function () {
                vm.newTodoResource(query);
            });
    }

    /**
     * Check status task.
     * This function get current state task and send request server.
     * If task is perform set task unPerform.
     * If task unPerform set task perform.
     */
    function checkTask() {
        //Get current state task
        var state = this.getCurrentStateTask();
        //Create dto task
        var dto = {
            id: state.$li.attr('id')
        };
        //Get status task
        dto.isExecuted = state.$btnDoneTask.is(":checked");
        //Create query object
        var query = {
            type: 'PUT',
            data: dto
        };
        //Select handler task
        query.success = dto.isExecuted ?
            this.performTask.bind(this)
            : this.unPerformTask.bind(this);
        //Send request from server
        this.newTodoResource(query);
    }

    /**
     * Event for textarea element current task when editing.
     * Do not save empty task
     * Create new task if user push ctr+enter.
     */
    function updateKeyUp(e) {
        var state = this.getCurrentStateTask();

        var currentText = state.$task.text();
        var updateText = state.$editTask.val().trim();
        //Disabled button update
        state.$btnUpdateTask.prop('disabled', true);
        //Do not save empty task
        if (updateText === '') return;
        //Update text is valid
        if (currentText != updateText) {
            state.$btnUpdateTask.prop('disabled', false);
        }
        //Create new task if user push ctr+enter.
        if (e.ctrlKey && e.keyCode === ENTER_KEY) {
            this.update();
        }
    }

    /**
     * Event for input text field.
     * Create new task if user push ctr+enter.
     * @param e
     */
    function inputKeyUp(e) {
        if (!$(event.target).val().trim()) {
            this.getBtnAdd().prop('disabled', true);
        } else if (e.ctrlKey && e.keyCode === ENTER_KEY) {
            this.add($(event.target).val());
        } else {
            this.getBtnAdd().prop('disabled', false);
        }
    }

    /**
     * If you hover over the text of the content is copied to the task tekst area and becomes editable.
     * If task unperform show button done, update, remove. Don not active button update.
     * If task perform shown button done and remove.
     * @param event
     */
    function toggle(event) {
        var state = this.getCurrentStateTask();
        var eventType = event.type;

        if (state.$btnDoneTask.is(":checked")) {
            //Show button remove and done
            state.$btnRemoveTask.toggle(eventType === 'mouseenter');
            state.$btnDoneTask.toggle(eventType === 'mouseenter');
        } else {
            util.switchValueTask(state.$task, state.$editTask);
            //Show textarea element
            state.$editTask.toggle(eventType === 'mouseenter');
            //Hide div element
            state.$task.toggle(eventType === 'mouseleave');
            //Disabled update button
            state.$btnUpdateTask.prop('disabled', true);
            //Show button done, update, remove
            state.$btnDoneTask.toggle(eventType === 'mouseenter');
            state.$btnUpdateTask.toggle(eventType === 'mouseenter');
            state.$btnRemoveTask.toggle(eventType === 'mouseenter');
        }
    }

    function addTaskList(taskList) {
        var vm = this;
        $.each(taskList, function (i, task) {
            if (task.isExecuted) {
                vm.getTemplateCompletedTask(task).appendTo(vm.getTodoList());
            } else {
                vm.getTemplateTask(task).prependTo(vm.getTodoList());
                autosize($('#' + vm.config.textAreaEditTaskId));
            }
        });
        this.showMessage();
    }

    /**
     * Adding new task in todo-list
     * @param task
     */
    function addNewTask(task) {
        //Adding task in todo-list
        this.getTemplateTask(task).prependTo(this.getTodoList());
        //Set autosize text area edit task
        autosize($('#' + this.config.textAreaEditTaskId));
        this.getInput()
            //Set empty input field
            .val('')
            //Set default height input field
            .css('height', minHeightOneRowInput);
        //Disabled button add
        this.getBtnAdd().prop('disabled', true);
        //Show message from user
        this.showMessage();
    }

    /**
     * Destroy task
     * @param task
     */
    function destroyTask(task) {
        var vm = this;
        var $li = this.getTodoList().find("#" + task.id);

        $li.slideToggle(300, function () {
            $(this).remove();
            vm.showMessage();
        })
    }

    function updateTask(state, task) {
        state.$editTask.val(task.text);

        util.switchValueTask(state.$editTask, state.$task);

        state.$btnUpdateTask.prop('disabled', true);
    }

    /**
     * Set perform task and by adding it to the todo-list end.
     * @param {task dto}
     */
    function performTask(task) {

        this.getTodoList().find("#" + task.id).remove();

        this.getTemplateCompletedTask(task).appendTo(this.getTodoList());

    }

    function unPerformTask(task) {

        this.getTodoList().find("#" + task.id).remove();

        this.getTemplateTask(task).prependTo(this.getTodoList());

        autosize($('#' + this.config.textAreaEditTaskId));
    }

    /**
     * Create li with task , button checkTask, update ,and destroy.
     * @param str
     * @returns  {html element li}
     */
    function getTemplateTask(task) {
        this.config.text = util.formatText(task.text);
        this.config.publishedDate = task.publishedDate;
        this.config.liId = task.id;
        $.template("task", this.config.templateTask);
        return $.tmpl("task", this.config);
    }

    /**
     * Create li with task,button destroy and checkTask.
     * @param str
     * @returns {html element li}
     */
    function getTemplateCompletedTask(task) {
        this.config.text = util.formatText(task.text);
        this.config.liId = task.id;
        $.template("completedTasks", this.config.templateCompletedTasks);
        return $.tmpl("completedTasks", this.config);
    }

    /**
     * Find todolist object list object and return jquery element
     * @returns {*|HTMLElement}
     */
    function getTodoList() {
        return $('#' + this.config.todoListId);
    }

    /**
     * Find input object and return jquery element
     * @returns {*|HTMLElement}
     */
    function getInput() {
        return $('#' + this.config.inputId);
    }

    /**
     * Find btnAdd object and return jquery element
     * @returns {*|HTMLElement}
     */
    function getBtnAdd() {
        return $('#' + this.config.btnAddId);
    }

    /**
     * Find message from user and return jquery element
     * @returns {*|HTMLElement}
     */
    function getMessageUser() {
        return $('#' + this.config.messageUserId);
    }

    /**
     * Get current state task.
     * Find current state task, editTask, btn: update, remove, checkTask.
     * @returns {*|HTMLCollection}
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
     * Set focus input field if todo-list is empty.
     * If todo list is empty show message from user
     */
    function showMessage() {
        if(this.todoIsEmpty()) this.getInput().focus();
        this.todoIsEmpty() ? this.getMessageUser().slideDown(600) : this.getMessageUser().hide();
    }

    /**
     * Return true if todo-list is empty.If list doesn't empty return false.
     * @returns {boolean}
     */
    function todoIsEmpty() {
        return this.getTodoList().children().length === 0;
    }

    /**
     * Handler ajax request
     * @param options
     */
    function newTodoResource(options) {

        $.ajax({
            type: options.type,
            url: options.url || "http://localhost:8080/api/todo",
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
                        message: jqXHR.responseJSON == undefined
                            ? 'Error Server '
                            : jqXHR.responseJSON.detail
                    });
                $('main').prepend(message);
            }
        });
    }

    $.fn.todoList = function (options) {
        var element = this.first();
        new TodoList(element, options);
        return element;
    }
}(jQuery));
