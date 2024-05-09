package com.example.bilabo.model;

import java.util.ArrayList;
import java.util.List;

public class DamageReport {
    public int damageID;
    private int rentalAgreementID;
    private String description;
    private int price;

    public DamageReport(int damageID, int rentalAgreementID, String description, int price) {
        this.damageID = damageID;
        this.rentalAgreementID = rentalAgreementID;
        this.description = description;
        this.price = price;
    }

    public DamageReport() {
    }


    public int getDamageID() {
        return damageID;
    }

    public void setDamageID(int damageID) {
        this.damageID = damageID;
    }

    public int getRentalAgreementID() {
        return rentalAgreementID;
    }

    public void setRentalAgreementID(int rentalAgreementID) {
        this.rentalAgreementID = rentalAgreementID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static DamageReport createDamageReport(int rentalAgreementID, String description, int price) {
        // Instantiate a new DamageReport object with provided information
        DamageReport damageReport = new DamageReport();
        damageReport.setRentalAgreementID(rentalAgreementID);
        damageReport.setDescription(description);
        damageReport.setPrice(price);

        // Add logic for validation or processing

        // Save the damage report to the database

        return damageReport;
    }

    // Method to update an existing damage report
    public void updateDamageReport(int damageID, int rentalAgreementID, String description, int price) {
        // Retrieve the existing damage report based on ID
        // Update the damage report attributes with new information
        // Perform validation checks if necessary
        // Save the updated damage report to the database
    }

    // Method to delete a damage report
    public void deleteDamageReport(int damageID) {
        // Retrieve the damage report based on ID
        // Delete the damage report from the database
    }

    // Method to search for damage reports by rental agreement ID
    public List<DamageReport> searchDamageReportsByRentalAgreementID(int rentalAgreementID) {
        // Retrieve damage reports matching the provided rental agreement ID
        // Return a list of matching damage reports
        return new ArrayList<>();
    }

    // Other methods for additional functionality...
}

