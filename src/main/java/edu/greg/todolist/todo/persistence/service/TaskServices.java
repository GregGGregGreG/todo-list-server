package edu.greg.todolist.todo.persistence.service;

import edu.greg.todolist.todo.persistence.dto.TaskDto;
import edu.greg.todolist.todo.persistence.exception.TaskNotFoundException;
import edu.greg.todolist.todo.persistence.model.Task;

import java.util.List;

/**
 * Created by greg on 15.07.15.
 */
public interface TaskServices {

    /**
     * Adds a new task entry.
     *
     * @param added  The information of the added task entry.
     * @param email The information of the email user.
     * @return The added task entry.
     */
    Task add(TaskDto added, String email);

    /**
     * Updates the information of a task entry.
     * @param updated   The information of the updated task entry.
     * @return  The updated task entry.
     * @throws TaskNotFoundException    If no to-do entry is found with the given id.
     */
    Task update(TaskDto updated) throws TaskNotFoundException;

    /**
     * Deletes a task entry.
     *
     * @param id The id of the deleted task entry.
     * @return The deleted task entry.
     * @throws TaskNotFoundException if no to-do entry is found with the given id.
     */
    Task deleteById(Integer id) throws TaskNotFoundException;

    /**
     * Find all task from user
     * @param email The email of the creator entry.
     * @return Returns a list of task entries.
     */
    List<Task> findAllByUserEmail(String email);

    /**
     * Finds a task entry.
     * @param id    The id of the wanted task entry.
     * @return  The found task.
     * @throws TaskNotFoundException    if no to-do task is found with the given id.
     */
    Task findById(Integer id) throws TaskNotFoundException;

}
