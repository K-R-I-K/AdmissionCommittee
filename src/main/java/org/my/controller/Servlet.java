package org.my.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.my.controller.command.Command;
import org.my.controller.command.CommandContainer;
import org.my.model.entities.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class Servlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Servlet.class);

    @Override
    public void init() {
        logger.info("Servlet.init");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = "index.jsp";

        request.getSession().setAttribute("admin", Role.ADMIN);
        request.getSession().setAttribute("applicant", Role.APPLICANT);
        request.getSession().setAttribute("blocked", Role.BLOCKED);

        String commandName = request.getParameter("command");
        logger.info("Servlet.doGet; commandName ==> " + commandName);

        if(commandName == null) {
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            Command command = CommandContainer.getCommand(commandName);
            page = CommandContainer.doCommand(command, request);

            if (page.contains("redirect")) {
                response.sendRedirect(page.replace("redirect:",""));
            } else {
                request.getRequestDispatcher(page).forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        logger.info("Servlet.destroy");
    }
}
