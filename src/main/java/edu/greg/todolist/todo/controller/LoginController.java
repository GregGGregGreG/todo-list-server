package edu.greg.todolist.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by greg on 27.06.15.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping()
    public String login(){
        return "login";
    }
}
