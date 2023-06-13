package org.my.model.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.my.model.dao.implementations.FacultyDaoImpl;
import org.my.model.dao.util.db.ConnectionPool;
import org.my.model.dao.util.db.DBException;
import org.my.model.entities.Faculty;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class FacultyDaoTest {
    private Connection connection;
    private FacultyDao facultyDao;

    @Before
    public void before() {
        connection = ConnectionPool.getConnection();
        facultyDao = new FacultyDaoImpl(connection);
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
        Faculty faculty = Faculty.builder()
                .id(0L)
                .name("name111")
                .budgetPlaces(111)
                .totalPlaces(111111)
                .build();

        facultyDao.add(faculty);
        Faculty actual = facultyDao.findByName("name111");
        if(actual != null) {
            facultyDao.deleteEntity(actual);
        }

        Assert.assertNotNull(actual);
    }

    @Test
    public void findById() {
        Faculty faculty = Faculty.builder()
                .id(0L)
                .name("name111")
                .budgetPlaces(111)
                .totalPlaces(111111)
                .build();

        facultyDao.add(faculty);
        Faculty entity = facultyDao.findByName("name111");
        Faculty actual = facultyDao.findById(entity.getId());
        facultyDao.delete(entity.getId());
        Assert.assertNotNull(actual);
    }

    @Test
    public void findAll() {
        Faculty faculty = Faculty.builder()
                .id(0L)
                .name("name111")
                .budgetPlaces(111)
                .totalPlaces(111111)
                .build();

        facultyDao.add(faculty);
        List<Faculty> list = facultyDao.findAll();
        facultyDao.deleteEntity(list.get(list.size() - 1));
        Assert.assertNotNull(list);
    }

    @Test
    public void deleteById() {
        Throwable exception = Assert.assertThrows(NoSuchElementException.class, () -> {
            Faculty faculty = Faculty.builder()
                    .id(0L)
                    .name("name111")
                    .budgetPlaces(111)
                    .totalPlaces(111111)
                    .build();

            facultyDao.add(faculty);
            Faculty tmp = facultyDao.findByName("name111");
            if(tmp != null) {
                facultyDao.delete(tmp.getId());
            }

            Faculty expected = facultyDao.findByName("name111");
            if (expected == null) {
                throw new NoSuchElementException("no such element");
            }
        });
        Assert.assertEquals(exception.getMessage(), "no such element");
    }

    @Test
    public void findFaculty() {
        Faculty faculty = Faculty.builder()
                .id(0L)
                .name("name111")
                .budgetPlaces(111)
                .totalPlaces(111111)
                .build();

        facultyDao.add(faculty);
        Faculty expected = facultyDao.findByName("name111");
        if(expected != null) {
            facultyDao.deleteEntity(expected);
        }
        Assert.assertNotNull(expected);
    }

    @Test
    public void update() {
        Faculty faculty = Faculty.builder()
                .id(0L)
                .name("name111")
                .budgetPlaces(111)
                .totalPlaces(111111)
                .build();

        facultyDao.add(faculty);
        Faculty faculty1 = facultyDao.findByName("name111");

        faculty = Faculty.builder()
                .id(faculty1.getId())
                .name("name222")
                .budgetPlaces(222)
                .totalPlaces(222222)
                .build();

        facultyDao.update(faculty);
        Faculty faculty2 = facultyDao.findByName("name222");

        Assert.assertNotEquals(faculty1, faculty2);

        if(faculty1 != null) {
            facultyDao.deleteEntity(faculty1);
        }
        if(faculty2 != null) {
            facultyDao.deleteEntity(faculty2);
        }
    }

    @Test
    public void isExist() {
        Faculty faculty = Faculty.builder()
                .id(0L)
                .name("name111")
                .budgetPlaces(111)
                .totalPlaces(111111)
                .build();

        facultyDao.add(faculty);
        Faculty entity = facultyDao.findByName("name111");
        boolean actual = facultyDao.isExisting(faculty);
        if(actual) {
            facultyDao.deleteEntity(entity);
        }
        Assert.assertTrue(actual);
    }

    @Test
    public void findByName() {
        Faculty faculty = Faculty.builder()
                .id(0L)
                .name("name111")
                .budgetPlaces(111)
                .totalPlaces(111111)
                .build();

        facultyDao.add(faculty);
        Faculty entity = facultyDao.findByName("name111");
        if(entity != null) {
            facultyDao.deleteEntity(entity);
        }
        Assert.assertNotNull(entity);
    }

    @Test
    public void getFacultiesWithSorting() {
        Faculty faculty = Faculty.builder()
                .id(0L)
                .name("name111")
                .budgetPlaces(111)
                .totalPlaces(111111)
                .build();

        facultyDao.add(faculty);
        Faculty entity = facultyDao.findByName("name111");
        List<Faculty> actual = facultyDao.getFacultiesWithSorting("byName(a-z)", 1);
        if(entity != null) {
            facultyDao.deleteEntity(entity);
        }
        Assert.assertNotNull(actual);
    }

    @Test
    public void userUpdate() {
        Faculty faculty = Faculty.builder()
                .id(0L)
                .name("name111")
                .budgetPlaces(111)
                .totalPlaces(111111)
                .build();

        Faculty newFaculty = new Faculty(faculty);
        newFaculty.setId(2L);
        newFaculty.setName("2");
        newFaculty.setBudgetPlaces(2);
        newFaculty.setTotalPlaces(2);

        Assert.assertNotEquals(faculty, newFaculty);
    }
}
