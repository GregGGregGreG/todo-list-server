package edu.greg.todolist.todo.persistence.service;

import edu.greg.todolist.todo.persistence.model.Role;
import edu.greg.todolist.todo.persistence.model.Task;
import edu.greg.todolist.todo.persistence.model.User;
import edu.greg.todolist.todo.persistence.repository.RoleRepository;
import edu.greg.todolist.todo.persistence.repository.TaskRepository;
import edu.greg.todolist.todo.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by greg on 01.07.15.
 */
@Transactional
@Service
public class InitDbServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TaskRepository taskRepository;


    @PostConstruct
    public void init() {
        User user = new User();
        user.setName("GreG");
        user.setEmail("1@mail.ru");
        user.setPassword("123456");

        Role roleTest = new Role();
        roleTest.setName("ADMIN");
        roleRepository.save(roleTest);

        Set<Role> roles = new HashSet<>();
        roles.add(roleTest);

        user.setRoles(roles);

        userRepository.save(user);

        Task taskOne = new Task();
        taskOne.setPublishedDate(new Date());
        taskOne.setDescription("One");
        taskOne.setUser(user);
        taskRepository.save(taskOne);

        Task taskTwo = new Task();
        taskTwo.setPublishedDate(new Date());
        taskTwo.setDescription("Two");
        taskTwo.setUser(user);
        taskRepository.save(taskTwo);

    }


}
