package edu.greg.spring.todolist.todo.security;

import edu.greg.spring.todolist.todo.persistence.model.User;
import edu.greg.spring.todolist.todo.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by greg on 04.08.15.
 */
@Component
public class CustomUserDetailsServiceFromFindByName implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {

        User user = userRepository.findByName(name);

        if (user == null) {
            throw new UsernameNotFoundException("Not found user by name: " + name);
        }

        return new SecurityUser(user);
    }
}
