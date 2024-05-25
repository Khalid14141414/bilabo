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


    public List <LeasingContract> fetchAll() {
        return leasing_contractRepo.fetchAll();
    }
    public List <LeasingContract> fetchFlow1() {
        return leasing_contractRepo.fetchFlow1();
    }

    public LeasingContract findContractById(int contractId) {
        return leasing_contractRepo.findContractByid(contractId);
    }


    public void addLeasingContract(LeasingContract leasing_contract){
        leasing_contractRepo.createLeasingContract(leasing_contract);
    }
    public double calculateTotalPriceOfLeasingContracts() {
        List<LeasingContract> leasingContracts = leasing_contractRepo.fetchAll(); //Henter alle kontrakter
        double totalPrice = 0.0;

        for (LeasingContract contract : leasingContracts) {
            totalPrice += contract.getPrice();// Tilføj kontrakt pris til den totale pris
        }

        return totalPrice;
    }

    public LeasingContract findIdAndFlow(int id){
        return leasing_contractRepo.findContractByidAndFlow(id);
    }
}
