package edu.greg.todolist.todo.persistence.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by greg on 27.06.15.
 */
@Data
@Entity
@ToString(exclude = {"roles", "tasks"})
public class User extends AbstractEntity {

    private String name;

    private String email;

    private String password;

    @ManyToMany
    @JoinTable
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Task> tasks;

}
