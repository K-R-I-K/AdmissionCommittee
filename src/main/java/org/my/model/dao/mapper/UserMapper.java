package org.my.model.dao.mapper;

import org.my.model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .role(rs.getString("role"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .city(rs.getString("city"))
                .region(rs.getString("region"))
                .educationalInstitution(rs.getString("educational_institution"))
                .build();
    }
}