package edu.greg.todolist.todo.persistence.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Created by greg on 27.06.15.
 */
@Entity
@Data
@ToString(exclude = {"roles", "tasks"})
@Proxy(lazy=false)
public class User extends AbstractEntity {

    private String name;

    private String email;

    private String password;

    @ManyToMany
    @JoinTable
    private Set<Role> roles;

//    @OneToMany(mappedBy = "user")
//    private List<Task> tasks;

}
