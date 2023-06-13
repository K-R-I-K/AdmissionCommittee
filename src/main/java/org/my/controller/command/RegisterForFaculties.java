package org.my.controller.command;

import org.my.model.dao.ApplicantFacultyDao;
import org.my.model.dao.FacultyDao;
import org.my.model.dao.UserDao;
import org.my.model.entities.ApplicantFaculty;
import org.my.model.entities.Faculty;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegisterForFaculties implements Command {
    private ApplicantFacultyDao applicantFacultyDao;
    private FacultyDao facultyDao;
    public RegisterForFaculties(ApplicantFacultyDao applicantFacultyDao, FacultyDao facultyDao) {
        this.applicantFacultyDao = applicantFacultyDao;
        this.facultyDao = facultyDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        /*try {*/
            //request.getSession().setAttribute("faculties", facultyDao.findAll());

            List<String> chosenFaculties = new ArrayList<>();
            String mark1 = request.getParameter("mark1");
            String mark2 = request.getParameter("mark2");
            String mark3 = request.getParameter("mark3");
            String avgCertificateMark = request.getParameter("avg_certificate_mark");
            boolean isWrongInput = false;

            if(request.getParameterValues("chosenFaculties") == null) {
                request.getSession().setAttribute("error", "facultyFaculties");
                isWrongInput = true;
            } else {
                chosenFaculties = new ArrayList<>(List.of(request.getParameterValues("chosenFaculties")));
                /*List<Faculty> faculties = (List<Faculty>) request.getSession().getAttribute("faculties");
                request.getSession().setAttribute("selectedFaculties", chosenFaculties);
                request.getSession().setAttribute("faculties", faculties.removeAll(chosenFaculties));*/
            }
            if(mark1 == null || mark1.equals("")) {
                if(!isWrongInput) request.getSession().setAttribute("error", "facultyMark1");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("mark1", mark1);
            }
            if(mark2 == null || mark2.equals("")) {
                if(!isWrongInput) request.getSession().setAttribute("error", "facultyMark2");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("mark2", mark2);
            }
            if(mark3 == null || mark3.equals("")) {
                if(!isWrongInput) request.getSession().setAttribute("error", "facultyMark3");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("mark3", mark3);
            }
            if(avgCertificateMark == null || avgCertificateMark.equals("")) {
                if(!isWrongInput) request.getSession().setAttribute("error", "facultyAvgCertificateMark");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("avgCertificateMark", avgCertificateMark);
            }
            if(isWrongInput) return "registerForFaculties.jsp";


            for (int i = 0; i < chosenFaculties.size(); i++) {
                applicantFacultyDao.add(ApplicantFaculty.builder()
                        .userId(Long.parseLong(request.getParameter("user_id")))
                        .facultyId(Long.parseLong(chosenFaculties.get(i)))
                        .mark1(Integer.parseInt(request.getParameter("mark1")))
                        .mark2(Integer.parseInt(request.getParameter("mark2")))
                        .mark3(Integer.parseInt(request.getParameter("mark3")))
                        .avgCertificateMark(Double.parseDouble(request.getParameter("avg_certificate_mark")))
                        .build());
            }
            return new GetFaculties(facultyDao).execute(request);
       /* } catch (NullPointerException e) {
            return "registerForFaculties.jsp";
        }*/
    }
}
