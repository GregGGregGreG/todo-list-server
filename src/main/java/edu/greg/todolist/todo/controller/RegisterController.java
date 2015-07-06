package edu.greg.todolist.todo.controller;

import edu.greg.todolist.todo.persistence.entity.User;
import edu.greg.todolist.todo.persistence.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by greg on 27.06.15.
 */
@Controller
@RequestMapping("/registration")
public class RegisterController {


    @Autowired
    private UserServices userServices;

    @ModelAttribute
    public User constructUser() {
        return new User();
    }

    @RequestMapping
    public String showRegister() {
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("user") User user,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }

        userServices.save(user);
        return "redirect:/registration.html?success=true";

    }
}
