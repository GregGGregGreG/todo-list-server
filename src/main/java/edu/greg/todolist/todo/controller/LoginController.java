package edu.greg.todolist.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static edu.greg.todolist.todo.util.TodoView.*;

/**
 * Created by greg on 27.06.15.
 */
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping()
    public ModelAndView login(Principal principal) {
        log.debug("Rendering login page.");
        if (principal != null) {
            log.debug("User already authorized and request redirect to account page.");
            return new ModelAndView(redirectTo(ACCOUNT));
        }
        return new ModelAndView(getView(LOGIN));
    }
}
