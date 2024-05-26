package com.example.bilabo.controller;

import com.example.bilabo.model.Car;
import com.example.bilabo.model.Employee;
import com.example.bilabo.service.CarService;
import com.example.bilabo.service.EmployeeService;
import com.example.bilabo.service.LeasingContractService;
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
    @Autowired
    LeasingContractService leasingContractService;

    // Viser alle biler tilgængelige for salg.
    @GetMapping("/seallebiler")
    public String car(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("cars", carService.fetchAll());
        return "seallebiler";
    }

    // Viser alle ledige biler.
    @GetMapping("/ledigbiler")
    public String getAvailableCars(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("available", carService.fetchAvailable());
        return "ledigbiler";
    }

    // Tilføjer en ny bil til listen.
    @PostMapping("/createNew")
    public String addCartoList(Car car) {
        carService.addCar(car);
        return "redirect:/seallebiler";
    }

    // Sletter en bil baseret på køretøjets nummer.
    @GetMapping("/deleteOne/{vehicle_number}")
    public String deleteOne(@PathVariable("vehicle_number") int vehicle_number){
        carService.deleteCar(vehicle_number);
        return "redirect:/seallebiler";
    }

    // Henter information om en bil for at opdatere den.
    @GetMapping("/opdaterBilen/{vehicle_number}")
    public String updateCar(@PathVariable("vehicle_number") int vehicle_number, Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("opdater", carService.findId(vehicle_number));
        return "opdaterBil";
    }

    // Opdaterer en bils information i systemet.
    @PostMapping("/carupdate")
    public String updateCarToList(Car car, int vehicle_number) {
        carService.updateCar(car, vehicle_number);
        return "redirect:/seallebiler";
    }

    @GetMapping("/sammenlagtpris")
    public String getTotalPrice(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) {
            return "redirect:/";
        }
        Employee adminLogin = (Employee) session.getAttribute("adminlogin");

        model.addAttribute("admin", adminLogin);

        double totalPrice = carService.calculateTotalPriceOfRentedCars();
        model.addAttribute("totalPrice", totalPrice);

        double totalPrices = leasingContractService.calculateTotalPriceOfLeasingContracts();
        model.addAttribute("totalPrices", totalPrices);

        List<Map<String, Object>> rentedCars = carService.TotalpriceData();
        model.addAttribute("rentedCars", rentedCars);

        return "sammenlagtspris";  // This should match `sammenlagtpris.html`
    }
}