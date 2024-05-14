package com.example.bilabo.reporsitories;

import com.example.bilabo.model.RentalAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class RentalAgreementRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RentalAgreementRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RentalAgreement findById(int rentalAgreementId) {
        String sql = "SELECT * FROM leje_aftaler WHERE lejeaftale_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{rentalAgreementId}, this::mapRowToRentalAgreement);
    }

    public List<RentalAgreement> findAll() {
        String sql = "SELECT * FROM leje_aftaler";
        return jdbcTemplate.query(sql, this::mapRowToRentalAgreement);
    }

    public void deleteById(int rentalAgreementId) {
        String sql = "DELETE FROM leje_aftaler WHERE lejeaftale_id = ?";
        jdbcTemplate.update(sql, rentalAgreementId);
    }

    public RentalAgreement save(RentalAgreement rentalAgreement) {
        String sql = "INSERT INTO leje_aftaler (bil_id, kunde_id, lejeperiode_start, lejeperiode_slut, afhentning_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, rentalAgreement.getCarID(), rentalAgreement.getCustomerID(),
                rentalAgreement.getRentalAgreementBegin(), rentalAgreement.getRentalAgreementEnds(),
                rentalAgreement.getPickupID());
        return rentalAgreement;
    }

    private RentalAgreement mapRowToRentalAgreement(ResultSet resultSet, int rowNum) throws SQLException {
        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setRentalAgreementID(resultSet.getInt("lejeaftale_id"));
        rentalAgreement.setCarID(resultSet.getInt("bil_id"));
        rentalAgreement.setCustomerID(resultSet.getInt("kunde_id"));
        rentalAgreement.setRentalAgreementBegin(resultSet.getDate("lejeperiode_start").toLocalDate());
        rentalAgreement.setRentalAgreementEnds(resultSet.getDate("lejeperiode_slut").toLocalDate());
        rentalAgreement.setPickupID(resultSet.getInt("afhentning_id"));
        return rentalAgreement;
    }
}
