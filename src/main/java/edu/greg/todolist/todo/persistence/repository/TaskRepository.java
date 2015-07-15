package edu.greg.todolist.todo.persistence.repository;

import edu.greg.todolist.todo.persistence.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by greg on 07.07.15.
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
