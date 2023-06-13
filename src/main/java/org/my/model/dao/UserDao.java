package org.my.model.dao;

import org.my.model.entities.User;

/**
 * Interface ApplicantFacultyDao is used to access the information about faculties with registered applicants from the database.
 *
 * @author Kyrylo Shchupak
 */
public interface UserDao extends Dao<User>{
    /**
     * The method gets user by login and password.
     *
     * @param login - user`s login.
     * @param password - user`s password.
     * @return user with login and password.
     */
    User findByLoginAndPassword(String login, String password);
}
