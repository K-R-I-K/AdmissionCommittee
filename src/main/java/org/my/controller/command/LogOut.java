package org.my.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);
        request.getSession().setAttribute("userId", null);
        return "index.jsp";
    }
}
