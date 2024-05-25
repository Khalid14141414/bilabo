package com.example.bilabo.reporsitories;

import com.example.bilabo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate template;

    private RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);

    public void createCustomer(Customer c){
        template.update("INSERT INTO customer (customer_id,full_name, email, phone, address, cpr) VALUES(?,?,?,?,?,?)",
                c.getCustomer_id(),  c.getFull_name(), c.getEmail(), c.getPhone(), c.getAddress(), c.getCpr());
    }

    public List<Customer> fetchAll() {
        return template.query("SELECT * FROM customer", rowMapper);
    }

    public void updateCustomer(Customer customer, int customer_id) {
        template.update("UPDATE customer SET full_name = ?, email = ?, phone = ?, address = ?, cpr = ? WHERE customer_id = ?",
                customer.getFull_name(), customer.getEmail(), customer.getPhone(), customer.getAddress(), customer.getCpr(), customer.getCustomer_id());
    }

    public Customer findCustomerByid(int customer_id){
        return template.queryForObject("Select * FROM customer WHERE customer_id = ?", rowMapper, customer_id);
    }

    public String findIdByEmail(String email){
        return template.queryForObject("Select customer_id FROM customer WHERE email = ?", String.class, email);
    }
}