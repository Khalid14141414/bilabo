package com.example.bilabo.controller;

import com.example.bilabo.model.DamageReport;
import com.example.bilabo.service.DamageReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/damageReports")
public class DamageReportController {

    private final DamageReportService damageReportService;

    @Autowired
    public DamageReportController(DamageReportService damageReportService) {
        this.damageReportService = damageReportService;
    }

    @GetMapping("/list")
    public String showAllDamageReports(Model model) {
        List<DamageReport> damageReports = damageReportService.getAllDamageReports();
        model.addAttribute("damageReports", damageReports);
        return "damage-report-list"; // Assuming you have a corresponding HTML template
    }

    @GetMapping("/add")
    public String showAddDamageReportForm(Model model) {
        model.addAttribute("damageReport", new DamageReport());
        return "add-damage-report"; // Assuming you have a corresponding HTML template
    }

    @PostMapping("/add")
    public String addDamageReport(@ModelAttribute("damageReport") DamageReport damageReport) {
        damageReportService.createDamageReport(damageReport);
        return "redirect:/damageReports/list";
    }

    @GetMapping("/details")
    public String showDamageReportDetails(@RequestParam("id") int damageId, Model model) {
        DamageReport damageReport = damageReportService.getDamageReportById(damageId);
        model.addAttribute("damageReport", damageReport);
        return "damage-report-details"; // Assuming you have a corresponding HTML template
    }

    @GetMapping("/delete")
    public String deleteDamageReport(@RequestParam("id") int damageId) {
        damageReportService.deleteDamageReport(damageId);
        return "redirect:/damageReports/list";
    }

    @GetMapping("/update")
    public String showUpdateDamageReportForm(@RequestParam("id") int damageId, Model model) {
        DamageReport damageReport = damageReportService.getDamageReportById(damageId);
        model.addAttribute("damageReport", damageReport);
        return "update-damage-report"; // Assuming you have a corresponding HTML template
    }

    @PostMapping("/update")
    public String updateDamageReport(@ModelAttribute("damageReport") DamageReport updatedDamageReport) {
        int damageId = updatedDamageReport.getDamageID();
        damageReportService.updateDamageReport(damageId, updatedDamageReport);
        return "redirect:/damageReports/list";
    }
}

