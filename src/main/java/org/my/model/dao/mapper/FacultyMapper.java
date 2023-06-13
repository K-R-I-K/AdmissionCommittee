package org.my.model.dao.mapper;

import org.my.model.entities.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyMapper implements Mapper<Faculty> {

    public Faculty extractFromResultSet(ResultSet rs) throws SQLException {
        return Faculty.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .budgetPlaces(rs.getLong("budget_places"))
                .totalPlaces(rs.getLong("total_places"))
                .build();
    }
}
