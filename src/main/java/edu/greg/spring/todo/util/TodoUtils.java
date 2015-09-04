package edu.greg.spring.todo.util;

import edu.greg.spring.todo.persistence.dto.TaskDto;
import edu.greg.spring.todo.persistence.dto.UserDto;
import edu.greg.spring.todo.persistence.exception.UserNotFoundException;
import edu.greg.spring.todo.persistence.model.Task;
import edu.greg.spring.todo.persistence.model.User;
import edu.greg.spring.todo.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by greg on 04.08.15.
 */
@Component
public class TodoUtils {

    @Autowired
    private UserService userService;

    private static TodoUtils utils;

    private TodoUtils() {

    }

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
        return models
                .stream()
                .map(TodoUtils::createDtoUser)
                .collect(Collectors.toList());
    }

    /**
     * Create task dto from task model.
     * @param model The information of the task entry.
     * @return The taskDto.
     */
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

    /**
     * Convert list taskModel to list taskDto
     * @param models The information of the task list entry.
     * @return The list with taskDto.
     */
    public static List<TaskDto> createDtoTaskList(List<Task> models) {
        return models
                .stream()
                .map(TodoUtils::createDtoTask)
                .collect(Collectors.toList());
    }
}
