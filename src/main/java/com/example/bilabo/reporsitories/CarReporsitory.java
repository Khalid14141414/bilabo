package com.example.bilabo.reporsitories;

import com.example.bilabo.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;

@Repository
public class CarReporsitory {

@Autowired
    JdbcTemplate jdbcTemplate;

    // Save a new car
    public void save(Car car) {
        String sql = "INSERT INTO bil (model, årgang, registreringsnummer, pris) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getModel(), car.getYear(), car.getRegisteringNumber(), car.getPrice());
    }

    // Update an existing car
    public void update(Car car) {
        String sql = "UPDATE bil SET model = ?, årgang = ?, registreringsnummer = ?, pris = ? WHERE bil_id = ?";
        jdbcTemplate.update(sql, car.getModel(), car.getYear(), car.getRegisteringNumber(), car.getPrice(), car.getCarID());
    }

    // Delete a car
    public void delete(int carId) {
        String sql = "DELETE FROM bil WHERE bil_id = ?";
        jdbcTemplate.update(sql, carId);
    }

    // Find a car by ID
    public Car findById(int carId) {
        String sql = "SELECT * FROM bil WHERE bil_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{carId}, (rs, rowNum) -> {
            Car car = new Car();
            car.setCarID(rs.getInt("bil_id"));
            car.setModel(rs.getString("model"));
            car.setYear(rs.getObject("årgang", Year.class));
            car.setRegisteringNumber(rs.getString("registreringsnummer"));
            car.setPrice((int) rs.getDouble("pris"));
            return car;
        });
    }

    public List<Car> getAllCars() {
        String sql = "SELECT * FROM bil";
        return jdbcTemplate.query(sql, (rs, rowNum) -> (Car) rs);
    }

    // Map a row from the result set to a Car object
    private Car mapRowToCar(java.sql.ResultSet rs) throws java.sql.SQLException {
        Car car = new Car();
        car.setCarID(rs.getInt("bil_id"));
        car.setModel(rs.getString("model"));
        car.setYear(rs.getObject("årgang", java.time.Year.class));
        car.setRegisteringNumber(rs.getString("registreringsnummer"));
        car.setPrice((int) rs.getDouble("pris"));
        return car;
    }


}
