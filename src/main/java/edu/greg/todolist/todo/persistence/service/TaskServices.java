package edu.greg.todolist.todo.persistence.service;

import edu.greg.todolist.todo.persistence.dto.TaskDto;
import edu.greg.todolist.todo.persistence.model.Task;
import edu.greg.todolist.todo.persistence.exception.TaskNotFoundException;
import edu.greg.todolist.todo.persistence.repository.TaskRepository;
import edu.greg.todolist.todo.persistence.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by greg on 07.07.15.
 */
@Service
@Transactional
public class TaskServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServices.class);

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public Task save(TaskDto taskDto, String userName) {
        Task task = Task.getBuilder(taskDto.getDescription())
                .user(userRepository.findByName(userName))
                .build();

      return taskRepository.save(task);
    }

    @Transactional(rollbackFor = {TaskNotFoundException.class})
    public Task deleteById(Integer id) throws TaskNotFoundException {
        LOGGER.debug("Deleting a task entry with id: {}", id);
        Task deleted = taskRepository.findOne(id);
        LOGGER.debug("Deleting task entry: {}", deleted);
        taskRepository.delete(deleted);
        return deleted;
    }

    public Task findOne(Integer id) {
        return taskRepository.findOne(id);
    }
}
