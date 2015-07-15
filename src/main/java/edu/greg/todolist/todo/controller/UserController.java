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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 05.07.15.
 */
@Slf4j
@Controller
public class UserController {

    protected static final String ACCOUNT_VIEW = "account";


    @Autowired
    private UserServices userService;

    @Resource
    private TaskServices taskServices;

//    @ModelAttribute("user")
//    public User constructBlog() {
//        return new User();
//    }

    @RequestMapping("/account")
    public String account(Model model) {
        log.debug("Rendering account page.");
        model.addAttribute("user", userService.findOne("GreG"));
        return ACCOUNT_VIEW;
    }

    @RequestMapping(value = "/api/todo", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDto> findById() {
        log.debug("Finding to-do entry with id: {}");

        User user = userService.findOne("GreG");
        List<TaskDto> taskDtoList = createTaskListDtoFromUser(user);

        return taskDtoList;
    }

    @RequestMapping(value = "/api/todo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TaskDto add(@RequestBody TaskDto dto) {
        log.debug("Adding a new to-do entry with information: {}", dto);

        Task added = taskServices.add(dto, "GreG");

        log.debug("Added a to-do entry with information: {}", added);

        return createTaskDto(added);
    }

    @RequestMapping(value = "/api/todo/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public TaskDto update(@RequestBody TaskDto dto, @PathVariable("id") Long todoId) throws TaskNotFoundException {
        log.debug("Updating a model entry with information: {}", dto);

//        validate(OBJECT_NAME_TODO, dto);

        Task updated = taskServices.update(dto);
        log.debug("Updated the information of a model to: {}", updated);

        return createTaskDto(updated);
    }

    @RequestMapping(value = "/api/todo/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public TaskDto deleteById(@PathVariable Integer id) throws TaskNotFoundException {
        log.debug("Deleting a model entry with id: {}", id);
        Task deleted = taskServices.deleteById(id);
        log.debug("Deleted model : {}", deleted);
        return createTaskDto(deleted);
    }

    private List<TaskDto> createTaskListDtoFromUser(User user) {
        List<Task> taskList = user.getTasks();
        List<TaskDto> taskDtoList = new ArrayList<>();

        for (Task task : taskList) {
            taskDtoList.add(createTaskDto(task));
        }

        return taskDtoList;
    }

    private TaskDto createTaskDto(Task model) {
        TaskDto dto = new TaskDto();

        dto.setId(model.getId());
        dto.setDescription(model.getDescription());
        dto.setPublishedDate(model.getPublishedDate());

        return dto;
    }
}
