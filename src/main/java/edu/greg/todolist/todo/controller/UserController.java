package edu.greg.todolist.todo.controller;

import edu.greg.todolist.todo.persistence.entity.User;
import edu.greg.todolist.todo.persistence.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by greg on 05.07.15.
 */
@Controller
public class UserController {

    @Autowired
    private UserServices userService;

    @ModelAttribute("user")
    public User constructBlog() {
        return new User();
    }

    @RequestMapping("/account")
    public String account(Model model) {
        model.addAttribute("user", userService.findOne("GreG"));
        return "account";
    }
}
