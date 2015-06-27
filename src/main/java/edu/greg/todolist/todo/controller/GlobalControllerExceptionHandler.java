package edu.greg.todolist.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * Created by greg on 26.06.15.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="IOException occured")
    @ExceptionHandler(IOException.class)
    public void handleIOException(){
        logger.error("IOException handler executed");
        //returning 404 error code
    }

}
