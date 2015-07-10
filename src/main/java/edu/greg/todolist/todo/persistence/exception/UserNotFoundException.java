package edu.greg.todolist.todo.persistence.exception;

/**
 * Created by greg on 07.07.15.
 */
public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message) {
        super(message);
    }
}
