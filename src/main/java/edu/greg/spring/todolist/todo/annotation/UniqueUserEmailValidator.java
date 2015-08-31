package edu.greg.spring.todolist.todo.annotation;

import edu.greg.spring.todolist.todo.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by greg on 03.08.15.
 */
public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UniqueUserEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return userRepository == null || userRepository.findByEmail(email) == null;
    }
}
