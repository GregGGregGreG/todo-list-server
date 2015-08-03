package edu.greg.todolist.todo.controller;

import edu.greg.todolist.todo.persistence.dto.TaskDto;
import edu.greg.todolist.todo.persistence.exception.TaskNotFoundException;
import edu.greg.todolist.todo.persistence.model.Task;
import edu.greg.todolist.todo.persistence.service.TaskServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/api/todo", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDto> findAllTaskByEmail(Principal principal) {
        String email = principal.getName();
        log.debug("Finding task list entry with user email: {}", email);

        List<Task> models = taskServices.findAllByUserEmail(email);

        log.debug("Found task list entry :{} ", models);

        return createDtoTaskList(models);
    }

    @RequestMapping(value = "/api/todo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TaskDto add(@RequestBody TaskDto dto, Principal principal) throws IllegalAccessException {
        String email = principal.getName();
        log.debug("Finding user entry with email {}", email);

        log.debug("Adding a new task entry with information: {}", dto);

        Task added = taskServices.add(dto, email);

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

    private TaskDto createDtoTask(Task model) {
        TaskDto dto = new TaskDto();

        dto.setId(model.getId());
        dto.setText(model.getText());
        dto.setPublishedDate(model.getPublishedDate());
        dto.setIsExecuted(model.getIsExecuted());

        return dto;
    }

}
