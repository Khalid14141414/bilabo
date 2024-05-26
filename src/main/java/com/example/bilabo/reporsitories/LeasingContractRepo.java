

package com.example.bilabo.reporsitories;

import com.example.bilabo.model.LeasingContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeasingContractRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RowMapper<LeasingContract> rowMapper = new BeanPropertyRowMapper<>(LeasingContract.class);

    // Henter alle leasingkontrakter fra databasen
    public List<LeasingContract> fetchAll() {
        return jdbcTemplate.query("SELECT * FROM leasing_contract", rowMapper);
    }

    // Henter leasingkontrakter med flow = 1 ved at sammenkæde med biloplysninger
    public List<LeasingContract> fetchFlow1() {
        return jdbcTemplate.query("SELECT * FROM leasing_contract JOIN car USING(vehicle_number) WHERE flow = 1", rowMapper);
    }

    // Opretter en ny leasingkontrakt i databasen
    public void createLeasingContract(LeasingContract leasingContract) {
        jdbcTemplate.update("INSERT INTO leasing_contract (start_date, end_date, price, vehicle_number, username, customer_id) VALUES (?, ?, ?, ?, ?, ?)",
                leasingContract.getStart_date(), leasingContract.getEnd_date(), leasingContract.getPrice(),
                leasingContract.getVehicle_number(), leasingContract.getUsername(), leasingContract.getCustomer_id());
    }

    // Finder en leasingkontrakt i databasen ved dens kontrakt-id
    public LeasingContract findContractByid(int contract_id) {
        List<LeasingContract> contracts = jdbcTemplate.query("SELECT * FROM leasing_contract WHERE contract_id = ?", rowMapper, contract_id);
        return contracts.size() == 1 ? contracts.get(0) : null;
    }

    // Finder en leasingkontrakt med flow = 1 i databasen ved dens kontrakt-id
    public LeasingContract findContractByidAndFlow(int contract_id) {
        List<LeasingContract> contracts = jdbcTemplate.query("SELECT * FROM leasing_contract JOIN car USING(vehicle_number) WHERE flow = 1 AND contract_id = ?", rowMapper, contract_id);
        return contracts.size() == 1 ? contracts.get(0) : null;
    }
}
