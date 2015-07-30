package edu.greg.todolist.todo.controller;

import edu.greg.todolist.todo.persistence.dto.UserDto;
import edu.greg.todolist.todo.persistence.model.User;
import edu.greg.todolist.todo.persistence.service.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by greg on 27.06.15.
 */
@Slf4j
@Controller
public class JoinController {

    protected static final String JOIN_VIEW = "join";

    @Autowired
    private UserServices userServices;

    @ModelAttribute("dto")
    public UserDto constructUser() {
        return new UserDto();
    }

    @RequestMapping("/join")
    public String showRegister(Principal principal) {
        log.debug("Rendering registration page.");
        return JOIN_VIEW;
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String doRegister(@Valid @ModelAttribute("dto") UserDto dto,
                             BindingResult result) {
        log.debug("Adding a new user entry with information: {}", dto);
        if (result.hasErrors()) {
            log.debug("Handle errors user entry: {}", result.hasErrors());
            return JOIN_VIEW;
        }

        User added = userServices.add(dto);
        log.debug("Added a user entry with information: {}", added);
        return "redirect:/" + JOIN_VIEW + "?success=true";
    }

    @RequestMapping(value = "/signup_check/username", method = RequestMethod.POST)
    @ResponseBody
    public String signUpCheckUserName(@Valid @ModelAttribute("dto") UserDto dto,
                                      BindingResult result) {
        if (result.hasErrors()) {
            log.debug("Handle errors user entry: {}", result.hasErrors());
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getField() + " - " + error.getDefaultMessage());
                if (error.getField().equals("name")) {
                    return error.getDefaultMessage();
                }
            }

        }
        return Boolean.TRUE.toString();
    }

}
