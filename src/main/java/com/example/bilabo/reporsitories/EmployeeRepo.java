package com.example.bilabo.reporsitories;

import com.example.bilabo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepo {

    @Autowired
    JdbcTemplate template;

    private RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);

    // Finder en medarbejder ved brugernavn og adgangskode
    public Employee findByUserAndPassword(String username, String user_password) {
        List<Employee> employees = template.query("SELECT * FROM Employee WHERE username=? AND user_password=?", rowMapper, username, user_password);
        return employees.size() == 1 ? employees.get(0) : null;
    }

    // Henter alle medarbejdere fra databasen
    public List<Employee> fetchAll() {
        return template.query("SELECT * FROM Employee", rowMapper);
    }

    // Tilføjer en ny medarbejder til databasen
    public void addEmployee(Employee employee) {
        template.update("INSERT INTO Employee (username, user_password, full_name, email, phone, is_active, is_admin) VALUES (?,?,?,?,?,?,?)",
                employee.getUsername(), employee.getUser_password(), employee.getFull_name(), employee.getEmail(), employee.getPhone(), employee.getIs_active(), employee.getIs_admin());
    }

    // Tjekker om en bruger eksisterer ved brugernavn
    public boolean doesTheUserExsit(String username) {
        List<Employee> employees = template.query("SELECT * FROM Employee WHERE Username=?", rowMapper, username);
        return !employees.isEmpty();
    }

    // Deaktiverer en medarbejder ved at sætte 'is_active' til 0
    public void fireEmployee(String username) {
        template.update("UPDATE employee SET is_active = 0 WHERE username = ?", username);
    }

    // Opdaterer medarbejderoplysninger i databasen
    public void updateEmployee(Employee employee) {
        template.update("UPDATE employee SET user_password = ?, full_name = ?, email = ?, phone = ?, is_active = ?, is_admin = ? WHERE username = ?",
                employee.getUser_password(), employee.getFull_name(), employee.getEmail(), employee.getPhone(), employee.getIs_active(), employee.getIs_admin(), employee.getUsername());
    }

    // Finder en medarbejder ved brugernavn
    public Employee findByUsername(String username) {
        List<Employee> users = template.query("SELECT * FROM employee WHERE username = ?", rowMapper, username);
        return users.size() == 1 ? users.get(0) : null;
    }

    // Finder en administrator ved brugernavn
    public Employee findAdmin(String username) {
        List<Employee> users = template.query("SELECT * FROM employee WHERE is_admin = 1 AND username = ?", rowMapper, username);
        return users.size() == 1 ? users.get(0) : null;
    }
}
