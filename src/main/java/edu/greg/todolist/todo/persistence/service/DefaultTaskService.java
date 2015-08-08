package edu.greg.todolist.todo.persistence.service;

import edu.greg.todolist.todo.persistence.dto.TaskDto;
import edu.greg.todolist.todo.persistence.exception.TaskNotFoundException;
import edu.greg.todolist.todo.persistence.model.Task;
import edu.greg.todolist.todo.persistence.model.User;
import edu.greg.todolist.todo.persistence.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Created by greg on 07.07.15.
 */
@Slf4j
@Service
@Transactional
public class DefaultTaskService implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task addTaskToUser(TaskDto added, User creator) {
        log.debug("Adding a new task entry with information: {} to user: {} ", added, creator);

        Task model = Task.getBuilder(added.getText())
                .user(creator)
                .build();

        return taskRepository.save(model);
    }

    @Transactional(rollbackFor = {TaskNotFoundException.class})
    public Task deleteById(Integer id) throws TaskNotFoundException {
        log.debug("Deleting a task entry with id: {}", id);

        Task deleted = findById(id);

        log.debug("Deleting task entry: {}", deleted);
        taskRepository.delete(deleted);
        return deleted;
    }

    //Find all task from user and sort publishedDate
    @Override
    public List<Task> findAllByUser(User creator, PageRequest filter) {
        log.debug("Finding a task list from user: {}", creator);
        List<Task> models = taskRepository.findAllByUser(creator, filter);

        return models;
    }

    @Transactional(readOnly = true, rollbackFor = {TaskNotFoundException.class})
    @Override
    public Task findById(Integer id) throws TaskNotFoundException {
        log.debug("Finding a task entry with id: {}", id);

        Task found = taskRepository.findOne(id);
        log.debug("Found task entry: {}", found);

        if (found == null) {
            throw new TaskNotFoundException("No to-entry found with id: " + id);
        }

        return found;
    }

    @Transactional(rollbackFor = {TaskNotFoundException.class})
    @Override
    public Task update(TaskDto updated) throws TaskNotFoundException {
        log.debug("Updating task with information: {}", updated);

        Task model = findById(updated.getId());
        log.debug("Found a task entry: {}", model);

        if (updated.getText() != null) {
            log.debug("Updating text task with information: {}", updated.getText());
            model.update(updated.getText());
        }

        if (updated.getIsExecuted() != null) {
            log.debug("Updating executed task with information: {}", updated.getIsExecuted());
            model.setIsExecuted(updated.getIsExecuted());
            model.setPublishedDate(new Date());
        }

        return model;
    }
}
