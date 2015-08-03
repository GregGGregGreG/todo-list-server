package edu.greg.todolist.todo.annotation;

import edu.greg.todolist.todo.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by greg on 03.08.15.
 */
public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void initialize(UniqueUserName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return userRepository == null || userRepository.findByName(name) == null;
    }
}
