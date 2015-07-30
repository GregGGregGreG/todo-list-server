package edu.greg.todolist.todo.persistence.service;


import edu.greg.todolist.todo.persistence.dto.UserDto;
import edu.greg.todolist.todo.persistence.model.Role;
import edu.greg.todolist.todo.persistence.model.User;
import edu.greg.todolist.todo.persistence.repository.RoleRepository;
import edu.greg.todolist.todo.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by greg on 01.07.15.
 */
@Slf4j
@Service
@Transactional
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User add(UserDto added) {
        log.debug("Adding a new user entry with information: {}", added);

        Set<Role> userRole = new HashSet<>();
        userRole.add(roleRepository.findByName("ROLE_USER"));

        User model = User.getBuilder(added.getName())
                .email(added.getEmail())
                .password(bCrypt(added.getPassword()))
                .roles(userRole)
                .enabled(Boolean.TRUE)
                .build();

        return userRepository.save(model);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Encoding data
     * bcrypt is a key derivation function which is used in this instance as a cryptographic hash function
     *
     * @param password The information of the  encoding password.
     * @return The encoding password.
     */
    private static String bCrypt(String password) {
        log.debug("Coding new password: {}", password);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
