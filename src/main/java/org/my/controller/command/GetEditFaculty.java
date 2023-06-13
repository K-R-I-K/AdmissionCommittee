package org.my.controller.command;

import org.my.model.dao.FacultyDao;
import org.my.model.entities.Faculty;

import javax.servlet.http.HttpServletRequest;

public class GetEditFaculty implements Command {
    private FacultyDao facultyDao;
    public GetEditFaculty(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession()
                .setAttribute("editFaculty", facultyDao.findById(Long.parseLong(request.getParameter("id"))));
        return "editFaculty.jsp";
    }
}
