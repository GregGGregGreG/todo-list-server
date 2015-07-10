package edu.greg.todolist.todo.controller;


import edu.greg.todolist.todo.persistence.dto.TaskDto;
import edu.greg.todolist.todo.persistence.dto.UserDto;
import edu.greg.todolist.todo.persistence.entity.Task;
import edu.greg.todolist.todo.persistence.entity.User;
import edu.greg.todolist.todo.persistence.service.TaskServices;
import edu.greg.todolist.todo.persistence.service.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 05.07.15.
 */
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices userService;

    @Autowired
    private TaskServices taskServices;

//    @ModelAttribute("user")
//    public User constructBlog() {
//        return new User();
//    }

    @RequestMapping("/account")
    public String account(Model model) {
        model.addAttribute("user", userService.findOne("GreG"));
        LOGGER.debug("Rendering account page.");
        return "account";
    }

    @RequestMapping(value = "/api/todo", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskDto> findById() {
        LOGGER.debug("Finding to-do entry with id: {}");

        User user = userService.findOne("GreG");
        List<TaskDto> taskDtoList = createTaskListDtoFromUser(user);

        return taskDtoList;
    }

    @RequestMapping(value = "/api/todo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TaskDto add(@Valid @RequestBody TaskDto dto) {
        LOGGER.debug("Adding a new to-do entry with information: {}", dto);

        Task added = taskServices.save(dto, "GreG");

        LOGGER.debug("Added a to-do entry with information: {}", added);

        return createTaskDto(added);
    }

    @RequestMapping(value = "/api/todo/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public TaskDto deleteById(@PathVariable Integer id) {
        LOGGER.debug("Deleting a task entry with id: {}", id);
        Task deleted = taskServices.deleteById(id);
        LOGGER.debug("Deleted task : {}", deleted);
        return createTaskDto(deleted);
    }


    private UserDto createUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setTaskDtoList(createTaskListDtoFromUser(user));

        return userDto;
    }

    private List<TaskDto> createTaskListDtoFromUser(User user) {
        List<Task> taskList = user.getTasks();
        List<TaskDto> taskDtoList = new ArrayList<>();

        for (Task task : taskList) {
            taskDtoList.add(createTaskDto(task));
        }

        return taskDtoList;
    }

    private TaskDto createTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();

        taskDto.setId(task.getId());
        taskDto.setDescription(task.getDescription());
        taskDto.setPublishedDate(task.getPublishedDate());

        return taskDto;
    }
}
