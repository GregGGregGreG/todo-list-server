package edu.greg.todolist.todo.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by greg on 27.06.15.
 */
@Entity
@Data
public class Role extends AbstractEntity {

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
