package org.my.controller.command;

import org.my.model.dao.DaoFactory;
import org.my.model.dao.FacultyDao;
import org.my.model.entities.Faculty;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditFaculty implements Command {
    private FacultyDao facultyDao;
    public EditFaculty(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }
    @Override
    public String execute(HttpServletRequest request) {
        try {
            facultyDao.update(Faculty.builder()
                    .id(Long.parseLong(request.getParameter("id")))
                    .name(String.valueOf(request.getParameter("name")))
                    .budgetPlaces(Long.parseLong(request.getParameter("budget_places")))
                    .totalPlaces(Long.parseLong(request.getParameter("total_places")))
                    .build());
            request.getSession().setAttribute("faculties", facultyDao.findAll());
            return "redirect:/home?command=getFaculties";
        } catch (NullPointerException e) {
            return "redirect:faculty.jsp";
        }
    }
}
