package org.example.proiect.dao;

import org.example.proiect.model.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DrugsStorageDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DrugsStorageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Drug findByName(String name) {
        String sql = "SELECT * FROM drugs WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new DrugRowMapper());
    }

    private static final class DrugRowMapper implements RowMapper<Drug> {
        public Drug mapRow(ResultSet rs, int rowNum) throws SQLException {
            Drug drug = new Drug();
            drug.setId(rs.getLong("id"));
            drug.setName(rs.getString("name"));
            // set other fields
            return drug;
        }
    }

    public int save(Drug drug) {
        String sql = "INSERT INTO drug_storage (name, manufacturer, description, side_effects, dosage, quantity) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, drug.getName(), drug.getManufacturer(), drug.getDescription(), drug.getSideEffects(), drug.getDosage(), drug.getQuantity());
    }
}