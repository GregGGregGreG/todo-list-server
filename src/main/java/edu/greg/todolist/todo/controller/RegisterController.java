package edu.greg.todolist.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by greg on 27.06.15.
 */
@Controller
@RequestMapping("/registration")
public class RegisterController {

    @RequestMapping(method = RequestMethod.GET)
    public String doRegister() {
        return "registration";
    }
}
