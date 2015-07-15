package edu.greg.todolist.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Created by greg on 26.06.15.
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {


//    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="IOException occured")
//    @ExceptionHandler(IOException.class)
//    public void handleIOException(){
//        logger.error("IOException handler executed");
//        //returning 404 error code
//    }

//    public static final String DEFAULT_ERROR_VIEW = "error";
//
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        // If the exception is annotated with @ResponseStatus rethrow it and let
//        // the framework handle it - like the OrderNotFoundException example
//        // at the start of this post.
//        // AnnotationUtils is a Spring Framework utility class.
//        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
//            throw e;
//
//        // Otherwise setup and send the user to a default error-view.
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
//    }

}
