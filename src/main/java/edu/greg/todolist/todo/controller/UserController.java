package edu.greg.todolist.todo.controller;


import edu.greg.todolist.todo.persistence.dto.TaskDto;
import edu.greg.todolist.todo.persistence.exception.TaskNotFoundException;
import edu.greg.todolist.todo.persistence.model.Task;
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
        log.debug("Finding task list entry with user id: {}");

        List<Task> models = taskServices.findAllFromUser(1);


//        User user = userService.findOne("GreG");
//        List<TaskDto> taskDtoList = createTaskListDtoFromUser(user);

        return createDtoTaskList(models);
    }

    @RequestMapping(value = "/api/todo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TaskDto add(@RequestBody TaskDto dto) {
        log.debug("Adding a new to-do entry with information: {}", dto);

        Task added = taskServices.add(dto, "GreG");

        log.debug("Added a to-do entry with information: {}", added);

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
