package edu.greg.todolist.todo.persistence.repository;

import edu.greg.todolist.todo.persistence.model.Role;
import edu.greg.todolist.todo.util.TodoRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by greg on 28.06.15.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(TodoRole name);
}
