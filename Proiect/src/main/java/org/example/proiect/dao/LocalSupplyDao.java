package org.example.proiect.dao;

import org.example.proiect.model.LocalSupply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocalSupplyDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocalSupplyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public LocalSupply findByDrugNameAndWardName(String drugName, String wardName) {
        String sql = "SELECT * FROM local_supply WHERE drug_name = ? AND ward_name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{drugName, wardName}, new LocalSupplyRowMapper());
    }

    public List<LocalSupply> findByWardName(String wardName) {
        String sql = "SELECT * FROM local_supply WHERE ward_name = ?";
        return jdbcTemplate.query(sql, new Object[]{wardName}, new LocalSupplyRowMapper());
    }

    private static final class LocalSupplyRowMapper implements RowMapper<LocalSupply> {
        public LocalSupply mapRow(ResultSet rs, int rowNum) throws SQLException {
            LocalSupply localSupply = new LocalSupply();
            // map the result set to the localSupply object
            // make sure to set all the fields
            return localSupply;
        }
    }

    public int save(LocalSupply localSupply) {
        String sql = "INSERT INTO local_suply (ward_id, drug_name, manufacturer, description, side_effects, dosage, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, localSupply.getWard().getId(), localSupply.getDrugName(), localSupply.getManufacturer(), localSupply.getDescription(), localSupply.getSideEffects(), localSupply.getDosage(), localSupply.getQuantity());
    }
}