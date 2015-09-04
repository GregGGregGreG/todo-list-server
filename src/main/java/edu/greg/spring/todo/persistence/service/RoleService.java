package edu.greg.spring.todo.persistence.service;

import edu.greg.spring.todo.persistence.model.Role;

/**
 * Created by greg on 07.08.15.
 */
public interface RoleService {

    /**
     * Adds a new role entry.
     *
     * @param added  The information of the added role entry.
     * @return The added role entry.
     */
    Role add(Role added);
}
