package com.example.bilabo.service;

import com.example.bilabo.model.Car;
import com.example.bilabo.reporsitories.CarReporsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarReporsitory carRepository;

    // Save a new car
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    // Update an existing car
    public void updateCar(Car car) {
        carRepository.update(car);
    }

    // Delete a car by ID
    public void deleteCar(int carId) {
        carRepository.delete(carId);
    }

    // Find a car by ID
    public Car findCarById(int carId) {
        return carRepository.findById(carId);
    }

    // Get all cars
    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }
}
