package org.my.model.dao.implementations;

import org.my.model.dao.util.db.ConnectionPool;
import org.my.model.dao.util.db.DBException;
import org.my.model.dao.*;

public class DaoFactoryImpl extends DaoFactory {
    private UserDao userDAO;
    private FacultyDao facultyDAO;
    private ApplicantFacultyDao applicantFacultyDao;

    @Override
    public UserDao createUserDao() throws DBException {
        if (userDAO == null) {
            userDAO = new UserDaoImpl(ConnectionPool.getConnection());
        }
        return userDAO;
    }

    @Override
    public FacultyDao createFacultyDao() throws DBException {
        if (facultyDAO == null) {
            facultyDAO = new FacultyDaoImpl(ConnectionPool.getConnection());
        }
        return facultyDAO;
    }

    @Override
    public ApplicantFacultyDao createApplicantFacultyDao() throws DBException {
        if (applicantFacultyDao == null) {
            applicantFacultyDao = new ApplicantFacultyDaoImpl(ConnectionPool.getConnection());
        }
        return applicantFacultyDao;
    }
}
