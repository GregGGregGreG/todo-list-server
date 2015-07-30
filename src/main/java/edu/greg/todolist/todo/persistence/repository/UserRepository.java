package edu.greg.todolist.todo.persistence.repository;

import edu.greg.todolist.todo.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by greg on 28.06.15.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByName(String name);

    User findByEmail(String email);
}
