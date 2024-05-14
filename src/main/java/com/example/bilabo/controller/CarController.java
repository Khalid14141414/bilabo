package com.example.bilabo.controller;

import com.example.bilabo.model.Car;
import com.example.bilabo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    // Endpoint to save a new car
    @PostMapping
    public void saveCar(@RequestBody Car car) {
        carService.saveCar(car);
    }

    // Endpoint to update an existing car
    @PutMapping("/{carId}")
    public void updateCar(@PathVariable int carId, @RequestBody Car car) {
        car.setCarID(carId); // Ensure the ID is set correctly
        carService.updateCar(car);
    }

    // Endpoint to delete a car by its ID
    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable int carId) {
        carService.deleteCar(carId);
    }

    // Endpoint to retrieve a car by its ID
    @GetMapping("/{carId}")
    public Car getCarById(@PathVariable int carId) {
        return carService.findCarById(carId);
    }

    // Endpoint to retrieve all cars
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }
}
