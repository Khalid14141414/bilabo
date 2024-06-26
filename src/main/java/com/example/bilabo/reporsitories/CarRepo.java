package com.example.bilabo.reporsitories;

import com.example.bilabo.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarRepo {

    @Autowired
    JdbcTemplate template;

    private RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);

    // Henter alle biler fra databasen
    public List<Car> fetchAll() {
        return template.query("SELECT * FROM Car", rowMapper);
    }

    // Henter tilgængelige biler fra databasen
    public List<Car> fetchAvailable() {
        return template.query("SELECT * FROM Car WHERE flow = 0", rowMapper);
    }

    // Tilføjer en ny bil til databasen
    public void addCar(Car c) {
        template.update("INSERT INTO car (vehicle_number, frame_number, brand, model, make, color, price, flow, odometer, fuel_type, motor, gear_type) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
                c.getVehicle_number(), c.getFrame_number(), c.getBrand(), c.getModel(), c.getMake(),
                c.getColor(), c.getPrice(), c.getFlow(), c.getOdometer(), c.getFuel_type(), c.getMotor(), c.getGear_type());
    }

    // Sletter en bil fra databasen ved dens køretøjsnummer
    public Boolean deleteCar(int vehicle_number) {
        return template.update("DELETE FROM car WHERE vehicle_number = ?", vehicle_number) > 0;
    }

    // Finder en bil i databasen ved dens køretøjsnummer
    public Car findCarByid(int vehicle_number) {
        List<Car> users = template.query("Select * FROM car WHERE vehicle_number = ?", rowMapper, vehicle_number);
        return users.size() == 1 ? users.get(0) : null;
    }

    // Opdaterer biloplysninger i databasen
    public void updateCar(Car c, int vehicle_number) {
        template.update("UPDATE car SET frame_number = ?, brand = ?, model = ?, make = ?, color = ?, price = ?, flow = ?, odometer = ?, fuel_type = ?, motor = ?, gear_type = ? WHERE vehicle_number = ?",
                c.getFrame_number(), c.getBrand(), c.getModel(), c.getMake(), c.getColor(), c.getPrice(), c.getFlow(), c.getOdometer(), c.getFuel_type(), c.getMotor(), c.getGear_type(), c.getVehicle_number());
    }

    // Opdaterer bilstatus til 'tilgængelig' efter en kontrakt slutter
    public void updateAfterContract(int vehicle_number) {
        template.update("UPDATE car SET flow = 1 WHERE vehicle_number = ?", vehicle_number);
    }

    // Henter udlejede biler fra databasen
    public List<Car> fetchRentedCars() {
        return template.query("SELECT * FROM car WHERE flow = 1", rowMapper);
    }

    // Opdaterer bilstatus til 'beskadiget' efter en skaderapport
    public void updateAfterDamageReport(int vehicle_number) {
        template.update("UPDATE car SET flow = 2 WHERE vehicle_number = ?", vehicle_number);
    }

    // Henter data for samlede priser inklusive lejede biler og deres kontrakter
    public List<Map<String, Object>> getTotalPricesData() {
        return template.queryForList("SELECT car.vehicle_number, car.frame_number, car.brand, car.flow, " +
                "leasing_contract.contract_id, leasing_contract.username, leasing_contract.customer_id, leasing_contract.start_date, " +
                "leasing_contract.end_date, car.price AS car_price, leasing_contract.price AS contract_price FROM car JOIN leasing_contract ON car.vehicle_number = leasing_contract.vehicle_number WHERE flow = 1");
    }
}
