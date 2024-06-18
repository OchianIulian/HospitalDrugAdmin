package org.example.proiect.dao;

import org.example.proiect.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByFullName(String name) {
        String sql = "SELECT * FROM users WHERE full_name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new UserRowMapper());
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserRowMapper());
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static final class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            // map the result set to the user object
            // make sure to set all the fields
            return user;
        }
    }

    public int save(User user) {
        String sql = "INSERT INTO medical_personnel (full_name, email, cnp, role) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getFullName(), user.getEmail(), user.getCnp(), user.getRole().toString());
    }
}