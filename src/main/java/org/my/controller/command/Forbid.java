package org.my.controller.command;

import org.my.model.dao.ApplicantFacultyDao;
import org.my.model.dao.FacultyDao;
import org.my.model.dao.UserDao;
import org.my.model.entities.ApplicantFaculty;
import org.my.model.entities.Faculty;
import org.my.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class Forbid implements Command {
    private ApplicantFacultyDao applicantFacultyDao;
    private UserDao userDao;
    private FacultyDao facultyDao;

    public Forbid(ApplicantFacultyDao applicantFacultyDao, UserDao userDao, FacultyDao facultyDao){
        this.applicantFacultyDao = applicantFacultyDao;
        this.userDao = userDao;
        this.facultyDao = facultyDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        applicantFacultyDao.update(ApplicantFaculty.builder()
                .id(Long.parseLong(request.getParameter("id")))
                .userId(Long.parseLong(request.getParameter("applicantId")))
                .facultyId(Long.parseLong(request.getParameter("facultyId")))
                .mark1(Integer.parseInt(request.getParameter("mark1")))
                .mark2(Integer.parseInt(request.getParameter("mark2")))
                .mark3(Integer.parseInt(request.getParameter("mark3")))
                .avgCertificateMark(Double.parseDouble(request.getParameter("avgCertificateMark")))
                .passed(false)
                .build());
        request.getSession().setAttribute("faculty", facultyDao.findById(Long.parseLong(request.getParameter("facultyId"))));
        List<ApplicantFaculty> applicantFacultyList = applicantFacultyDao.findByFacultyId(Long.parseLong(request.getParameter("facultyId")));
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
        return "redirect:faculty.jsp";
    }
}
