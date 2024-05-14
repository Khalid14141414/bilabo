package com.example.bilabo.reporsitories;

import com.example.bilabo.model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DamageReportRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DamageReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DamageReport save(DamageReport damageReport) {
        String sql = "INSERT INTO skade_report (lejeaftale_id, beskrivelse, pris) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, damageReport.getRentalAgreementID(), damageReport.getDescription(), damageReport.getPrice());
        return damageReport;
    }

    public DamageReport findById(int damageId) {
        String sql = "SELECT * FROM skade_report WHERE skade_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{damageId}, this::mapRowToDamageReport);
    }

    public List<DamageReport> findAll() {
        String sql = "SELECT * FROM skade_report";
        return jdbcTemplate.query(sql, this::mapRowToDamageReport);
    }

    public void deleteById(int damageId) {
        String sql = "DELETE FROM skade_report WHERE skade_id = ?";
        jdbcTemplate.update(sql, damageId);
    }

    private DamageReport mapRowToDamageReport(ResultSet resultSet, int rowNum) throws SQLException {
        DamageReport damageReport = new DamageReport();
        damageReport.setDamageID(resultSet.getInt("skade_id"));
        damageReport.setRentalAgreementID(resultSet.getInt("lejeaftale_id"));
        damageReport.setDescription(resultSet.getString("beskrivelse"));
        damageReport.setPrice(resultSet.getInt("pris"));
        return damageReport;
    }
}

