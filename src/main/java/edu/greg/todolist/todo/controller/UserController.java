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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 05.07.15.
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserServices userService;

    @Autowired
    private TaskServices taskServices;

//    @ModelAttribute("user")
//    public User constructBlog() {
//        return new User();
//    }

    @RequestMapping("/account")
    public ModelAndView account(Model model,HttpServletRequest request,HttpServletResponse response) throws TaskNotFoundException {
//        if (true) throw new TaskNotFoundException();
//        model.addAttribute("user", userService.findOne("GreG"));
//        log.debug("Rendering account page.");
//        return "account";
        throw new TaskNotFoundException();
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
    public TaskDto add(@Valid @RequestBody TaskDto dto) {
        log.debug("Adding a new to-do entry with information: {}", dto);

        Task added = taskServices.save(dto, "GreG");

        log.debug("Added a to-do entry with information: {}", added);

        return createTaskDto(added);
    }

    @RequestMapping(value = "/api/todo/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public TaskDto deleteById(@PathVariable Integer id) throws TaskNotFoundException {
        log.debug("Deleting a task entry with id: {}", id);
        Task deleted = taskServices.deleteById(id);
        log.debug("Deleted task : {}", deleted);
        return createTaskDto(deleted);
    }


//    private UserDto createUserDto(User user) {
//        UserDto userDto = new UserDto();
//
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setTaskDtoList(createTaskListDtoFromUser(user));
//
//        return userDto;
//    }

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
