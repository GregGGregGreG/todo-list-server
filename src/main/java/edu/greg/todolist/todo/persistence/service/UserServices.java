package edu.greg.todolist.todo.persistence.service;


import edu.greg.todolist.todo.persistence.dto.UserDto;
import edu.greg.todolist.todo.persistence.model.User;
import edu.greg.todolist.todo.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by greg on 01.07.15.
 */
@Slf4j
@Service
@Transactional
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public User add(UserDto added) {
        log.debug("Adding a new user entry with information: {}", added);

        User model = User.getBuilder(added.getName())
                .email(added.getEmail())
                .password(added.getPassword())
                .build();

        return userRepository.save(model);
    }

    public User findOne(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
