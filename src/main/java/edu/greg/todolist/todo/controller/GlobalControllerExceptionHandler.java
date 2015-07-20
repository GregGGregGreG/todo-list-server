package edu.greg.todolist.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;


/**
 * Created by greg on 26.06.15.
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler{

        public static final String DEFAULT_ERROR_VIEW = "error";
        public static final String NOT_FOUND_VIEW = "p404";

    //
//    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="IOException occured")
//    @ExceptionHandler(IOException.class)
//    public ModelAndView handleIOException(HttpServletRequest request, Exception e){
//        log.info("IOException handler executed 404",e.getMessage());
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        //returning 404 error code
//        return mav;
//    }

//
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
//        mav.addObject("status", ResponseStatus.class);
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
//    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ModelAndView handleError(HttpServletRequest req, Exception exception)
//            throws Exception {
//
//        // Rethrow annotated exceptions or they will be processed here instead.
//        if (AnnotationUtils.findAnnotation(exception.getClass(),
//                ResponseStatus.class) != null)
//            throw exception;
//
//        log.error("Request: " + req.getRequestURI() + " raised " + exception);
//
//        ModelAndView mav = new ModelAndView();
////        mav.addObject("exception", exception);
////        mav.addObject("url", req.getRequestURL());
////        mav.addObject("timestamp", new Date().toString());
////        mav.addObject("status", 500);
//
//        mav.setViewName("404");
//        return mav;
//    }

//        @ExceptionHandler(ResourceNotFoundException.class)
//        @ResponseBody
//        @ResponseStatus(HttpStatus.NOT_FOUND)
//        public ErrorMessage handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest req) {
//                return new ErrorMessage(e);
//        }
//
//        @ExceptionHandler(InternalServerErrorException.class)
//        @ResponseBody
//        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//        public ErrorMessage handleInternalServerErrorException(InternalServerErrorException e, HttpServletRequest req) {
//                return new ErrorMessage(e);
//        }
}
