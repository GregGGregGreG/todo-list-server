package edu.greg.todolist.todo.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by greg on 10.08.15.
 */
@Setter
@Getter
public class UserDto {

    private String name;
    private Date createdDate;

}
