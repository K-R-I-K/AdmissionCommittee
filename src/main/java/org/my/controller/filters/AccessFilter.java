package org.my.controller.filters;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.my.model.entities.Role;
import org.my.model.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AccessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();
        User user = (User) request.getSession().getAttribute("user");
        if((path.contains("registerForFaculties") || path.contains("setFacultiesForRegistration")) &&
                (user == null || user.getRole() == Role.BLOCKED)){
            logger.warn("Guest tried to register for faculties");
            response.sendRedirect("index.jsp");
        } else if(path.contains("newFaculty") || path.contains("deleteFaculty") ||
                path.contains("editFaculty") || path.contains("getEditFaculty") ||
                path.contains("getUsers") || path.contains("makeAdmin") ||
                path.contains("blockUser") || path.contains("unblockUser")||
                path.contains("permit") || path.contains("forbid")) {
            if (user == null || user.getRole().equals(Role.APPLICANT) || user.getRole().equals(Role.BLOCKED)) {
                logger.warn("Applicant (Blocked) or guest tried to access admin page without permission");
                response.sendRedirect("index.jsp");

            } else {
                logger.info("Admin opened one of the admin pages");
                chain.doFilter(servletRequest,servletResponse);
            }
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
