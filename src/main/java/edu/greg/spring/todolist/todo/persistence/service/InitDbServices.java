package edu.greg.spring.todolist.todo.persistence.service;

import edu.greg.spring.todolist.todo.persistence.repository.UserRepository;
import edu.greg.spring.todolist.todo.persistence.model.Role;
import edu.greg.spring.todolist.todo.persistence.model.Task;
import edu.greg.spring.todolist.todo.persistence.model.User;
import edu.greg.spring.todolist.todo.persistence.repository.RoleRepository;
import edu.greg.spring.todolist.todo.persistence.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
        user.setName("greg");
        user.setEmail("1@mail.ru");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode("123456"));
        user.setEnabled(true);

        Role roleTest = new Role();
        roleTest.setName("ROLE_USER");
        roleRepository.save(roleTest);

        Set<Role> roles = new HashSet<>();
        roles.add(roleTest);

        user.setRoles(roles);

        userRepository.save(user);

        User secondUser = new User();
        secondUser.setName("grom");
        secondUser.setEmail("2@mail.ru");

        secondUser.setPassword(encoder.encode("123456"));
        secondUser.setEnabled(true);

        secondUser.setRoles(roles);

        userRepository.save(secondUser);



        Task taskOne = new Task();
        taskOne.setPublishedDate(new Date());
        taskOne.setText("One");
        taskOne.setIsExecuted(true);
        taskOne.setCreator(user);
        taskOne.setPerformer(user.getName());
        taskRepository.save(taskOne);

        Task taskTwo = new Task();
        taskTwo.setPublishedDate(new Date());
        taskTwo.setText("Two");
        taskTwo.setCreator(user);
        taskTwo.setPerformer(user.getName());
        taskRepository.save(taskTwo);

    }
}
