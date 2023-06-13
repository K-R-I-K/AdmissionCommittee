package org.my.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguage implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("language", String.valueOf(request.getParameter("language")));
        return "index.jsp";
    }
}
