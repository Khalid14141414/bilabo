package com.example.bilabo.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Cars {
    private int CarID;
    private String model;
    private Year year;
    private String registeringNumber;
    private int price;

    public Cars(int carID, String model, Year year, String registeringNumber, int price) {
        CarID = carID;
        this.model = model;
        this.year = year;
        this.registeringNumber = registeringNumber;
        this.price = price;
    }

    public Cars(){
        // default
    }

    public int getCarID() {
        return CarID;
    }

    public void setCarID(int carID) {
        CarID = carID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getRegisteringNumber() {
        return registeringNumber;
    }

    public void setRegisteringNumber(String registeringNumber) {
        this.registeringNumber = registeringNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public static Cars createCar(String model, Year year, String registeringNumber, int price) {
        // Instantiate a new Cars object with provided information
        Cars car = new Cars();
        car.setModel(model);
        car.setYear(year);
        car.setRegisteringNumber(registeringNumber);
        car.setPrice(price);

        // Add logic for validation or processing

        // Save the car record to the database

        return car;
    }

    // Method to update an existing car record
    public void updateCar(int carID, String model, Year year, String registeringNumber, int price) {
        // Retrieve the existing car record based on ID
        // Update the car attributes with new information
        // Perform validation checks if necessary
        // Save the updated car record to the database
    }

    // Method to delete a car record
    public void deleteCar(int carID) {
        // Retrieve the car record based on ID
        // Delete the car record from the database
    }

    // Method to search for cars by model
    public List<Cars> searchCarsByModel(String model) {
        // Retrieve car records matching the provided model
        // Return a list of matching cars
        return new ArrayList<>();
    }

    // Other methods for additional functionality...
}

