package edu.greg.spring.todolist.todo.util;

import edu.greg.spring.todolist.todo.persistence.dto.TaskDto;
import edu.greg.spring.todolist.todo.persistence.dto.UserDto;
import edu.greg.spring.todolist.todo.persistence.exception.UserNotFoundException;
import edu.greg.spring.todolist.todo.persistence.model.Task;
import edu.greg.spring.todolist.todo.persistence.model.User;
import edu.greg.spring.todolist.todo.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 04.08.15.
 */
@Component
public class TodoUtils {

    private TodoUtils() {

    }

    @Autowired
    private UserService userService;

    private static TodoUtils utils;

    @PostConstruct
    public void init() {
        utils = this;
        utils.userService = this.userService;
    }

    public static User getCurrentUser() throws UserNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String name = ((UserDetails) principal).getUsername();
            return utils.userService.findByName(name);
        }
        return null;
    }

    public static UserDto createDtoUser(User model) {
        UserDto dto = new UserDto();

        dto.setName(model.getName());
        dto.setCreatedDate(model.getCreatedDate());

        return dto;
    }

    public static List<UserDto> createDtoUserList(List<User> models) {
        List<UserDto> dtos = new ArrayList<>();

        for (User user : models) {
            dtos.add(createDtoUser(user));
        }

        return dtos;
    }

    public static TaskDto createDtoTask(Task model) {
        TaskDto dto = new TaskDto();

        dto.setId(model.getId());
        dto.setText(model.getText());
        dto.setCreated(model.getCreator().getName());
        dto.setPerformer(model.getPerformer());
        dto.setPublishedDate(model.getPublishedDate());
        dto.setIsExecuted(model.getIsExecuted());

        return dto;
    }

    public static List<TaskDto> createDtoTaskList(List<Task> models) {
        List<TaskDto> dtos = new ArrayList<>();

        for (Task task : models) {
            dtos.add(createDtoTask(task));
        }

        return dtos;
    }
}
