package com.example.bilabo.controller;

import com.example.bilabo.model.Employee;
import com.example.bilabo.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    // Viser en liste over alle ansatte.
    @GetMapping("/personale")
    public String getAllEmployees(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("admin", session.getAttribute("adminlogin"));
        model.addAttribute("employees", employeeService.fetchAllEmployees());
        return "personale";
    }

    // Viser siden til oprettelse af en ny ansat, kun hvis brugeren er admin.
    @GetMapping("/opretPersonale")
    public String opretPersonale(HttpSession session) {
        return (employeeService.findAdminUser((String) session.getAttribute("username")) == null) ? "redirect:/personale" : "opretPersonale";
    }

    // Opretter en ny ansat og tilføjer dem til listen over ansatte.
    @PostMapping("/opretPersonaler")
    public String opretPersonaler(Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/personale";
    }

    // Fjerner en ansat baseret på deres brugernavn.
    @GetMapping("/personale/{username}")
    public String fireEmployee(@PathVariable("username") String username, HttpSession session){
        return (employeeService.findAdminUser((String) session.getAttribute("username")) == null) ?
                "redirect:/personale" : "redirect:/personale";
    }

    // Henter oplysninger om en specifik ansat for at opdatere dem.
    @GetMapping("/opdaterPersonale/{username}")
    public String findByUsername(@PathVariable("username") String username, Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("employee", employeeService.findByUsername(username));
        session.setAttribute("urlusername", employeeService.findByUsername(username).getUsername());
        return "opdaterPersonale";
    }

    // Opdaterer en ansats oplysninger i systemet.
    @PostMapping("/opdateretPersonale")
    public String opdateretPersonal(Employee employee, int is_active, int is_admin,
                                    HttpSession session, RedirectAttributes redirectAttributes) {
        String usernames = (String) session.getAttribute("urlusername");
        if(is_active != 0 && is_active != 1 && is_admin != 0 && is_admin != 1  ){
            redirectAttributes.addFlashAttribute("fejl", "Admin value should be 0 or 1");
            redirectAttributes.addFlashAttribute("fejl2", "Active value should be 0 or 1");
            return "redirect:/opdaterPersonale/" + usernames;
        }
        else if (is_active != 0 && is_active != 1) {
            redirectAttributes.addFlashAttribute("fejl", "Active value should be 0 or 1");
            return "redirect:/opdaterPersonale/" + usernames;
        }
        else if (is_admin != 0 && is_admin != 1) {
            redirectAttributes.addFlashAttribute("fejl2", "Admin value should be 0 or 1");
            return "redirect:/opdaterPersonale/" + usernames;
        }else {
            employeeService.updateEmployee(employee);
            return "redirect:/personale";
        }
    }
}
