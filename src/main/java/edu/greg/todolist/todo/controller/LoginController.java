package edu.greg.todolist.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by greg on 27.06.15.
 */
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    protected static final String LOGIN_VIEW = "login";

    @RequestMapping()
    public String login(Principal principal) {
        log.debug("Rendering login page.");
        if (principal == null) {
            log.debug("User already authorized and request redirect to account page.");
            return "redirect:/" + UserController.ACCOUNT_VIEW;
        }
        return LOGIN_VIEW;
    }
}
