package com.example.bilabo.reporsitories;

import com.example.bilabo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Customer save(Customer customer) {
        String sql = "INSERT INTO kunde (navn, email, telefon) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), customer.getTelefonNr());
        return customer;
    }

    public Customer findById(int customerId) {
        String sql = "SELECT * FROM kunde WHERE kunde_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{customerId}, this::mapRowToCustomer);
    }

    public List<Customer> findAll() {
        String sql = "SELECT * FROM kunde";
        return jdbcTemplate.query(sql, this::mapRowToCustomer);
    }

    public void deleteById(int customerId) {
        String sql = "DELETE FROM kunde WHERE kunde_id = ?";
        jdbcTemplate.update(sql, customerId);
    }

    private Customer mapRowToCustomer(ResultSet resultSet, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerID(resultSet.getInt("kunde_id"));
        customer.setName(resultSet.getString("navn"));
        customer.setEmail(resultSet.getString("email"));
        customer.setTelefonNr(resultSet.getInt("telefon"));
        return customer;
    }
}

