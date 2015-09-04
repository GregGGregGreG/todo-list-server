package edu.greg.spring.config;

import edu.greg.spring.todo.persistence.repository.RoleRepository;
import edu.greg.spring.todo.persistence.repository.TaskRepository;
import edu.greg.spring.todo.persistence.repository.UserRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.util.List;

/**
 * Created by greg on 02.09.15.
 */
@ContextConfiguration
@EnableWebMvc
@ComponentScan(value = "edu.greg.spring.todolist.todo")
//@EnableJpaRepositories("edu.greg.spring.todolist.todo.persistence.repository")
public class TestWebAppConfig extends WebMvcConfigurerAdapter {

    private static final String TILES_DEFINITION = "/WEB-INF/defs/general.xml";

    @Bean
    public ViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(TilesView.class);
        return viewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(TILES_DEFINITION);
        configurer.setCheckRefresh(true);
        return configurer;
    }

    //Handler enum view
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(
                new TodoViewEnumModelAndViewResolver()
        );
    }

    //Repository mock
    @Bean
    public UserRepository UserRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public RoleRepository RoleRepository() {
        return Mockito.mock(RoleRepository.class);
    }

    @Bean
    public TaskRepository TaskRepository() {
        return Mockito.mock(TaskRepository.class);
    }
}
