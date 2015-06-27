package edu.greg.todolist.todo.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by greg on 27.06.15.
 */
@Controller
public class ErrorController {
    private static final Logger LOGGER = Logger.getLogger(ErrorController.class);

    @RequestMapping(value = "/error/404", method = RequestMethod.GET)
    public String show404Page() {
        LOGGER.debug("Rendering 404 page");
        return "error";
    }
}
