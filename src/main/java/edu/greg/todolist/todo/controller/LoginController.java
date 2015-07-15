package edu.greg.todolist.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by greg on 27.06.15.
 */
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    protected static final String LOGIN_VIEW = "login";

    @RequestMapping()
    public String login(){
        log.debug("Rendering login page.");
        return LOGIN_VIEW;
    }
}
