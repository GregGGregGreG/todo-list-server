package edu.greg.todolist.todo.persistence.exception.heandler;

import cz.jirutka.spring.exhandler.handlers.ErrorMessageRestExceptionHandler;
import cz.jirutka.spring.exhandler.messages.ErrorMessage;
import cz.jirutka.spring.exhandler.messages.ValidationErrorMessage;
import edu.greg.todolist.todo.persistence.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by greg on 07.08.15.
 */
public class UserNotFoundExceptionHandler extends ErrorMessageRestExceptionHandler<UserNotFoundException> {

    public UserNotFoundExceptionHandler() {
        super(HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ErrorMessage createBody(UserNotFoundException ex, HttpServletRequest req) {

        ErrorMessage tmpl = super.createBody(ex, req);
        tmpl.setTitle("User not found ");

        ValidationErrorMessage msg = new ValidationErrorMessage(tmpl);

        msg.addError(ex.getMessage());

        return msg;
    }
}
