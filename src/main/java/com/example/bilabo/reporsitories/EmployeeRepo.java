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

    public Employee findByUserAndPassword(String username, String user_password) {
        List<Employee> employees = template.query("SELECT * FROM Employee WHERE username=? AND user_password=?", rowMapper, username, user_password);
        return employees.size() == 1 ? employees.get(0) : null;
    }

    public List<Employee> fetchAll() {
        return template.query("SELECT * FROM Employee", rowMapper);
    }

    public void addEmployee(Employee employee) {
        template.update("INSERT INTO Employee (username, user_password, full_name, email, phone, is_active, is_admin) VALUES (?,?,?,?,?,?,?)",
                employee.getUsername(), employee.getUser_password(), employee.getFull_name(), employee.getEmail(), employee.getPhone(), employee.getIs_active(), employee.getIs_admin());
    }

    public boolean doesTheUserExsit(String username){
        List<Employee>employees = template.query("SELECT * FROM Employee WHERE Username=?", rowMapper, username);
        return !employees.isEmpty();
    }

    public void fireEmployee(String username){
        template.update("UPDATE employee SET is_active = 0 WHERE username = ?", username);
    }

    public void updateEmployee(Employee employee){
        template.update("UPDATE employee SET user_password = ?, full_name= ?, email= ?, phone= ?, is_active= ?, is_admin = ? where username=?",
                employee.getUser_password(), employee.getFull_name(), employee.getEmail(), employee.getPhone(), employee.getIs_active(), employee.getIs_admin(), employee.getUsername());
    }

    public Employee findByUsername(String username){
        List<Employee> users = template.query("Select * FROM employee WHERE username = ?", rowMapper, username);
        return users.size() == 1 ? users.get(0) : null;
    }

    public Employee findAdmin(String username){
        List<Employee> users = template.query("SELECT * FROM employee WHERE is_admin=1 and username = ?", rowMapper, username);
        return users.size() == 1 ? users.get(0) : null;
    }
}