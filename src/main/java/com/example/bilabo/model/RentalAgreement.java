package com.example.bilabo.model;


import java.time.LocalDate;
import java.util.Date;

public class RentalAgreement {
    private int rentalAgreementID;
    private int CarID;
    private int CustomerID;
    private LocalDate rentalAgreementBegin;
    private LocalDate rentalAgreementEnds;
    private int pickupID;

    public RentalAgreement(int rentalAgreementID, int carID, int customerID, LocalDate rentalAgreementBegin, LocalDate rentalAgreementEnds, int pickupID) {
        this.rentalAgreementID = rentalAgreementID;
        CarID = carID;
        CustomerID = customerID;
        this.rentalAgreementBegin = rentalAgreementBegin;
        this.rentalAgreementEnds = rentalAgreementEnds;
        this.pickupID = pickupID;
    }

    public RentalAgreement() {
   // default constructor
    }


    public int getRentalAgreementID() {
        return rentalAgreementID;
    }

    public void setRentalAgreementID(int rentalAgreementID) {
        this.rentalAgreementID = rentalAgreementID;
    }

    public int getCarID() {
        return CarID;
    }

    public void setCarID(int carID) {
        CarID = carID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public LocalDate getRentalAgreementBegin() {
        return rentalAgreementBegin;
    }

    public void setRentalAgreementBegin(LocalDate rentalAgreementBegin) {
        this.rentalAgreementBegin = rentalAgreementBegin;
    }

    public LocalDate getRentalAgreementEnds() {
        return rentalAgreementEnds;
    }

    public void setRentalAgreementEnds(LocalDate rentalAgreementEnds) {
        this.rentalAgreementEnds = rentalAgreementEnds;
    }

    public int getPickupID() {
        return pickupID;
    }

    public void setPickupID(int pickupID) {
        this.pickupID = pickupID;
    }

    public static RentalAgreement createRentalAgreement(int carID, int customerID, LocalDate beginDate, LocalDate endDate, int pickupID) {
        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setCarID(carID);
        rentalAgreement.setCustomerID(customerID);
        rentalAgreement.setRentalAgreementBegin(beginDate);
        rentalAgreement.setRentalAgreementEnds(endDate);
        rentalAgreement.setPickupID(pickupID);

        // Add logic for validation or processing

        // Save the rental agreement to the database

        return rentalAgreement;
    }

    // Method to update an existing rental agreement
    public void updateRentalAgreement(int rentalAgreementID, Date beginDate, Date endDate, int pickupID) {
        // Retrieve the existing rental agreement based on ID
        // Update the rental agreement attributes with new information
        // Perform validation checks if necessary
        // Save the updated rental agreement to the database
    }

    // Method to cancel a rental agreement
    public void cancelRentalAgreement(int rentalAgreementID) {
        // Retrieve the rental agreement based on ID
        // Perform any necessary actions related to canceling (e.g., releasing car)
        // Mark the rental agreement as canceled or remove it from the system
    }
}

