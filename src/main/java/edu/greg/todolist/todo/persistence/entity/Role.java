package edu.greg.todolist.todo.persistence.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by greg on 27.06.15.
 */
@Entity
@Data
@ToString(exclude = "users")
public class Role extends AbstractEntity {

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
