package edu.greg.spring.todo.controller;

import edu.greg.spring.todo.persistence.dto.TaskDto;
import edu.greg.spring.todo.persistence.dto.UserDto;
import edu.greg.spring.todo.persistence.exception.UserNotFoundException;
import edu.greg.spring.todo.persistence.model.Task;
import edu.greg.spring.todo.persistence.model.User;
import edu.greg.spring.todo.persistence.service.TaskService;
import edu.greg.spring.todo.util.TodoUtils;
import edu.greg.spring.todo.persistence.exception.TaskNotFoundException;
import edu.greg.spring.todo.persistence.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    private static final String PUBLISHED_DATE_FIELD = "publishedDate";
    private static final int MAX_COUNT_FIELD_RETURNED = 50;
    private static final PageRequest filter = new PageRequest(0, MAX_COUNT_FIELD_RETURNED, Sort.Direction.ASC, PUBLISHED_DATE_FIELD);

    @Resource
    private TaskService taskService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/api/todo", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDto> findAllTaskByUser() throws UserNotFoundException {
        User found = TodoUtils.getCurrentUser();

        log.debug("Finding task list entry with user: {}", found);
        List<Task> models = taskService.findAllByUser(found, filter);
        log.debug("Found task list entry :{} ", models);

        return TodoUtils.createDtoTaskList(models);
    }

    @RequestMapping(value = "/api/todo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TaskDto add(@RequestBody TaskDto dto) throws UserNotFoundException {
        User created = TodoUtils.getCurrentUser();
        log.debug("Finding user entry {}", created);

        log.debug("Adding a new task entry with information: {}", dto);
        Task added = taskService.addTaskToUser(dto, created);
        log.debug("Added a to-do user with information: {}", added);

        return TodoUtils.createDtoTask(added);
    }

    @RequestMapping(value = "/api/todo", method = RequestMethod.PUT)
    @ResponseBody
    public TaskDto update(@RequestBody TaskDto dto) throws TaskNotFoundException {
        log.debug("Updating a model entry with information: {}", dto);

//        validate(OBJECT_NAME_TODO, dto);

        Task updated = taskService.update(dto);
        log.debug("Updated the information of a model to: {}", updated);

        return TodoUtils.createDtoTask(updated);
    }

    @RequestMapping(value = "/api/todo/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public TaskDto deleteById(@PathVariable Integer id) throws TaskNotFoundException {
        log.debug("Deleting a model entry with id: {}", id);
        Task deleted = taskService.deleteById(id);
        log.debug("Deleted model : {}", deleted);
        return TodoUtils.createDtoTask(deleted);
    }

    @RequestMapping(value = "/api/all_search", method = RequestMethod.POST)
    @ResponseBody
    public List<UserDto> allSearch(@RequestBody UserDto dto) throws TaskNotFoundException {
        log.debug("Find user begins with: {}", dto.getName());
        List<User> found = userService.finByNameStartingWith(dto.getName());
        log.debug("Found user-list entry: {}", found);
        return TodoUtils.createDtoUserList(found);
    }

    @RequestMapping(value = "/api/todo_assigned", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDto> findAllAssignedTaskByUser() throws TaskNotFoundException {
        User found = TodoUtils.getCurrentUser();

        log.debug("Finding assigned task list entry with user: {}", found);
        List<Task> models = taskService.findAllAssignedByUser(found, filter);
        log.debug("Found assigned task list entry :{} ", models);
        return TodoUtils.createDtoTaskList(models);
    }
}
