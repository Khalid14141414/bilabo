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

    // Viser siden til oprettelse af lejekontrakt og henter alle kunder.
    @GetMapping("/opretlejekontrakt")
    public String lejekontrakt (Model model, HttpSession session){
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("customers", customerService.fetchAll());
        return "opretLejeKontrakt";
    }

    // Opretter en ny kunde og gemmer den i sessionen.
    @PostMapping("/opretenkunde")
    public String createCustomer (Customer c, HttpSession session){
        customerService.createCustomer(c);
        session.setAttribute("kundeoprettet", c);
        return "redirect:/opretNyKundeConfirmed";
    }

    // Viser siden til oprettelse af en ny kunde.
    @GetMapping("/opretNyKunde")
    public String CreateNewCustomer(HttpSession session){
        if (!employeeService.checkSession(session)) return "redirect:/";
        return "opretNyKunde";
    }

    // Bekræfter oprettelsen af en ny kunde og viser kundens oplysninger.
    @GetMapping("/opretNyKundeConfirmed")
    public String newCustomerCreated(HttpSession session, Model model){
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("customer", session.getAttribute("kundeoprettet"));
        return "opretNyKundeConfirmed";
    }

    // Henter oplysninger om en kunde for at opdatere dem.
    @GetMapping("/opdaterkunde/{customer_id}")
    public String updateCustomer(@PathVariable("customer_id") int customer_id, Model model, HttpSession session){
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("opdater", customerService.findId(customer_id));
        return "opdaterKunde";
    }

    // Opdaterer en kundes oplysninger i systemet.
    @PostMapping("/opdaterkunden")
    public String updateTheCustomer(Customer c, int customer_id){
        customerService.updateCustomer(c, customer_id);
        return "redirect:/opretLejeKontrakt";
    }

    // Finder en kundes ID baseret på deres e-mail.
    @GetMapping("/findCustomerId/{email}")
    public String findCustomerId(@PathVariable("email") String email, Model model) {
        String customerId = customerService.findCustomerid(email);
        model.addAttribute("customerId", customerId);
        return "customerIdView"; // return the name of your view
    }
}
