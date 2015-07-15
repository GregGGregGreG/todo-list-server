package edu.greg.todolist.todo.persistence.exception;

import cz.jirutka.spring.exhandler.handlers.ErrorMessageRestExceptionHandler;
import cz.jirutka.spring.exhandler.messages.ErrorMessage;
import cz.jirutka.spring.exhandler.messages.ValidationErrorMessage;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * Created by greg on 12.07.15.
 */
public class IllegalArgumentExceptionHandler  extends ErrorMessageRestExceptionHandler<IllegalArgumentException> {

    public IllegalArgumentExceptionHandler() {
        super(UNPROCESSABLE_ENTITY);
    }

    @Override
    public ValidationErrorMessage createBody(IllegalArgumentException ex, HttpServletRequest req) {

        ErrorMessage tmpl = super.createBody(ex, req);
        tmpl.setTitle(ex.getMessage());
        ValidationErrorMessage msg = new ValidationErrorMessage(tmpl);

        msg.addError(ex.getMessage());

        return msg;
    }
}
