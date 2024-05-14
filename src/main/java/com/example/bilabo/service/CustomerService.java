package com.example.bilabo.service;

import com.example.bilabo.model.Customer;
import com.example.bilabo.reporsitories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findCustomerById(int customerId) {
        return customerRepository.findById(customerId);
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomerById(int customerId) {
        customerRepository.deleteById(customerId);
    }
}

