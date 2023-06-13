package org.my.model.dao.implementations;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.my.model.dao.ApplicantFacultyDao;
import org.my.model.dao.mapper.ApplicantFacultyMapper;
import org.my.model.dao.util.db.DBException;
import org.my.model.entities.ApplicantFaculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicantFacultyDaoImpl implements ApplicantFacultyDao {
    private static final Logger logger = LogManager.getLogger(ApplicantFacultyDaoImpl.class);
    private final Connection connection;
    private final ApplicantFacultyMapper applicantFacultyMapper;

    public ApplicantFacultyDaoImpl(Connection connection) {
        this.connection = connection;
        applicantFacultyMapper = new ApplicantFacultyMapper();
    }

    @Override
    public void add(ApplicantFaculty entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO applicant_faculty (user_id, faculty_id, mark1, mark2, mark3, avg_certificate_mark, passed) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?)");
            statement.setLong(1, entity.getUserId());
            statement.setLong(2, entity.getFacultyId());
            statement.setInt(3, entity.getMark1());
            statement.setInt(4, entity.getMark2());
            statement.setInt(5, entity.getMark3());
            statement.setDouble(6, entity.getAvgCertificateMark());
            statement.setBoolean(7, entity.isPassed());

            statement.executeUpdate();
        } catch (SQLException e){
            logger.error("Cannot save ApplicantFaculty." + e);
            throw new DBException("Invalid ApplicantFaculty input", e);
        }
    }

    @Override
    public ApplicantFaculty findById(Long id) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM applicant_faculty WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return applicantFacultyMapper.extractFromResultSet(resultSet);
            }
            return null;
        }catch (SQLException e){
            logger.error("Cannot find ApplicantFaculty." + e);
            throw new DBException("Cannot find ApplicantFaculty", e);
        }
    }

    @Override
    public List<ApplicantFaculty> findAll() {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM applicant_faculty");
            ResultSet resultSet = statement.executeQuery();
            List<ApplicantFaculty> applicantFacultyList = new ArrayList<>();
            while(resultSet.next()){
                applicantFacultyList.add(applicantFacultyMapper.extractFromResultSet(resultSet));
            }
            return applicantFacultyList;
        }catch (SQLException e){
            logger.error("Cannot find all ApplicantFaculties." + e);
            throw new DBException("Cannot find all ApplicantFaculties", e);
        }
    }

    @Override
    public void update(ApplicantFaculty entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE applicant_faculty SET user_id=?, faculty_id=?, mark1=?, mark2=?, mark3=?, avg_certificate_mark=?, passed=? WHERE id=?");
            statement.setLong(1, entity.getUserId());
            statement.setLong(2, entity.getFacultyId());
            statement.setInt(3, entity.getMark1());
            statement.setInt(4, entity.getMark2());
            statement.setInt(5, entity.getMark3());
            statement.setDouble(6, entity.getAvgCertificateMark());
            statement.setBoolean(7, entity.isPassed());
            statement.setLong(8, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot update ApplicantFaculty." + e);
            throw new DBException("Cannot update ApplicantFaculty", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM applicant_faculty WHERE id=?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot delete ApplicantFaculty." + e);
            throw new DBException("Cannot delete ApplicantFaculty", e);
        }
    }

    @Override
    public void deleteEntity(ApplicantFaculty entity) {
        delete(entity.getId());
    }

    @Override
    public boolean isExisting(ApplicantFaculty entity) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM applicant_faculty WHERE user_id=? AND faculty_id=?");
            preparedStatement.setLong(1, entity.getUserId());
            preparedStatement.setLong(2, entity.getFacultyId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            logger.error("Cannot check is exist ApplicantFaculty." + e);
            throw new DBException("Cannot check is exist ApplicantFaculty", e);
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
    public List<ApplicantFaculty> findByFacultyId(Long facultyId) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM applicant_faculty WHERE faculty_id = ?");
            preparedStatement.setLong(1, facultyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ApplicantFaculty> applicantFacultyList = new ArrayList<>();
            while(resultSet.next()){
                applicantFacultyList.add(applicantFacultyMapper.extractFromResultSet(resultSet));
            }
            return applicantFacultyList;
        }catch (SQLException e){
            logger.error("Cannot find all ApplicantFaculties by facultyId." + e);
            throw new DBException("Cannot find all ApplicantFaculties by facultyId", e);
        }
    }

    @Override
    public List<Long> getFacultiesWithApplicant(Long userID) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM applicant_faculty WHERE user_id=?");
            preparedStatement.setLong(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Long> facultiesList = new ArrayList<>();
            while(resultSet.next()){
                facultiesList.add(resultSet.getLong("faculty_id"));
            }
            return facultiesList;
        }catch (SQLException e){
            logger.error("Cannot find all Faculties with userId." + e);
            throw new DBException("Cannot find all Faculties with userId", e);
        }
    }

    @Override
    public ApplicantFaculty findByUserAndFaculty(Long userId, Long facultyId) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM applicant_faculty WHERE user_id = ? AND faculty_id=?");
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, facultyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return applicantFacultyMapper.extractFromResultSet(resultSet);
            }
            return null;
        }catch (SQLException e){
            logger.error("Cannot find ApplicantFaculty." + e);
            throw new DBException("Cannot find ApplicantFaculty", e);
        }
    }
}
