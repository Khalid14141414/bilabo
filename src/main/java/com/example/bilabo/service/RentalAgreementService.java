package com.example.bilabo.service;

import com.example.bilabo.model.RentalAgreement;
import com.example.bilabo.reporsitories.RentalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RentalAgreementService {

    @Autowired
    private RentalAgreementRepository rentalAgreementRepository;

    public RentalAgreement createRentalAgreement(RentalAgreement rentalAgreement) {
        return rentalAgreementRepository.save(rentalAgreement);
    }

    public RentalAgreement getRentalAgreementById(int rentalAgreementId) {
        return rentalAgreementRepository.findById(rentalAgreementId);
    }

    public List<RentalAgreement> getAllRentalAgreements() {
        return rentalAgreementRepository.findAll();
    }

    public void deleteRentalAgreement(int rentalAgreementId) {
        rentalAgreementRepository.deleteById(rentalAgreementId);
    }
}

