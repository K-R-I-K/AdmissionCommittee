package org.my.controller.command;

import org.my.model.dao.UserDao;
import org.my.model.entities.User;

import javax.servlet.http.HttpServletRequest;

public class LogIn implements Command {
    private UserDao userDao;
    public LogIn(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            boolean isWrongInput = false;

            if(login == null || login.equals("")) {
                request.getSession().setAttribute("error", "loginLogin");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("login", login);
            }
            if(password == null || password.equals("")) {
                if(!isWrongInput) request.getSession().setAttribute("error", "loginPassword");
                isWrongInput = true;
            } else {
                request.getSession().setAttribute("password", password);
            }
            if(isWrongInput) return "logIn.jsp";

            User user = userDao.findByLoginAndPassword(String.valueOf(request.getParameter("login")),
                    String.valueOf(request.getParameter("password")));
            if (user != null) {
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("userId", user.getId());
                return "index.jsp";
            } else {
                request.getSession().setAttribute("error", "badLogin");
                request.getSession().setAttribute("password", null);
                return "logIn.jsp";
            }
        } catch (NullPointerException e) {
            return "logIn.jsp";
        }
    }
}
