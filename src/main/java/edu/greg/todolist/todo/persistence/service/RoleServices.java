package edu.greg.todolist.todo.persistence.service;

import edu.greg.todolist.todo.persistence.entity.Role;
import edu.greg.todolist.todo.persistence.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by greg on 04.07.15.
 */
@Service
@Transactional
public class RoleServices {

    @Autowired
    private RoleRepository roleRepository;


    public void save(Role role) {
        roleRepository.save(role);
    }

    public Role findOne(String name) {
        return roleRepository.findByName(name);
    }
}
