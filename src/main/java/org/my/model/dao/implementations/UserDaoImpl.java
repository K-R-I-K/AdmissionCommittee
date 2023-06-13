package org.my.model.dao.implementations;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.my.model.dao.UserDao;
import org.my.model.dao.mapper.UserMapper;
import org.my.model.dao.util.SHA512Utils;
import org.my.model.dao.util.db.DBException;
import org.my.model.entities.User;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private final Connection connection;
    private final UserMapper userMapper;
    public UserDaoImpl(Connection connection) {
        this.connection = connection;
        userMapper = new UserMapper();
    }

    @Override
    public void add(User entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (login, password, role, name, email, city, region, educational_institution) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, entity.getLogin());
            statement.setString(2, SHA512Utils.toHexString(SHA512Utils.getSHA(entity.getPassword())));
            statement.setString(3, entity.getRole().toString());
            statement.setString(4, entity.getName());
            statement.setString(5, entity.getEmail());
            statement.setString(6, entity.getCity());
            statement.setString(7, entity.getRegion());
            statement.setString(8, entity.getEducationalInstitution());

            statement.executeUpdate();
        } catch (SQLException | NoSuchAlgorithmException  e){
            logger.error("Cannot save User." + e);
            throw new DBException("Invalid User input", e);
        }
    }

    @Override
    public User findById(Long id) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return userMapper.extractFromResultSet(resultSet);
            }
            return null;
        }catch (SQLException e){
            logger.error("Cannot find User." + e);
            throw new DBException("Cannot find User", e);
        }
    }

    @Override
    public List<User> findAll() {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            List<User> usersList = new ArrayList<>();
            while(resultSet.next()){
                usersList.add(userMapper.extractFromResultSet(resultSet));
            }
            return usersList;
        }catch (SQLException e){
            logger.error("Cannot find all Users." + e);
            throw new DBException("Cannot find all Users", e);
        }
    }

    @Override
    public void update(User entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET login=?, password=?, role=?, name=?, email=?, city=?, region=?, educational_institution=? WHERE id=?");
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getRole().toString());
            statement.setString(4, entity.getName());
            statement.setString(5, entity.getEmail());
            statement.setString(6, entity.getCity());
            statement.setString(7, entity.getRegion());
            statement.setString(8, entity.getEducationalInstitution());
            statement.setLong(9, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot update User." + e);
            throw new DBException("Cannot update User", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot delete User." + e);
            throw new DBException("Cannot delete User", e);
        }
    }

    @Override
    public void deleteEntity(User entity) {
        delete(entity.getId());
    }

    @Override
    public boolean isExisting(User entity) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
            preparedStatement.setString(1, entity.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            logger.error("Cannot check is exist User." + e);
            throw new DBException("Cannot check is exist User", e);
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
    public User findByLoginAndPassword(String login, String password) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, SHA512Utils.toHexString(SHA512Utils.getSHA(password)));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return userMapper.extractFromResultSet(resultSet);
            }
            return null;
        }catch (SQLException | NoSuchAlgorithmException e){
            logger.error("Cannot find User by login and password." + e);
            throw new DBException("Cannot find User by login and password", e);
        }
    }
}
