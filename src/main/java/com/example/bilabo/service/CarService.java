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

    // Henter alle biler
    public List<Car> fetchAll() {
        return carRepo.fetchAll();
    }

    // Henter alle tilgængelige biler (flow = 0)
    public List<Car> fetchAvailable() {
        return carRepo.fetchAvailable();
    }

    // Tilføjer en ny bil
    public void addCar(Car car) {
        carRepo.addCar(car);
    }

    // Sletter en bil ved id
    public boolean deleteCar(int id) {
        return carRepo.deleteCar(id);
    }

    // Opdaterer en bil ved id
    public void updateCar(Car car, int id) {
        carRepo.updateCar(car, id);
    }

    // Finder en bil ved id
    public Car findId(int id) {
        return carRepo.findCarByid(id);
    }

    // Beregner den samlede pris af lejede biler (flow = 1)
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

    // Opdaterer bilen efter en skadesrapport (sætter flow = 2)
    public void updateAfterDamageReport(int id) {
        carRepo.updateAfterDamageReport(id);
    }

    // Henter totalpriserne og relaterede data
    public List<Map<String, Object>> TotalpriceData() {
        return carRepo.getTotalPricesData();
    }
}
