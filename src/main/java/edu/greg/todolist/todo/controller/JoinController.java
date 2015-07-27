package edu.greg.todolist.todo.controller;

import edu.greg.todolist.todo.persistence.dto.UserDto;
import edu.greg.todolist.todo.persistence.model.User;
import edu.greg.todolist.todo.persistence.service.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by greg on 27.06.15.
 */
@Slf4j
@Controller
@RequestMapping("/join")
public class JoinController {

    protected static final String JOIN_VIEW = "join";

    @Autowired
    private UserServices userServices;

    @ModelAttribute("dto")
    public UserDto constructUser() {
        return new UserDto();
    }

    @RequestMapping
    public String showRegister() {
        log.debug("Rendering registration page.");
        return JOIN_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doRegister(@Valid @ModelAttribute("dto") UserDto dto,
                             BindingResult result) {
        log.debug("Adding a new user entry with information: {}", dto);
        if (result.hasErrors()) {
            return JOIN_VIEW;
        }

        User added = userServices.add(dto);
        log.debug("Added a user entry with information: {}", added);
        return "redirect:/registration.html?success=true";
    }
}
