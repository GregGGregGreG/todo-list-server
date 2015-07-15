package edu.greg.todolist.todo.controller;

import edu.greg.todolist.todo.persistence.model.User;
import edu.greg.todolist.todo.persistence.service.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by greg on 27.06.15.
 */
@Slf4j
@Controller
@RequestMapping("/registration")
public class RegisterController {

    protected static final String REGISTRATION_VIEW = "registration";

    @Autowired
    private UserServices userServices;

    @ModelAttribute
    public User constructUser() {
        return new User();
    }

    @RequestMapping
    public String showRegister() {
        log.debug("Rendering registration page.");
        return REGISTRATION_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("user") User user,
                             BindingResult result) {
        log.debug("Adding a new user entry with information: {}", user);
        if (result.hasErrors()) {
            return REGISTRATION_VIEW;
        }

        userServices.save(user);
        return "redirect:/registration.html?success=true";
    }
}
