package edu.greg.todolist.todo.controller;

import edu.greg.todolist.todo.persistence.dto.TaskDto;
import edu.greg.todolist.todo.persistence.exception.TaskNotFoundException;
import edu.greg.todolist.todo.persistence.model.Task;
import edu.greg.todolist.todo.persistence.model.User;
import edu.greg.todolist.todo.persistence.service.TaskServices;
import edu.greg.todolist.todo.util.TodoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by greg on 31.07.15.
 */
@Slf4j
@Controller
public class TodoAPI {

    @Resource
    private TaskServices taskServices;

    @RequestMapping(value = "/api/todo", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDto> findAllTaskByEmail() {
        User found = TodoUtils.getCurrentUser();
        log.debug("Finding task list entry with user: {}", found);

        List<Task> models = taskServices.findAllByUser(found);

        log.debug("Found task list entry :{} ", models);

        return TodoUtils.createDtoTaskList(models);
    }

    @RequestMapping(value = "/api/todo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TaskDto add(@RequestBody TaskDto dto) throws IllegalAccessException {
        User user = TodoUtils.getCurrentUser();
        log.debug("Finding user entry {}", user);

        log.debug("Adding a new task entry with information: {}", dto);

        Task added = taskServices.addTaskToUser(dto, user);

        log.debug("Added a to-do user with information: {}", added);

        return TodoUtils.createDtoTask(added);
    }

    @RequestMapping(value = "/api/todo", method = RequestMethod.PUT)
    @ResponseBody
    public TaskDto update(@RequestBody TaskDto dto) throws TaskNotFoundException {
        log.debug("Updating a model entry with information: {}", dto);

//        validate(OBJECT_NAME_TODO, dto);

        Task updated = taskServices.update(dto);
        log.debug("Updated the information of a model to: {}", updated);

        return TodoUtils.createDtoTask(updated);
    }

    @RequestMapping(value = "/api/todo/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public TaskDto deleteById(@PathVariable Integer id) throws TaskNotFoundException {
        log.debug("Deleting a model entry with id: {}", id);
        Task deleted = taskServices.deleteById(id);
        log.debug("Deleted model : {}", deleted);
        return TodoUtils.createDtoTask(deleted);
    }

}
