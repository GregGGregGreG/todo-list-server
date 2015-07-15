package edu.greg.todolist.todo.persistence.exception.heandler;

import cz.jirutka.spring.exhandler.handlers.ErrorMessageRestExceptionHandler;
import cz.jirutka.spring.exhandler.messages.ErrorMessage;
import cz.jirutka.spring.exhandler.messages.ValidationErrorMessage;
import edu.greg.todolist.todo.persistence.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by greg on 12.07.15.
 */
public class TaskNotFoundExceptionHandler extends ErrorMessageRestExceptionHandler<TaskNotFoundException> {

    public TaskNotFoundExceptionHandler() {
        super(HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ErrorMessage createBody(TaskNotFoundException ex, HttpServletRequest req) {

        ErrorMessage tmpl = super.createBody(ex, req);
        tmpl.setTitle("Delete Task");

        ValidationErrorMessage msg = new ValidationErrorMessage(tmpl);

        msg.addError(ex.getMessage());

        return msg;
    }

}
