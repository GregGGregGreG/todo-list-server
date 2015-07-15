package edu.greg.todolist.config;

import cz.jirutka.spring.exhandler.RestHandlerExceptionResolver;
import cz.jirutka.spring.exhandler.support.HttpMessageConverterUtils;
import edu.greg.todolist.todo.persistence.exception.TaskNotFoundException;
import edu.greg.todolist.todo.persistence.exception.heandler.TaskNotFoundExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.List;

/**
 * Created by greg on 15.07.15.
 */
@Configuration
public class RestHandlerExceptioResolverConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(exceptionHandlerExceptionResolver());
        resolvers.add(restExceptionResolver());
    }


    @Bean
    public RestHandlerExceptionResolver restExceptionResolver() {
        return RestHandlerExceptionResolver.builder()
                .messageSource(httpErrorMessageSource())
                .defaultContentType(MediaType.APPLICATION_JSON)
                .addErrorMessageHandler(Exception.class, HttpStatus.INTERNAL_SERVER_ERROR)
                .addHandler(TaskNotFoundException.class, new TaskNotFoundExceptionHandler())
                .build();
    }

    @Bean
    public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
        resolver.setMessageConverters(HttpMessageConverterUtils.getDefaultHttpMessageConverters());
        return resolver;
    }

    @Bean
    public MessageSource httpErrorMessageSource() {
        ReloadableResourceBundleMessageSource m = new ReloadableResourceBundleMessageSource();
        m.setBasename("classpath:/messages");
        m.setDefaultEncoding("UTF-8");
        return m;
    }
}
