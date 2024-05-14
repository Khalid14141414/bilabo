package com.example.bilabo.CommandLine;

import com.example.bilabo.model.Car;
import com.example.bilabo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Year;

@SpringBootApplication
public class CommandLine implements CommandLineRunner {

    @Autowired
    private CarService carService;

    public static void main(String[] args) {
        SpringApplication.run(CommandLine.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // cheking of this is stored in database
        Car car = new Car(1, "Toyota Camry", 2022, "ABC123", 25000);
        carService.saveCar(car);
    }


}

