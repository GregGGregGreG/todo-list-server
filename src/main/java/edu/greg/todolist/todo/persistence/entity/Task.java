package edu.greg.todolist.todo.persistence.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by greg on 30.06.15.
 */
@Entity
@Data
public class Task extends AbstractEntity{

    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
    @Column(length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "published_date")
    private Date publishedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Builder getBuilder(String title) {
        return new Builder(title);
    }

    @PrePersist
    public void prePersist() {
        publishedDate = new Date();
    }

    public static class Builder {

        private Task built;

        public Builder(String description) {
            built = new Task();
            built.description = description;
        }

        public Task build() {
            return built;
        }

        public Builder user(User user) {
            built.user = user;
            return this;
        }
    }
}
