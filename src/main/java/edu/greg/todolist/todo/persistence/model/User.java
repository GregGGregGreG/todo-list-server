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

    public static final int MIN_LENGTH_NAME = 3;
    public static final int MAX_LENGTH_NAME = 5;

    public static final int MIN_LENGTH_PASSWORD = 5;
    public static final int MAX_LENGTH_PASSWORD = 1000;

    public static final int MIN_LENGTH_EMAIL = 1;
    public static final int MAX_LENGTH_EMAIL = 1000;

    @Column(nullable = false, length = MAX_LENGTH_NAME)
    private String name;
    @Column(nullable = false, length = MAX_LENGTH_EMAIL)
    private String email;
    @Column(nullable = false, length = MAX_LENGTH_PASSWORD)
    private String password;

    @ManyToMany
    @JoinTable
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Task> tasks;

    public static Builder getBuilder(String name) {
        return new Builder(name);
    }

    public static class Builder {

        private User built;

        public Builder(String name) {
            built = new User();
            built.name = name;
        }

        public User build() {
            return built;
        }

        public Builder email(String email) {
            built.email = email;
            return this;
        }

        public Builder password(String password) {
            built.password = password;
            return this;
        }
    }
}
