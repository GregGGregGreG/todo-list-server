package edu.greg.todolist.todo.persistence.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by greg on 30.06.15.
 */
@Data
@Entity
public class Task extends AbstractEntity {

    public static final int MAX_LENGTH_TEXT = Integer.MAX_VALUE;

    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
    @Column(length = MAX_LENGTH_TEXT)
    private String text;

    @Column(name = "published_date")
    private Date publishedDate;

    @Column(columnDefinition = "boolean default false")
    private Boolean isExecuted = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Builder getBuilder(String text) {
        return new Builder(text);
    }

    @PrePersist
    public void prePersist() {
        publishedDate = new Date();
    }

    public void update(String text) {
        this.text = text;
    }

    public void perform() {
        this.isExecuted = true;
    }

    public void unperformed() {
        this.isExecuted = false;
    }

    public static class Builder {

        private Task built;

        public Builder(String text) {
            built = new Task();
            built.text = text;
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