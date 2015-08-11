package edu.greg.todolist.todo.persistence.service;

import edu.greg.todolist.todo.persistence.dto.RegistrationForm;
import edu.greg.todolist.todo.persistence.exception.UserNotFoundException;
import edu.greg.todolist.todo.persistence.model.User;

import java.util.List;

/**
 * Created by greg on 07.08.15.
 */
public interface UserService {

    /**
     * Adds a new user entry.
     *
     * @param added The information of the added user entry.
     * @return  The added user entry.
     */
    User add(RegistrationForm added);

    /**
     * Finds a user entry.
     *
     * @param name The name of the wanted user entry.
     * @return  The found user entry.
     * @throws UserNotFoundException    if no to-do entry is found with the given id.
     */
    User findByName(String name) throws UserNotFoundException;

    /**
     * Finds a user entry.
     *
     * @param email The email of the wanted user entry.
     * @return  The found user entry.
     * @throws UserNotFoundException    if no to-do entry is found with the given id.
     */
    User findByEmail(String email) throws UserNotFoundException;

    /**
     * Finds a user list entry.
     *
     * @param symbols The symbols to search for user names.
     * @return  The found user list entry.
     * @throws UserNotFoundException    if no to-do entry is found with the given id.
     */
    List<User> finByNameStartingWith(String symbols);

}
