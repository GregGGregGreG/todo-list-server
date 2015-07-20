package edu.greg.todolist.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * Created by greg on 27.06.15.
 */
@Slf4j
@Controller
public class ErrorController {

    protected static final String ERROR_VIEW = "p404";
    public static final String DEFAULT_ERROR_VIEW = "error";

    @RequestMapping(value = "/error/404", method = RequestMethod.GET)
    public String show404Page(HttpServletRequest req, Model model) {
        log.debug("Rendering 404 page");
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        model.addAttribute("errorUrl", requestUri);
        return ERROR_VIEW;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String showError(HttpServletRequest req, Model model) {
        log.debug("Rendering error page");

        final Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        // create a message to be sent back via the model object.
        final String message = MessageFormat.format("{0} returned for {1}",
                statusCode, requestUri);

        model.addAttribute("errorMessage", message);
        return DEFAULT_ERROR_VIEW;
    }
}