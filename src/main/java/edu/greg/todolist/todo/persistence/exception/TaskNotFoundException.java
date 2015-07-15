package edu.greg.todolist.todo.persistence.exception;

/**
 * Created by greg on 15.07.15.
 */
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        super("task not found.");
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

}
