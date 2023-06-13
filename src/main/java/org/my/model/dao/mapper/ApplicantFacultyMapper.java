package org.my.model.dao.mapper;

import org.my.model.entities.ApplicantFaculty;
import org.my.model.entities.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicantFacultyMapper implements Mapper<ApplicantFaculty> {
    @Override
    public ApplicantFaculty extractFromResultSet(ResultSet rs) throws SQLException {
        return ApplicantFaculty.builder()
                .id(rs.getLong("id"))
                .userId(rs.getLong("user_id"))
                .facultyId(rs.getLong("faculty_id"))
                .mark1(rs.getInt("mark1"))
                .mark2(rs.getInt("mark2"))
                .mark3(rs.getInt("mark3"))
                .avgCertificateMark(rs.getInt("avg_certificate_mark"))
                .passed(rs.getBoolean("passed"))
                .build();
    }
}
