package com.example.bilabo.controller;

import com.example.bilabo.model.RentalAgreement;
import com.example.bilabo.service.RentalAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rentalAgreements")
public class RentalAgreementController {

    private final RentalAgreementService rentalAgreementService;

    @Autowired
    public RentalAgreementController(RentalAgreementService rentalAgreementService) {
        this.rentalAgreementService = rentalAgreementService;
    }

    @GetMapping("/list")
    public String showAllRentalAgreements(Model model) {
        List<RentalAgreement> rentalAgreements = rentalAgreementService.getAllRentalAgreements();
        model.addAttribute("rentalAgreements", rentalAgreements);
        return "rental-agreement-list"; // Assuming you have a corresponding HTML template
    }

    @GetMapping("/add")
    public String showAddRentalAgreementForm(Model model) {
        model.addAttribute("rentalAgreement", new RentalAgreement());
        return "add-rental-agreement"; // Assuming you have a corresponding HTML template
    }

    @PostMapping("/add")
    public String addRentalAgreement(@ModelAttribute("rentalAgreement") RentalAgreement rentalAgreement) {
        rentalAgreementService.createRentalAgreement(rentalAgreement);
        return "redirect:/rentalAgreements/list";
    }

    @GetMapping("/details")
    public String showRentalAgreementDetails(@RequestParam("id") int rentalAgreementId, Model model) {
        RentalAgreement rentalAgreement = rentalAgreementService.getRentalAgreementById(rentalAgreementId);
        model.addAttribute("rentalAgreement", rentalAgreement);
        return "rental-agreement-details"; // Assuming you have a corresponding HTML template
    }

    @GetMapping("/delete")
    public String deleteRentalAgreement(@RequestParam("id") int rentalAgreementId) {
        rentalAgreementService.deleteRentalAgreement(rentalAgreementId);
        return "redirect:/rentalAgreements/list";
    }
}

