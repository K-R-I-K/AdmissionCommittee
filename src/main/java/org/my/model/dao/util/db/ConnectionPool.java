package org.my.model.dao.util.db;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class  ConnectionPool {
    private static volatile DataSource dataSource;

    public static Connection getConnection() throws DBException {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName("org.postgresql.Driver");
                    ds.setUrl("jdbc:postgresql://localhost:5432/admissionCommittee?characterEncoding=UTF-8");
                    ds.setUsername("postgres");
                    ds.setPassword("1234");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        try {
            return dataSource.getConnection();
        }  catch (SQLException e) {
            throw new DBException("Failed to get connection", e);
        }
    }
}