package edu.greg.todolist.todo.persistence.service;

import edu.greg.todolist.todo.persistence.dto.TaskDto;
import edu.greg.todolist.todo.persistence.exception.TaskNotFoundException;
import edu.greg.todolist.todo.persistence.model.Task;
import edu.greg.todolist.todo.persistence.model.User;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by greg on 15.07.15.
 */
public interface TaskService {

    /**
     * Adds a new task entry to user.
     *
     * @param added The information of the added task entry.
     * @param user  The information of the user.
     * @return The added task entry.
     */
    Task addTaskToUser(TaskDto added, User user);

    /**
     * Updates the information of a task entry.
     *
     * @param updated The information of the updated task entry.
     * @return The updated task entry.
     * @throws TaskNotFoundException If no to-do entry is found with the given id.
     */
    Task update(TaskDto updated) throws TaskNotFoundException;

    /**
     * Deletes a task entry.
     *
     * @param id The id of the deleted task entry.
     * @return Remote task entry.
     * @throws TaskNotFoundException if no to-do entry is found with the given id.
     */
    Task deleteById(Integer id) throws TaskNotFoundException;

    /**
     * Find all task from user.
     *
     * @param found The found of the creator entry.
     * @param filter The filter of the task list.
     * @return Returns a list of task entries.
     */
    List<Task> findAllByUser(User found, PageRequest filter);

    /**
     * Finds a task entry.
     *
     * @param id The id of the wanted task entry.
     * @return The found task.
     * @throws TaskNotFoundException if no to-do task is found with the given id.
     */
    Task findById(Integer id) throws TaskNotFoundException;

}
