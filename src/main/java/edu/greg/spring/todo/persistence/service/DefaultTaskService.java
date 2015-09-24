package edu.greg.spring.todo.persistence.service;

import edu.greg.spring.todo.persistence.dto.TaskDto;
import edu.greg.spring.todo.persistence.exception.TaskNotFoundException;
import edu.greg.spring.todo.persistence.exception.UserNotFoundException;
import edu.greg.spring.todo.persistence.model.Task;
import edu.greg.spring.todo.persistence.model.User;
import edu.greg.spring.todo.persistence.repository.TaskRepository;
import edu.greg.spring.todo.util.TodoUtils;
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

    @Autowired
    private UserService userService;

    @Override
    public Task addTaskToUser(TaskDto added, User creator) throws UserNotFoundException {
        log.debug("Adding a new task entry with information: {} to user: {} ", added, creator);
        String userPerformerName = validPerformer(added);

        log.debug("Finding a user performer entry with name: {}", userPerformerName);
        User performer = userService.findByName(userPerformerName);
        log.debug("Found user performer entry: {}", performer);

        Task model = Task.getBuilder(added.getText())
                .creator(creator)
                .performer(performer.getName())
                .build();

        log.debug("Save task model: {}", model);
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

    //Find all task from user
    @Override
    public List<Task> findAllByUser(User creator, PageRequest filter) {
        log.debug("Finding all task list from user: {}", creator);
        List<Task> models = taskRepository.findAllByPerformerOrCreator(creator.getName(), creator, filter);

        return models;
    }

    //Find all tasks that are assigned to the user.
    @Override
    public List<Task> findAllAssignedByUser(User creator, PageRequest filter) {
        log.debug("Finding all task list from user: {}", creator);
        List<Task> models = taskRepository.findAllByPerformerNotAndCreator(creator.getName(), creator, filter);

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
            log.debug("Set task completed with information: {}", updated.getIsExecuted());
            model.setIsExecuted(updated.getIsExecuted());
            model.setPublishedDate(new Date());
        }

        return model;
    }

    private String validPerformer(TaskDto added) {
        return added.getPerformer().trim().equals("")
                ? TodoUtils.getCurrentUser().getName()
                : added.getPerformer().trim();
    }
}
