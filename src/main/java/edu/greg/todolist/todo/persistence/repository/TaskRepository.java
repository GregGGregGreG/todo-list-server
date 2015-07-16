package edu.greg.todolist.todo.persistence.repository;

import edu.greg.todolist.todo.persistence.model.Task;
import edu.greg.todolist.todo.persistence.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by greg on 07.07.15.
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUser(User user, Pageable pageable);
}
