package org.my.model.dao.implementations;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.my.controller.command.GetFaculties;
import org.my.model.dao.FacultyDao;
import org.my.model.dao.mapper.FacultyMapper;
import org.my.model.dao.util.db.DBException;
import org.my.model.entities.Faculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyDaoImpl implements FacultyDao {
    private static final Logger logger = LogManager.getLogger(FacultyDaoImpl.class);
    private final Connection connection;
    private final FacultyMapper facultyMapper;
    public FacultyDaoImpl(Connection connection) {
        this.connection = connection;
        facultyMapper = new FacultyMapper();
    }

    @Override
    public void add(Faculty entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO faculties (name, budget_places, total_places) VALUES(?, ?, ?)");
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getBudgetPlaces());
            statement.setLong(3, entity.getTotalPlaces());

            statement.executeUpdate();
        } catch (SQLException e){
            logger.error("Cannot save Faculty." + e);
            throw new DBException("Invalid Faculty input", e);
        }
    }

    @Override
    public Faculty findById(Long id) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM faculties WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return facultyMapper.extractFromResultSet(resultSet);
            }
            return null;
        }catch (SQLException e){
            logger.error("Cannot find Faculty." + e);
            throw new DBException("Cannot find Faculty", e);
        }
    }

    @Override
    public List<Faculty> findAll() {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM faculties");
            ResultSet resultSet = statement.executeQuery();
            List<Faculty> facultiesList = new ArrayList<>();
            while(resultSet.next()){
                facultiesList.add(facultyMapper.extractFromResultSet(resultSet));
            }
            return facultiesList;
        }catch (SQLException e){
            logger.error("Cannot find all Faculties." + e);
            throw new DBException("Cannot find all Faculties", e);
        }
    }

    @Override
    public void update(Faculty entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE faculties SET name=?, budget_places=?, total_places=? WHERE id=?");
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getBudgetPlaces());
            statement.setLong(3, entity.getTotalPlaces());
            statement.setLong(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot update Faculty." + e);
            throw new DBException("Cannot update Faculty", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM faculties WHERE id=?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot delete Faculty." + e);
            throw new DBException("Cannot delete Faculty", e);
        }
    }

    @Override
    public void deleteEntity(Faculty entity) {
        delete(entity.getId());
    }

    @Override
    public boolean isExisting(Faculty entity) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM faculties WHERE name = ?");
            preparedStatement.setString(1, entity.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            logger.error("Cannot check is exist Faculty." + e);
            throw new DBException("Cannot check is exist Faculty", e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Cannot close the connection." + e);
            throw new DBException("Cannot close the connection", e);
        }
    }

    @Override
    public Faculty findByName(String name) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM faculties WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return facultyMapper.extractFromResultSet(resultSet);
            }
            return null;
        }catch (SQLException e){
            logger.error("Cannot find Faculty." + e);
            throw new DBException("Cannot find Faculty", e);
        }
    }

    @Override
    public List<Faculty> getFacultiesWithSorting(String sortingType, int page) {
        try{
            String sqlSortingType = switch (sortingType) {
                case "byName(a-z)" -> " f.name asc ";
                case "byName(z-a)" -> " f.name desc ";
                case "byNumberOfBudgetPlaces" -> " f.budget_places asc ";
                case "byTheTotalNumberOfPlaces" -> " f.total_places asc";
                default -> "f.name asc ";
            };
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM faculties f " +
                    "ORDER BY " + sqlSortingType +
                    " LIMIT " + GetFaculties.PAGE_SIZE + " OFFSET ?");
            statement.setInt(1, (page - 1) * GetFaculties.PAGE_SIZE);
            ResultSet resultSet = statement.executeQuery();
            List<Faculty> facultiesList = new ArrayList<>();
            while(resultSet.next()){
                facultiesList.add(facultyMapper.extractFromResultSet(resultSet));
            }
            return facultiesList;
        }catch (SQLException e){
            logger.error("Cannot find all Faculties." + e);
            throw new DBException("Cannot find all Faculties", e);
        }
    }
}
