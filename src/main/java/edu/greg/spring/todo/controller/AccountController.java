package edu.greg.spring.todo.controller;


import edu.greg.spring.todo.persistence.model.User;
import edu.greg.spring.todo.util.TodoView;
import edu.greg.spring.todo.persistence.dto.UserDto;
import edu.greg.spring.todo.util.TodoUtils;
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
