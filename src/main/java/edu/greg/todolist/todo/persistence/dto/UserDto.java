package edu.greg.todolist.todo.persistence.dto;


import edu.greg.todolist.todo.annotation.UniqueUserEmail;
import edu.greg.todolist.todo.annotation.UniqueUserName;
import edu.greg.todolist.todo.persistence.model.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Length.List;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;


/**
 * Created by greg on 06.07.15.
 */
@Setter
@Getter
public class UserDto {

    @List({
            @Length(min = User.MIN_LENGTH_NAME, message = "{UserDto.error.name.size.min}"),
            @Length(max = User.MAX_LENGTH_NAME, message = "{UserDto.error.name.size.max}")
    })
    @UniqueUserName(message = "{UserDto.error.name.size.unique}")
    private String name;

    @NotEmpty(message = "{UserDto.error.email}")
    @Email(message = "{UserDto.error.email}")
    @UniqueUserEmail(message = "{UserDto.error.email.unique}")
    private String email;

    @Length(min = User.MIN_LENGTH_PASSWORD, message = "{UserDto.error.password.size.min}")
    private String password;

    private Date createdDate;

}
