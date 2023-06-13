package org.my.controller.command;

import org.my.model.dao.UserDao;
import org.my.model.entities.Role;
import org.my.model.entities.User;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    private UserDao userDao;
    public Registration(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String city = request.getParameter("city");
            String region = request.getParameter("region");
            String educationalInstitution = request.getParameter("educational_institution");
            boolean isWrongInput = false;

            if(login == null || login.equals("")) {
                request.getSession().setAttribute("error", "registrationLogin");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("login", login);
            }
            if(password == null || password.equals("")) {
                if(!isWrongInput) request.getSession().setAttribute("error", "registrationPassword");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("password", password);
            }
            if(name == null || name.equals("")) {
                if(!isWrongInput) request.getSession().setAttribute("error", "registrationName");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("name", name);
            }
            if(email == null || email.equals("")) {
                if(!isWrongInput) request.getSession().setAttribute("error", "registrationEmail");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("email", email);
            }
            if(city == null || city.equals("")) {
                if(!isWrongInput) request.getSession().setAttribute("error", "registrationCity");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("city", city);
            }
            if(region == null || region.equals("")) {
                if(!isWrongInput) request.getSession().setAttribute("error", "registrationRegion");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("region", region);
            }
            if(educationalInstitution == null || educationalInstitution.equals("")) {
                if(!isWrongInput) request.getSession().setAttribute("error", "registrationEducationalInstitution");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("educationalInstitution", educationalInstitution);
            }
            if(isWrongInput) return "registration.jsp";
            User user = User.builder()
                    .login(login)
                    .password(password)
                    .role(Role.APPLICANT.toString())
                    .name(name)
                    .email(email)
                    .city(city)
                    .region(region)
                    .educationalInstitution(educationalInstitution)
                    .build();
            if (userDao.isExisting(user)) {
                request.getSession().setAttribute("error", "registrationAlreadyExist");
                return "registration.jsp";
            } else {
                userDao.add(user);
                user = userDao.findByLoginAndPassword(login, password);
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("userId", user.getId());
                return "redirect:index.jsp";
            }
        } catch (NullPointerException e) {
            return "registration.jsp";
        }
    }
}
