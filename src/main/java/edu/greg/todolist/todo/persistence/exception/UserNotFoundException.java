package edu.greg.todolist.todo.persistence.exception;

/**
 * Created by greg on 07.07.15.
 */
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User not found.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
