package edu.greg.spring.todolist.todo.persistence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by greg on 27.06.15.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = "users")
public class Role extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
