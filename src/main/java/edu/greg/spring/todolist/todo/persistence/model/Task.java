package edu.greg.spring.todolist.todo.persistence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by greg on 30.06.15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Task extends AbstractEntity {

    public static final int TEXT_MAX_LENGTH = Integer.MAX_VALUE;

    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
    @Column(nullable = false, length = TEXT_MAX_LENGTH)
    private String text;

    @Column(name = "published_date", unique = true, nullable = false)
    private Date publishedDate;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean isExecuted = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    @Column(nullable = false)
    private String performer;

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

    public static class Builder {

        private Task built;

        public Builder(String text) {
            built = new Task();
            built.text = text;
        }

        public Task build() {
            return built;
        }

        public Builder creator(User creator) {
            built.creator = creator;
            return this;
        }

        public Builder performer(String performer) {
            built.performer = performer;
            return this;
        }
    }
}
