package edu.greg.spring.todo.persistence.repository;

import edu.greg.spring.todo.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by greg on 28.06.15.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
