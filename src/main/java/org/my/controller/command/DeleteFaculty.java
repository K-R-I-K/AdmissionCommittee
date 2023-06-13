package org.my.controller.command;

import org.my.model.dao.FacultyDao;

import javax.servlet.http.HttpServletRequest;

public class DeleteFaculty implements Command {
    private FacultyDao facultyDao;
    public DeleteFaculty(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }
    @Override
    public String execute(HttpServletRequest request) {
        facultyDao.delete(Long.parseLong(request.getParameter("id")));
        request.getSession().setAttribute("faculties", facultyDao.findAll());
        return "redirect:/home?command=getFaculties";
    }
}
