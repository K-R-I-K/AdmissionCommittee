package org.my.controller.command;

import org.my.model.dao.FacultyDao;
import org.my.model.entities.Faculty;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetFaculties implements Command {
    private FacultyDao facultyDao;
    public final static int PAGE_SIZE = 5;
    public GetFaculties(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("sortingTypes", List.of(new String[]{"byName(a-z)", "byName(z-a)",
                "byNumberOfBudgetPlaces", "byTheTotalNumberOfPlaces"}));
        String sortingType;
        if(request.getParameter("sortingType") == null) {
            sortingType = "byName(a-z)";
        } else {
            sortingType = String.valueOf(request.getParameter("sortingType"));
        }
        request.getSession().setAttribute("sortingType", sortingType);
        /*List<Faculty> faculties = facultyDao.findAll();
        switch (sortingType) {
            case "byName(a-z)" -> faculties.sort(Comparator.comparing(Faculty::getName));
            case "byName(z-a)" -> faculties.sort((f, s) -> s.getName().compareTo(f.getName()));
            case "byNumberOfBudgetPlaces" -> faculties.sort(Comparator.comparing(Faculty::getBudgetPlaces));
            case "byTheTotalNumberOfPlaces" -> faculties.sort(Comparator.comparing(Faculty::getTotalPlaces));
        }*/
        int page;
        if(request.getParameter("page") == null) {
            page = 1;
        } else {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(page <= 0) {
            page = 1;
        }
        /*int start = (page-1)*pageSize;
        int end = Math.min(start + pageSize, faculties.size());
        if(start > end) {
            faculties =  Collections.EMPTY_LIST;
        } else {
            faculties = faculties.subList(start, end);
        }*/
        request.getSession().setAttribute("faculties", facultyDao.getFacultiesWithSorting(sortingType, page));
        request.getSession().setAttribute("isNext", facultyDao.getFacultiesWithSorting(sortingType, page + 1).size() > 0);
        return "faculties.jsp";
    }
}
