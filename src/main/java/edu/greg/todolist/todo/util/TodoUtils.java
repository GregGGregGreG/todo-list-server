package edu.greg.todolist.todo.util;

import edu.greg.todolist.todo.persistence.dto.TaskDto;
import edu.greg.todolist.todo.persistence.dto.UserDto;
import edu.greg.todolist.todo.persistence.model.Task;
import edu.greg.todolist.todo.persistence.model.User;
import edu.greg.todolist.todo.persistence.service.UserServices;
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
    private UserServices userService;

    private static TodoUtils utils;

    @PostConstruct
    public void init() {
        utils = this;
        utils.userService = this.userService;
    }

    public static User getCurrentUser() {
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
        dto.setEmail(model.getEmail());
        dto.setCreatedDate(model.getCreatedDate());

        return dto;
    }

    public static TaskDto createDtoTask(Task model) {
        TaskDto dto = new TaskDto();

        dto.setId(model.getId());
        dto.setText(model.getText());
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
