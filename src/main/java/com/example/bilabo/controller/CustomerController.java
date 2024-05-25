package com.example.bilabo.controller;

import com.example.bilabo.model.Customer;
import com.example.bilabo.service.CustomerService;
import com.example.bilabo.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/opretlejekontrakt")
    public String lejekontrakt (Model model, HttpSession session){
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("customers", customerService.fetchAll());
        return "opretLejekontrakt";
    }

    @PostMapping("/opretenkunde")
    public String createCustomer (Customer c, HttpSession session){
        customerService.createCustomer(c);
        session.setAttribute("kundeoprettet", c);
        return "redirect:/opretNyKundeConfirmed";
    }

    @GetMapping("/opretNyKunde")
    public String CreateNewCustomer(HttpSession session){
        if (!employeeService.checkSession(session)) return "redirect:/";
        return "opretNyKunde";
    }

    @GetMapping("/opretNyKundeConfirmed")
    public String newCustomerCreated(HttpSession session, Model model){
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("customer", session.getAttribute("kundeoprettet"));
        return "opretNyKundeConfirmed";
    }

    @GetMapping("/opdaterkunde/{customer_id}")
    public String updateCustomer(@PathVariable("customer_id") int customer_id, Model model, HttpSession session){
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("opdater", customerService.findId(customer_id));
        return "opdaterKunde";
    }

    @PostMapping("/opdaterkunden")
    public String updateTheCustomer(Customer c,int customer_id){
        customerService.updateCustomer(c, customer_id);
        return "redirect:/opretlejekontrakt";
    }


        @GetMapping("/findCustomerId/{email}")
        public String findCustomerId(@PathVariable("email") String email, Model model) {
            String customerId = customerService.findCustomerid(email);
            model.addAttribute("customerId", customerId);
            return "customerIdView"; // return the name of your view
        }
    }
