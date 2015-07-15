package edu.greg.todolist.todo.persistence.service;

import edu.greg.todolist.todo.persistence.dto.TaskDto;
import edu.greg.todolist.todo.persistence.exception.TaskNotFoundException;
import edu.greg.todolist.todo.persistence.model.Task;
import edu.greg.todolist.todo.persistence.repository.TaskRepository;
import edu.greg.todolist.todo.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by greg on 07.07.15.
 */
@Slf4j
@Service
@Transactional
public class DefaultTaskServices implements TaskServices {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Task add(TaskDto taskDto, String userName) {
        Task model = Task.getBuilder(taskDto.getDescription())
                .user(userRepository.findByName(userName))
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

    @Override
    public List<Task> findAllFromUser(Integer userId) {
        return null;
    }

    @Transactional(readOnly = true, rollbackFor = {TaskNotFoundException.class})
    @Override
    public Task findById(Integer id) throws TaskNotFoundException{
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
    public Task update(TaskDto updated) {
        log.debug("Updating task with information: {}", updated);

        Task model = findById(updated.getId());
        log.debug("Found a task entry: {}", model);

        model.update(updated.getDescription());

        return model;


    }
}
