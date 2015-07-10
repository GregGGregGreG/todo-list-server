package edu.greg.todolist.todo.persistence.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by greg on 06.07.15.
 */
@Setter
@Getter
public class UserDto {

    private String name;
    private String email;
    private List<TaskDto> taskDtoList;

}
