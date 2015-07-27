package edu.greg.todolist.service;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Created by greg on 28.06.15.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataBaseConfig.class})
@PersistenceContext(type = PersistenceContextType.EXTENDED)

//@WebAppConfiguration
public class TestRepository {

//    @Resource
//    private UserServices userServices;
//
//    @Resource
//    private RoleServices roleServices;
//
//    @Test
//    public void testSaveUser() throws Exception {
//        User user = new User();
//        user.setName("GreG");
//        user.setEmail("1@mail.ru");
//        userServices.add(user);
//
//        assertEquals(userServices.findOne("GreG").getName(), "GreG");
//
//    }
//
//
//    @Test
//    public void testFindAllUser() throws Exception {
//        User user = new User();
//        user.setName("GreG");
//        user.setEmail("1@mail.ru");
//        userServices.add(user);
//        userServices.add(user);
//
//        assertEquals(userServices.findAll().size(), 2);
//
//    }
//
//    @Test
//    public void testSaveRole() throws Exception {
//        Role roleAdmin = new Role();
//        roleAdmin.setName("ADMIN");
//        roleServices.save(roleAdmin);
//
//        assertEquals(roleServices.findOne("ADMIN").getName(), "ADMIN");
//    }
//
//    @Test
//    public void testUserSetRole() throws Exception {
//        Role roleAdmin = new Role();
//        roleAdmin.setName("ADMIN");
//        roleServices.save(roleAdmin);
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleAdmin);
//
//        User user = new User();
//        user.setName("GreG");
//        user.setEmail("1@mail.ru");
//        user.setRoles(roles);
//        userServices.add(user);

//        User greg = userServices.findById("GreG");



//        assertEquals(user.getName(), userServices.findById("GreG").getName());

//    }
}
