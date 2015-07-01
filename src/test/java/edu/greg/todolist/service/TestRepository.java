package edu.greg.todolist.service;

import edu.greg.todolist.todo.persistence.entity.User;
import edu.greg.todolist.todo.persistence.service.UserServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * Created by greg on 28.06.15.
 */
//@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataBaseConfig.class})
//@WebAppConfiguration
public class TestRepository {

    @Resource
    private UserServices userServices;

    @Test
    public void testSaveUser() throws Exception {
        User user = new User();
        user.setName("GreG");
        user.setEmail("1@mail.ru");
        userServices.save(user);

        assertEquals(userServices.findOne("GreG").getName(), "GreG");

    }

    @Test
    public void testFindAllUser() throws Exception {
        User user = new User();
        user.setName("GreG");
        user.setEmail("1@mail.ru");
        userServices.save(user);
        userServices.save(user);

        assertEquals(userServices.findAll().size(), 2);

    }
}
