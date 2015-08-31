package edu.greg.spring.todolist.todo.controller;


import edu.greg.spring.todolist.todo.persistence.dto.UserDto;
import edu.greg.spring.todolist.todo.util.TodoUtils;
import edu.greg.spring.todolist.todo.util.TodoView;
import edu.greg.spring.todolist.todo.persistence.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by greg on 05.07.15.
 */
@Slf4j
@Controller
public class AccountController {

    @ModelAttribute("user")
    public UserDto constructUser() {
        return new UserDto();
    }

    @RequestMapping("/account")
    public TodoView account(Model model) {
        log.debug("Rendering account page.");

        User found = TodoUtils.getCurrentUser();
        log.debug("Found user entry {}", found);

        model.addAttribute("user", TodoUtils.createDtoUser(found));
        return TodoView.ACCOUNT;
    }
}
