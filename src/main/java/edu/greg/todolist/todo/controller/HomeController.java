package edu.greg.todolist.todo.controller;

import edu.greg.todolist.todo.persistence.exception.TaskNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by greg on 26.06.15.
 */
@Slf4j
@Controller
public class HomeController {

    protected static final String INDEX_VIEW = "index";

    @RequestMapping(value = {"/index"})
    public String index() {
        log.debug("Rendering home page.");
        return INDEX_VIEW;
    }

    @RequestMapping(value = {"/test"})
    public String test() throws Exception{
        throw new TaskNotFoundException();
    }
}
