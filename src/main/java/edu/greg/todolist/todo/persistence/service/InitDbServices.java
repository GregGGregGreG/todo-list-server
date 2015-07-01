package edu.greg.todolist.todo.persistence.service;

import edu.greg.todolist.todo.persistence.entity.User;
import edu.greg.todolist.todo.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

/**
 * Created by greg on 01.07.15.
 */
@Transactional
@Service
public class InitDbServices {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setName("TEST");
        user.setEmail("TEST");
        user.setPassword("TEST");
        userRepository.save(user);
    }


}
