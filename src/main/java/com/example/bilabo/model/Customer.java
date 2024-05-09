package com.example.bilabo.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int CustomerID;
    private String name;
    private String lastName;
    private String email;
    private int telefonNr;
    private String by;
    private int postcode;
    private String roadname;


    public Customer(int customerID, String name, String lastName, String email, int telefonNr, String by, int postcode, String roadname) {
        CustomerID = customerID;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.telefonNr = telefonNr;
        this.by = by;
        this.postcode = postcode;
        this.roadname = roadname;
    }

    public Customer() {
        // default
    }


    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefonNr() {
        return telefonNr;
    }

    public void setTelefonNr(int telefonNr) {
        this.telefonNr = telefonNr;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getRoadname() {
        return roadname;
    }

    public void setRoadname(String roadname) {
        this.roadname = roadname;
    }


    public static Customer createCustomer(String name, String lastName, String email, int telefonNr, String by, int postcode, String roadname) {
        // Instantiate a new Customer object with provided information
        Customer customer = new Customer();
        customer.setName(name);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setTelefonNr(telefonNr);
        customer.setBy(by);
        customer.setPostcode(postcode);
        customer.setRoadname(roadname);

        // Add logic for validation or processing

        // Save the customer record to the database

        return customer;
    }

    // Method to update an existing customer record
    public void updateCustomer(int customerID, String name, String lastName, String email, int telefonNr, String by, int postcode, String roadname) {
        // Retrieve the existing customer record based on ID
        // Update the customer attributes with new information
        // Perform validation checks if necessary
        // Save the updated customer record to the database
    }

    // Method to delete a customer record
    public void deleteCustomer(int customerID) {
        // Retrieve the customer record based on ID
        // Delete the customer record from the database
    }

    // Method to search for customers by name
    public List<Customer> searchCustomersByName(String name) {
        // Retrieve customer records matching the provided name
        // Return a list of matching customers
        return new ArrayList<>();
    }

    }

        // Other methods for additional functionality...


