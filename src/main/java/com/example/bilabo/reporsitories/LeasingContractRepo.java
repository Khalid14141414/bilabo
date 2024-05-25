

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

    public List<LeasingContract> fetchAll(){
        return jdbcTemplate.query("SELECT * FROM leasing_contract", rowMapper);
    }

    public List<LeasingContract> fetchFlow1(){
        return jdbcTemplate.query("SELECT * FROM leasing_contract Join car using(vehicle_number) where flow = 1", rowMapper);
    }

    public void createLeasingContract(LeasingContract leasingContract) {
        jdbcTemplate.update("INSERT INTO leasing_contract (start_date, end_date, price, vehicle_number, username, customer_id) VALUES (?, ?, ?, ?, ?, ?)",
                leasingContract.getStart_date(), leasingContract.getEnd_date(), leasingContract.getPrice(),
                leasingContract.getVehicle_number(), leasingContract.getUsername(), leasingContract.getCustomer_id());
    }

    public LeasingContract findContractByid(int contract_id) {
        List<LeasingContract> contracts = jdbcTemplate.query("Select * FROM leasing_contract WHERE contract_id = ?", rowMapper, contract_id);
        return contracts.size() == 1 ? contracts.get(0) : null;
    }

    public LeasingContract findContractByidAndFlow(int contract_id) {
        List<LeasingContract> contracts = jdbcTemplate.query("SELECT * FROM leasing_contract Join car using(vehicle_number) where flow = 1 and contract_id = ?", rowMapper, contract_id);
        return contracts.size() == 1 ? contracts.get(0) : null;
    }
}