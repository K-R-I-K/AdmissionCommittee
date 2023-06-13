package org.my.model.dao;

import org.my.model.entities.Faculty;
import org.my.model.entities.User;

import java.util.List;

/**
 * Interface ApplicantFacultyDao is used to access the information about faculties with registered applicants from the database.
 *
 * @author Kyrylo Shchupak
 */

public interface FacultyDao extends Dao<Faculty> {
    /**
     * The method gets faculty by name.
     *
     * @param name - faculty`s name.
     * @return faculty with name.
     */
    Faculty findByName(String name);

    /**
     * The method gets faculties in sortingType order.
     *
     * @param sortingType - type of sorting.
     * @param page - page number for pagination.
     * @return faculties in sortingType order.
     */
    List<Faculty> getFacultiesWithSorting(String sortingType, int page);
}
