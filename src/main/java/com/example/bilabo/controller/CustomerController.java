package com.example.bilabo.controller;
import com.example.bilabo.model.Customer;
import com.example.bilabo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String showAllCustomers(Model model) {
        List<Customer> customers = customerService.findAllCustomers();
        model.addAttribute("customers", customers);
        return "customer-list"; // Assuming you have a corresponding HTML template
    }

    @GetMapping("/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-customer"; // Assuming you have a corresponding HTML template
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("/details")
    public String showCustomerDetails(@RequestParam("id") int customerId, Model model) {
        Customer customer = customerService.findCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "customer-details"; // Assuming you have a corresponding HTML template
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int customerId) {
        customerService.deleteCustomerById(customerId);
        return "redirect:/customers/list";
    }
}
