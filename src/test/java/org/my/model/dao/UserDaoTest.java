package org.my.model.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.my.model.dao.implementations.UserDaoImpl;
import org.my.model.dao.util.db.ConnectionPool;
import org.my.model.dao.util.db.DBException;
import org.my.model.entities.Role;
import org.my.model.entities.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class UserDaoTest {
    private Connection connection;
    private UserDao userDao;

    @Before
    public void before() {
        connection = ConnectionPool.getConnection();
        userDao = new UserDaoImpl(connection);
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DBException("Cannot close connection", e);
        }
    }

    @Test
    public void add() {
        User user = User.builder()
                .id(0L)
                .login("login111")
                .password("password111")
                .role(Role.APPLICANT.toString())
                .name("name111")
                .email("email111")
                .city("city111")
                .region("region111")
                .educationalInstitution("educationalInstitution111")
                .build();

        userDao.add(user);
        User actual = userDao.findByLoginAndPassword("login111", "password111");
        if(actual != null) {
            userDao.deleteEntity(actual);
        }

        Assert.assertNotNull(actual);
    }

    @Test
    public void findById() {
        User user = User.builder()
                .id(0L)
                .login("login111")
                .password("password111")
                .role(Role.APPLICANT.toString())
                .name("name111")
                .email("email111")
                .city("city111")
                .region("region111")
                .educationalInstitution("educationalInstitution111")
                .build();

        userDao.add(user);
        User entity = userDao.findByLoginAndPassword("login111", "password111");
        User actual = userDao.findById(entity.getId());
        userDao.delete(entity.getId());
        Assert.assertNotNull(actual);
    }

    @Test
    public void findAll() {
        User user = User.builder()
                .id(0L)
                .login("login112")
                .password("password112")
                .role(Role.APPLICANT.toString())
                .name("name112")
                .email("email112")
                .city("city112")
                .region("region112")
                .educationalInstitution("educationalInstitution112")
                .build();

        userDao.add(user);
        List<User> list = userDao.findAll();
        userDao.deleteEntity(list.get(list.size() - 1));
        Assert.assertNotNull(list);
    }

    @Test
    public void deleteById() {
        Throwable exception = Assert.assertThrows(NoSuchElementException.class, () -> {
            User user = User.builder()
                    .id(0L)
                    .login("login113")
                    .password("password113")
                    .role(Role.APPLICANT.toString())
                    .name("name113")
                    .email("email113")
                    .city("city113")
                    .region("region113")
                    .educationalInstitution("educationalInstitution113")
                    .build();

            userDao.add(user);
            User tmp = userDao.findByLoginAndPassword("login113", "password113");
            if(tmp != null) {
                userDao.delete(tmp.getId());
            }

            User expected = userDao.findByLoginAndPassword("login113", "password113");
            if (expected == null) {
                throw new NoSuchElementException("no such element");
            }
        });
        Assert.assertEquals(exception.getMessage(), "no such element");
    }

    @Test
    public void findUser() {
        User user = User.builder()
                .id(0L)
                .login("login114")
                .password("password114")
                .role(Role.APPLICANT.toString())
                .name("name114")
                .email("email114")
                .city("city114")
                .region("region114")
                .educationalInstitution("educationalInstitution114")
                .build();

        userDao.add(user);
        User expected = userDao.findByLoginAndPassword("login114", "password114");
        if(expected != null) {
            userDao.deleteEntity(expected);
        }
        Assert.assertNotNull(expected);
    }

    @Test
    public void update() {
        User user = User.builder()
                .id(0L)
                .login("login114")
                .password("password114")
                .role(Role.APPLICANT.toString())
                .name("name114")
                .email("email114")
                .city("city114")
                .region("region114")
                .educationalInstitution("educationalInstitution114")
                .build();

        userDao.add(user);
        User user1 = userDao.findByLoginAndPassword("login114", "password114");

        user = User.builder()
                .id(user1.getId())
                .login("login222")
                .password("password222")
                .role(Role.APPLICANT.toString())
                .name("name222")
                .email("email222")
                .city("city222")
                .region("region222")
                .educationalInstitution("educationalInstitution111")
                .build();

        userDao.update(user);
        User user2 = userDao.findByLoginAndPassword("login222", "password222");

        Assert.assertNotEquals(user1, user2);

        if(user1 != null) {
            userDao.deleteEntity(user1);
        }
        if(user2 != null) {
            userDao.deleteEntity(user2);
        }
    }

    @Test
    public void isExist() {
        User user = User.builder()
                .id(0L)
                .login("login111")
                .password("password111")
                .role(Role.APPLICANT.toString())
                .name("name111")
                .email("email111")
                .city("city111")
                .region("region111")
                .educationalInstitution("educationalInstitution111")
                .build();

        userDao.add(user);
        User entity = userDao.findByLoginAndPassword("login111", "password111");
        boolean actual = userDao.isExisting(user);
        if(actual) {
            userDao.deleteEntity(entity);
        }
        Assert.assertTrue(actual);
    }

    @Test
    public void findByLoginAndPassword() {
        User user = User.builder()
                .id(0L)
                .login("login111")
                .password("password111")
                .role(Role.APPLICANT.toString())
                .name("name111")
                .email("email111")
                .city("city111")
                .region("region111")
                .educationalInstitution("educationalInstitution111")
                .build();

        userDao.add(user);
        User entity = userDao.findByLoginAndPassword("login111", "password111");
        if(entity != null) {
            userDao.deleteEntity(entity);
        }
        Assert.assertNotNull(entity);
    }

    @Test
    public void userUpdate() {
        User user = User.builder()
                .id(0L)
                .login("login111")
                .password("password111")
                .role(Role.APPLICANT.toString())
                .name("name111")
                .email("email111")
                .city("city111")
                .region("region111")
                .educationalInstitution("educationalInstitution111")
                .build();

        User newUser = new User(user);
        newUser.setId(2L);
        newUser.setLogin("2");
        newUser.setPassword("2");
        newUser.setRole(Role.ADMIN);
        newUser.setName("2");
        newUser.setEmail("2@2");
        newUser.setCity("2");
        newUser.setRegion("2");
        newUser.setEducationalInstitution("2");

        Assert.assertNotEquals(user, newUser);
    }
}
