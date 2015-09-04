package edu.greg.spring.todo.persistence.repository;

import edu.greg.spring.todo.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by greg on 28.06.15.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    User findByEmail(String email);

    List<User> findByNameStartingWith(String name);
}
