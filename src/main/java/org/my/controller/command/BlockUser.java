package org.my.controller.command;

import org.my.model.dao.UserDao;
import org.my.model.entities.Role;
import org.my.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BlockUser implements Command {
    private UserDao userDao;
    public BlockUser(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = userDao.findById(Long.parseLong(request.getParameter("id")));
        userDao.update(User.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .role(Role.BLOCKED.toString())
                .name(user.getName())
                .email(user.getEmail())
                .city(user.getCity())
                .region(user.getCity())
                .educationalInstitution(user.getEducationalInstitution())
                .build());
        List<User> users = userDao.findAll();
        users.remove(userDao.findById(Long.parseLong(String.valueOf(request.getSession().getAttribute("userId")))));
        request.getSession().setAttribute("users", users);
        return "redirect:users.jsp";
    }
}
