package edu.greg.todolist.todo.persistence.service;


import edu.greg.todolist.todo.persistence.dto.UserDto;
import edu.greg.todolist.todo.persistence.exception.UserNotFoundException;
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
import java.util.Set;

import static edu.greg.todolist.todo.util.TodoRole.USER;

/**
 * Created by greg on 01.07.15.
 */
@Slf4j
@Service
@Transactional
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User add(UserDto added) {
        log.debug("Adding a new user entry with information: {}", added);

        Set<Role> roles = new HashSet<>();
        
        log.debug("Finding a role entry with name: {}", USER);
        Role userRole = roleRepository.findByName(USER.getName());
        log.debug("Found role entry: {}", userRole);

        log.debug("Adding role : {} to new user entry", userRole);
        roles.add(userRole);

        User model = User.getBuilder(added.getName())
                .email(added.getEmail())
                .password(bCrypt(added.getPassword()))
                .roles(roles)
                .enabled(Boolean.TRUE)
                .build();

        return userRepository.save(model);
    }

    @Override
    public User findByName(String name) throws UserNotFoundException {
        log.debug("Finding a user entry with name: {}", name);

        User found = userRepository.findByName(name);
        log.debug("Found task entry: {}", found);

        if (found == null) {
            throw new UserNotFoundException("No user-entry found with name: " + name);
        }

        return found;
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        log.debug("Finding a user entry with email: {}", email);

        User found = userRepository.findByEmail(email);
        log.debug("Found task entry: {}", found);

        if (found == null) {
            throw new UserNotFoundException("No user-entry found with email: " + email);
        }

        return found;
    }

    /**
     * Encoding data
     * bcrypt is a key derivation function which is used in this instance as a cryptographic hash function
     *
     * @param password The information of the  encoding password.
     * @return The encoding password.
     */
    private static String bCrypt(String password) {
        log.debug("Coding a new password: {}", password);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
