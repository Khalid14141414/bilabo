package com.example.bilabo.controller;

import com.example.bilabo.model.Car;
import com.example.bilabo.service.CarService;
import com.example.bilabo.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class CarController {

    @Autowired
    CarService carService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/seallebiler")
    public String car(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("cars", carService.fetchAll());
        return "seallebiler";
    }

    @GetMapping("/ledigbiler")
    public String getAvailableCars(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("available", carService.fetchAvailable());
        return "ledigbiler";
    }

    @PostMapping("/createNew")
    public String addCartoList(Car car) {
        carService.addCar(car);
        return "redirect:/seallebiler";
    }

    @GetMapping("/deleteOne/{vehicle_number}")
    public String deleteOne(@PathVariable("vehicle_number") int vehicle_number){
        carService.deleteCar(vehicle_number);
        return "redirect:/seallebiler";
    }

    @GetMapping("/opdaterBilen/{vehicle_number}")
    public String updateCar(@PathVariable("vehicle_number") int vehicle_number, Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("opdater", carService.findId(vehicle_number));
        return "opdaterBil";
    }

    @PostMapping("/carupdate")
    public String updateCarToList(Car car, int vehicle_number) {
        carService.updateCar(car, vehicle_number);
        return "redirect:/seallebiler";
    }

    @GetMapping("/totalPriceOfRentedCars")
    public String getTotalPriceOfRentedCars(Model model) {
        double totalPrice = carService.calculateTotalPriceOfRentedCars();
        model.addAttribute("totalPrice", totalPrice);
        return "sammenlagtspris"; // return the name of your view
        }

        @PostMapping("/updateAfterDamageReport/{id}")
        public String updateAfterDamageReport(@PathVariable("id") int id) {
            carService.updateAfterDamageReport(id);
            return "redirect:/"; // redirect to the desired URL
        }

        @GetMapping("/totalPriceData")
        public String getTotalPriceData(Model model) {
            List<Map<String, Object>> totalPriceData = carService.TotalpriceData();
            model.addAttribute("totalPriceData", totalPriceData);
            return "totalPriceDataView"; // return the name of your view
        }
    }
