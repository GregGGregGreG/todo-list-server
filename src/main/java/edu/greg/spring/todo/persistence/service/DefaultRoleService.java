package edu.greg.spring.todo.persistence.service;

import edu.greg.spring.todo.persistence.model.Role;
import edu.greg.spring.todo.persistence.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by greg on 04.07.15.
 */
@Slf4j
@Service
@Transactional
public class DefaultRoleService implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role add(Role added) {
        log.debug("Adding a new role entry with information: {}", added);
        return roleRepository.save(added);
    }

}
