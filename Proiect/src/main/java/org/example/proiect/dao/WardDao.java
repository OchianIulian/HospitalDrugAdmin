package org.example.proiect.dao;

import org.example.proiect.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class WardDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Ward findByName(String name) {
        String sql = "SELECT * FROM wards WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new WardRowMapper());
    }

    private static final class WardRowMapper implements RowMapper<Ward> {
        public Ward mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ward ward = new Ward();
            // map the result set to the ward object
            // make sure to set all the fields
            return ward;
        }
    }

    public int save(Ward ward) {
        String sql = "INSERT INTO wards (name) VALUES (?)";
        return jdbcTemplate.update(sql, ward.getName());
    }
}