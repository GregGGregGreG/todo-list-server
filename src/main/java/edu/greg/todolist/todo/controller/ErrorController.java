package edu.greg.todolist.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by greg on 27.06.15.
 */
@Slf4j
@Controller
public class ErrorController {

    protected static final String ERROR_VIEW = "404";

    @RequestMapping(value = "/error/404", method = RequestMethod.GET)
    public String show404Page() {
        log.debug("Rendering 404 page");
        return ERROR_VIEW;
    }
}
