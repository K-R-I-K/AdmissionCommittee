package org.my.controller.command;

import org.my.model.dao.UserDao;
import org.my.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUsers implements Command {
    private UserDao userDao;
    public GetUsers(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userDao.findAll();
        users.remove(userDao.findById(Long.parseLong(String.valueOf(request.getSession().getAttribute("userId")))));
        request.getSession().setAttribute("users", users);
        return "users.jsp";
    }
}
