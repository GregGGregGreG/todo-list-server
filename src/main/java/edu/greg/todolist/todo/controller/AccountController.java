package edu.greg.todolist.todo.controller;


import edu.greg.todolist.todo.persistence.dto.UserDto;
import edu.greg.todolist.todo.persistence.model.User;
import edu.greg.todolist.todo.persistence.service.UserServices;
import edu.greg.todolist.todo.util.TodoView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static edu.greg.todolist.todo.util.TodoView.ACCOUNT;

/**
 * Created by greg on 05.07.15.
 */
@Slf4j
@Controller
public class AccountController {

    @Autowired
    private UserServices userService;

    @ModelAttribute("user")
    public UserDto constructBlog() {
        return new UserDto();
    }

    @RequestMapping("/account")
    public TodoView account(Model model,Principal principal) {
        log.debug("Rendering account page.");

        User found = getCurrentUser();
        log.debug("Found user entry {}", found);

        model.addAttribute("user", createDtoUser(found));
        return ACCOUNT;
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String name = ((UserDetails) principal).getUsername();
            return userService.findByName(name);
        }
        return null;
    }

    private UserDto createDtoUser(User model) {
        UserDto dto = new UserDto();

        dto.setName(model.getName());
        dto.setEmail(model.getEmail());
        dto.setCreatedDate(model.getCreatedDate());

        return dto;
    }

}
