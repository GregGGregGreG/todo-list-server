package edu.greg.todolist.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by greg on 26.06.15.
 */
@Slf4j
@Controller
public class HomeController {

    protected static final String ACCOUNT_VIEW = "account";

    @RequestMapping(value = {"/"})
    public String index() {
        log.debug("Home page redirect to login page.");
        return ACCOUNT_VIEW;
    }
}
