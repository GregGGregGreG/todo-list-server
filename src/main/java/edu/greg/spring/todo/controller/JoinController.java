package edu.greg.spring.todo.controller;

import edu.greg.spring.todo.persistence.dto.RegistrationForm;
import edu.greg.spring.todo.persistence.dto.ValidationResponse;
import edu.greg.spring.todo.persistence.model.User;
import edu.greg.spring.todo.util.TodoView;
import edu.greg.spring.todo.persistence.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by greg on 27.06.15.
 */
@Slf4j
@Controller
public class JoinController {
    private static final String NAME_FIELD = "name";
    private static final String EMAIL_FIELD = "email";

    @Autowired
    private UserService userServices;

    @ModelAttribute("dto")
    public RegistrationForm constructUser() {
        return new RegistrationForm();
    }

    @RequestMapping("/join")
    public ModelAndView showRegister(Principal principal) {
        log.debug("Rendering registration page.");
        if (principal != null) {
            log.debug("User already authorized and request redirect to account page.");
            return new ModelAndView(TodoView.redirectTo(TodoView.ACCOUNT));
        }
        return new ModelAndView(TodoView.getView(TodoView.JOIN));
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ModelAndView doRegister(@Valid @ModelAttribute("dto") RegistrationForm dto,
                                   BindingResult result,
                                   ModelMap model) {
        log.debug("Adding a new user entry with information: {}", dto);
        if (result.hasErrors()) {
            log.debug("Handle errors user entry: {}", result.hasErrors());
            return new ModelAndView(TodoView.getView(TodoView.JOIN));
        }

        User added = userServices.add(dto);
        log.debug("Added a user entry with information: {}", added);
        model.put("success", true);
        return new ModelAndView(TodoView.redirectTo(TodoView.JOIN));
    }

    @RequestMapping(value = "/signup_check/username", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse signUpCheckUserName(@Valid @ModelAttribute("dto") RegistrationForm dto,
                                                  BindingResult result) {
        log.debug("Handle errors user entry: {}", result.hasErrors());
        return getFieldError(result, NAME_FIELD);
    }

    @RequestMapping(value = "/signup_check/email", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse signUpCheckEmail(@Valid @ModelAttribute("dto") RegistrationForm dto,
                                               BindingResult result) {
        log.debug("Handle errors user entry: {}", result.hasErrors());
        return getFieldError(result, EMAIL_FIELD);
    }

    private ValidationResponse getFieldError(BindingResult result, String field) {
        if (result.hasErrors()) {
            log.debug("Find {} message error", field);
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getField() + " - " + error.getDefaultMessage());
                if (error.getField().equals(field)) {
                    return new ValidationResponse(Boolean.FALSE, error.getDefaultMessage());
                }
            }
        }
        return new ValidationResponse(Boolean.TRUE);
    }
}
