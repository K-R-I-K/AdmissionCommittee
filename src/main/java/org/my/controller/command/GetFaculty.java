package org.my.controller.command;

import org.my.model.dao.ApplicantFacultyDao;
import org.my.model.dao.FacultyDao;
import org.my.model.dao.UserDao;
import org.my.model.entities.ApplicantFaculty;
import org.my.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class GetFaculty implements Command {
    private FacultyDao facultyDao;
    private ApplicantFacultyDao applicantFacultyDao;
    private UserDao userDao;
    public GetFaculty(FacultyDao facultyDao, ApplicantFacultyDao applicantFacultyDao, UserDao userDao) {
        this.facultyDao = facultyDao;
        this.applicantFacultyDao= applicantFacultyDao;
        this.userDao = userDao;
    }
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("faculty", facultyDao.findById(Long.parseLong(request.getParameter("id"))));
        List<ApplicantFaculty> applicantFacultyList = applicantFacultyDao.findByFacultyId(Long.parseLong(request.getParameter("id")));
        List<User> users = new ArrayList<>();
        List<Double> rating = new ArrayList<>();
        for (int i = 0; i < applicantFacultyList.size(); i++) {
            users.add(userDao.findById(applicantFacultyList.get(i).getUserId()));
            rating.add(applicantFacultyList.get(i).getMark1() * 0.3 +
                    applicantFacultyList.get(i).getMark2() * 0.5 +
                    applicantFacultyList.get(i).getMark3() * 0.2 +
                    applicantFacultyList.get(i).getAvgCertificateMark());
        }
        request.getSession().setAttribute("applicants", users);
        request.getSession().setAttribute("marks", applicantFacultyList);
        request.getSession().setAttribute("rating", rating);
        return "faculty.jsp";
    }
}
