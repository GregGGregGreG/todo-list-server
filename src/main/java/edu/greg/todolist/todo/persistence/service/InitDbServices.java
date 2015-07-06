package edu.greg.todolist.todo.persistence.service;

import edu.greg.todolist.todo.persistence.entity.Role;
import edu.greg.todolist.todo.persistence.entity.User;
import edu.greg.todolist.todo.persistence.repository.RoleRepository;
import edu.greg.todolist.todo.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
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


    @PostConstruct
    public void init() {
        User user = new User();
        user.setName("GreG");
        user.setEmail("TEST");
        user.setPassword("TEST");

        Role roleTest = new Role();
        roleTest.setName("ADMIN");
        roleRepository.save(roleTest);

        Set<Role> roles = new HashSet<>();
        roles.add(roleTest);

        user.setRoles(roles);

        userRepository.save(user);
    }


}
