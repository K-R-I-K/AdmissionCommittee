package org.my.controller.command;

import org.my.model.dao.ApplicantFacultyDao;
import org.my.model.dao.FacultyDao;
import org.my.model.entities.Faculty;
import org.my.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SetFacultiesForRegistration implements Command {
    private FacultyDao facultyDao;
    private ApplicantFacultyDao applicantFacultyDao;
    public SetFacultiesForRegistration(FacultyDao facultyDao, ApplicantFacultyDao applicantFacultyDao) {
        this.facultyDao = facultyDao;
        this.applicantFacultyDao = applicantFacultyDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Long> facultiesId = applicantFacultyDao.getFacultiesWithApplicant(
                Long.parseLong(String.valueOf(request.getSession().getAttribute("userId"))));
        List<Faculty> facultiesWithApplicant = new ArrayList<>();
        for (int i = 0; i < facultiesId.size(); i++) {
            facultiesWithApplicant.add(facultyDao.findById(facultiesId.get(i)));
        }
        List<Faculty> faculties = facultyDao.findAll();
        faculties.removeAll(facultiesWithApplicant);
        request.getSession().setAttribute("faculties", faculties);
        return "registerForFaculties.jsp";
    }
}
