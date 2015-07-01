package edu.greg.todolist.todo.persistence.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by greg on 28.06.15.
 */
@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;
}
