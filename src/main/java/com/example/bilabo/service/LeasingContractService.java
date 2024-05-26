package com.example.bilabo.service;



import com.example.bilabo.model.LeasingContract;
import com.example.bilabo.reporsitories.LeasingContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeasingContractService {
    @Autowired
    LeasingContractRepo leasing_contractRepo;

    // Henter alle leasingkontrakter
    public List<LeasingContract> fetchAll() {
        return leasing_contractRepo.fetchAll();
    }

    // Henter leasingkontrakter med flow = 1 ved at sammenkæde med biloplysninger
    public List<LeasingContract> fetchFlow1() {
        return leasing_contractRepo.fetchFlow1();
    }

    // Finder en leasingkontrakt ved kontrakt-id
    public LeasingContract findContractById(int contractId) {
        return leasing_contractRepo.findContractByid(contractId);
    }

    // Tilføjer en ny leasingkontrakt
    public void addLeasingContract(LeasingContract leasing_contract) {
        leasing_contractRepo.createLeasingContract(leasing_contract);
    }

    // Beregner den samlede pris af alle leasingkontrakter
    public double calculateTotalPriceOfLeasingContracts() {
        List<LeasingContract> leasingContracts = leasing_contractRepo.fetchAll(); // Henter alle kontrakter
        double totalPrice = 0.0;

        for (LeasingContract contract : leasingContracts) {
            totalPrice += contract.getPrice(); // Tilføjer kontraktprisen til den samlede pris
        }

        return totalPrice;
    }

    // Finder en leasingkontrakt med flow = 1 ved kontrakt-id
    public LeasingContract findIdAndFlow(int id) {
        return leasing_contractRepo.findContractByidAndFlow(id);
    }
}
