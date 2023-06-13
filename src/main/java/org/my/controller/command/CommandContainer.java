package org.my.controller.command;

import org.my.model.dao.ApplicantFacultyDao;
import org.my.model.dao.DaoFactory;
import org.my.model.dao.FacultyDao;
import org.my.model.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Map<String, Command> commands;

    private static final DaoFactory daoFactory = DaoFactory.getInstance();

    private static final FacultyDao facultyDao = daoFactory.createFacultyDao();
    private static final UserDao userDao = daoFactory.createUserDao();
    private static final ApplicantFacultyDao applicantFacultyDao = daoFactory.createApplicantFacultyDao();


    private CommandContainer() {
    }

    static {
        commands = new HashMap<>();
        commands.put("getFaculties", new GetFaculties(facultyDao));
        commands.put("deleteFaculty", new DeleteFaculty(facultyDao));
        commands.put("addFaculty", new AddFaculty(facultyDao));
        commands.put("getEditFaculty", new GetEditFaculty(facultyDao));
        commands.put("editFaculty", new EditFaculty(facultyDao));
        commands.put("getFaculty", new GetFaculty(facultyDao, applicantFacultyDao, userDao));
        commands.put("logIn", new LogIn(userDao));
        commands.put("logOut", new LogOut());
        commands.put("registration", new Registration(userDao));
        commands.put("setFacultiesForRegistration", new SetFacultiesForRegistration(facultyDao, applicantFacultyDao));
        commands.put("registerForFaculties", new RegisterForFaculties(applicantFacultyDao, facultyDao));
        commands.put("getUsers", new GetUsers(userDao));
        commands.put("makeAdmin", new MakeAdmin(userDao));
        commands.put("blockUser", new BlockUser(userDao));
        commands.put("unblockUser", new UnblockUser(userDao));
        commands.put("permit", new Permit(applicantFacultyDao, userDao, facultyDao));
        commands.put("forbid", new Forbid(applicantFacultyDao, userDao, facultyDao));
        commands.put("changeLanguage", new ChangeLanguage());
    }

    public static Command getCommand(String command) {
        return commands.get(command);
    }

    public static String doCommand(Command command, HttpServletRequest request) {
        return command.execute(request);
    }
}
