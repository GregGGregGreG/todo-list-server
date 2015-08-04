package edu.greg.todolist.config;

import edu.greg.todolist.todo.persistence.model.User;
import edu.greg.todolist.todo.persistence.service.UserServices;
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
    private UserServices userService;

    @Override
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {

        User user = userService.findByName(name);

        if (user == null) {
            throw new UsernameNotFoundException("Not found user by name: " + name);
        }

        return new SecurityUser(user);
    }
}
