package org.my.model.dao;

import org.my.model.dao.util.db.DBException;
import org.my.model.dao.implementations.DaoFactoryImpl;

public abstract class DaoFactory {

    private static volatile DaoFactory daoFactory;

    public abstract UserDao createUserDao() throws DBException;

    public abstract FacultyDao createFacultyDao() throws DBException;

    public abstract ApplicantFacultyDao createApplicantFacultyDao() throws DBException;

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if(daoFactory == null) {
                    daoFactory = new DaoFactoryImpl();
                }
            }
        }
        return daoFactory;
    }
}
