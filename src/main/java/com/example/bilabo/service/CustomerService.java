package com.example.bilabo.service;

import com.example.bilabo.model.Customer;
import com.example.bilabo.model.Employee;
import com.example.bilabo.reporsitories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    // Opretter en ny kunde
    public void createCustomer(Customer customer) {
        customerRepo.createCustomer(customer);
    }

    // Finder en kunde ved id
    public Customer findId(int id) {
        return customerRepo.findCustomerByid(id);
    }

    // Finder kunde-id ved e-mail
    public String findCustomerid(String email) {
        return customerRepo.findIdByEmail(email);
    }

    // Henter alle kunder
    public List<Customer> fetchAll() {
        return customerRepo.fetchAll();
    }

    // Opdaterer en kunde ved id
    public void updateCustomer(Customer customer, int customer_id) {
        customerRepo.updateCustomer(customer, customer_id);
    }
}
