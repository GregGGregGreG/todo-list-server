package edu.greg.todolist.config;

import edu.greg.todolist.todo.security.CustomUserDetailsServiceFromFindByEmail;
import edu.greg.todolist.todo.security.CustomUserDetailsServiceFromFindByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * Created by greg on 29.07.15.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    CustomUserDetailsServiceFromFindByEmail findByEmail;

    @Autowired
    CustomUserDetailsServiceFromFindByName findByName;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = passwordEncoder();
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(encoder)
//                .usersByUsernameQuery(
//                        "select email,password,enabled from app_user where email = ?")
//                .authoritiesByUsernameQuery(
//                        "select app_user.email, role.name from app_user " +
//                                "join app_user_roles on app_user.id = app_user_roles.users " +
//                                "join role on app_user_roles.roles=role.id " +
//                                "where app_user.email = ?");

        auth
                .userDetailsService(findByEmail)
                .passwordEncoder(encoder)
                .and()
                .userDetailsService(findByName)
                .passwordEncoder(encoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/join/**", "/signup_check/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/account")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login?expired");

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
