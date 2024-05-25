package com.example.bilabo.controller;

import com.example.bilabo.model.Employee;
import com.example.bilabo.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        model.addAttribute("username", session.getAttribute("username"));
        return employeeService.checkSession(session) ? "home" : "redirect:/home";
    }

    @PostMapping("/login")
    public String loginAccount(String username, String user_password, Model model, HttpSession session) {
        Employee employee = employeeService.findbyuserandpassword(username,user_password);
        session.setAttribute("adminlogin", employee);

        if (employee != null && employee.getIs_active()==1){
            session.setAttribute("username", username);
            return "redirect:/home";
        } else {
            model.addAttribute("invalid", "bruger findes ikke");
            return "login";
        }
    }
}