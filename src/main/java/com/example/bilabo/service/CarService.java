package com.example.bilabo.service;

import com.example.bilabo.model.Car;
import com.example.bilabo.reporsitories.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

    public List<Car> fetchAll() {
        return carRepo.fetchAll();
    }

    public List<Car> fetchAvailable(){
        return carRepo.fetchAvailable();
    }

    public void addCar(Car car){
        carRepo.addCar(car);
    }

    public boolean deleteCar(int id){
        return carRepo.deleteCar(id);
    }

    public void updateCar(Car car, int id){
        carRepo.updateCar(car, id);
    }

    public Car findId(int id){
        return carRepo.findCarByid(id);
    }

    public double calculateTotalPriceOfRentedCars() {
        var rentedCars = carRepo.fetchRentedCars();
        double totalPrice = 0.0;
        for (Car car : rentedCars) {
            if (car.getFlow() == 1) {
                totalPrice += car.getPrice();
            }
        }
        return totalPrice;
    }

    public void updateAfterDamageReport(int id){
        carRepo.updateAfterDamageReport(id);
    }

    public List<Map<String, Object>> TotalpriceData() {
        return carRepo.getTotalPricesData();
    }
}