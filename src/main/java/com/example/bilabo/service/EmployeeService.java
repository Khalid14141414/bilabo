package com.example.bilabo.service;

import com.example.bilabo.model.Employee;
import com.example.bilabo.reporsitories.EmployeeRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;

    // Henter alle medarbejdere
    public List<Employee> fetchAllEmployees() {
        return employeeRepo.fetchAll();
    }

    // Opretter en ny medarbejder
    public void createEmployee(Employee employee) {
        employeeRepo.addEmployee(employee);
    }

    // Fyrer en medarbejder ved at deaktivere brugeren
    public void fireEmployee(String username) {
        employeeRepo.fireEmployee(username);
    }

    // Opdaterer en medarbejder
    public void updateEmployee(Employee employee) {
        employeeRepo.updateEmployee(employee);
    }

    // Finder en administrator ved brugernavn
    public Employee findAdminUser(String username) {
        return employeeRepo.findAdmin(username);
    }

    // Finder en medarbejder ved brugernavn
    public Employee findByUsername(String username) {
        return employeeRepo.findByUsername(username);
    }

    // Finder en medarbejder ved brugernavn og adgangskode
    public Employee findbyuserandpassword(String username, String user_password) {
        return employeeRepo.findByUserAndPassword(username, user_password);
    }

    // Tjekker om en administratorkonto er logget ind via session
    public Boolean checkSession(HttpSession httpSession) {
        return httpSession.getAttribute("adminlogin") != null;
    }
}
