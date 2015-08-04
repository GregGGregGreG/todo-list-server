package edu.greg.todolist.todo.controller;

import edu.greg.todolist.todo.persistence.dto.TaskDto;
import edu.greg.todolist.todo.persistence.exception.TaskNotFoundException;
import edu.greg.todolist.todo.persistence.model.Task;
import edu.greg.todolist.todo.persistence.model.User;
import edu.greg.todolist.todo.persistence.service.TaskServices;
import edu.greg.todolist.todo.persistence.service.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 31.07.15.
 */
@Slf4j
@Controller
public class TodoAPI {

    @Resource
    private TaskServices taskServices;

    @Autowired
    private UserServices userService;

    @RequestMapping(value = "/api/todo", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDto> findAllTaskByEmail(Principal principal) {
        User found = getCurrentUser();
        log.debug("Finding task list entry with user: {}", found);

        List<Task> models = taskServices.findAllByUser(found);

        log.debug("Found task list entry :{} ", models);

        return createDtoTaskList(models);
    }

    @RequestMapping(value = "/api/todo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TaskDto add(@RequestBody TaskDto dto, Principal principal) throws IllegalAccessException {
        User user = getCurrentUser();
        log.debug("Finding user entry {}", user);

        log.debug("Adding a new task entry with information: {}", dto);

        Task added = taskServices.addTaskToUser(dto, user);

        log.debug("Added a to-do user with information: {}", added);

        return createDtoTask(added);
    }

    @RequestMapping(value = "/api/todo", method = RequestMethod.PUT)
    @ResponseBody
    public TaskDto update(@RequestBody TaskDto dto) throws TaskNotFoundException {
        log.debug("Updating a model entry with information: {}", dto);

//        validate(OBJECT_NAME_TODO, dto);

        Task updated = taskServices.update(dto);
        log.debug("Updated the information of a model to: {}", updated);

        return createDtoTask(updated);
    }

    @RequestMapping(value = "/api/todo/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public TaskDto deleteById(@PathVariable Integer id) throws TaskNotFoundException {
        log.debug("Deleting a model entry with id: {}", id);
        Task deleted = taskServices.deleteById(id);
        log.debug("Deleted model : {}", deleted);
        return createDtoTask(deleted);
    }

    private List<TaskDto> createDtoTaskList(List<Task> models) {
        List<TaskDto> dtos = new ArrayList<>();

        for (Task task : models) {
            dtos.add(createDtoTask(task));
        }

        return dtos;
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String name = ((UserDetails) principal).getUsername();
            return userService.findByName(name);
        }
        return null;
    }

    private TaskDto createDtoTask(Task model) {
        TaskDto dto = new TaskDto();

        dto.setId(model.getId());
        dto.setText(model.getText());
        dto.setPublishedDate(model.getPublishedDate());
        dto.setIsExecuted(model.getIsExecuted());

        return dto;
    }

}
