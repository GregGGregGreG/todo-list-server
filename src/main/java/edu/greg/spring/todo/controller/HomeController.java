package edu.greg.spring.todo.controller;

import edu.greg.spring.todo.util.TodoView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by greg on 26.06.15.
 */
@Slf4j
@Controller
public class HomeController {

    @RequestMapping(value = {"/"})
    public ModelAndView index() {
        log.debug("Home page redirect to account page.");
        return new ModelAndView(TodoView.redirectTo(TodoView.ACCOUNT));
    }
}
