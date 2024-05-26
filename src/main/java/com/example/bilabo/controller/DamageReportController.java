package com.example.bilabo.controller;

import com.example.bilabo.model.DamageReport;
import com.example.bilabo.model.LeasingContract;
import com.example.bilabo.service.DamageReportService;
import com.example.bilabo.service.DamageService;
import com.example.bilabo.service.EmployeeService;
import com.example.bilabo.service.LeasingContractService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class DamageReportController {

    @Autowired
    DamageReportService damageReportService;
    @Autowired
    DamageService damageService;
    @Autowired
    LeasingContractService leasingContractService;
    @Autowired
    EmployeeService employeeService;

    // Viser en liste over alle skaderapporter.
    @GetMapping("/skaderapport")
    public String showDamageReport(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("damage_report", damageReportService.showReport());
        return "skaderapport";
    }

    // Viser siden til oprettelse af en ny skaderapport.
    @GetMapping("/skaderapportopret")
    public String createDamageReport(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("contract", leasingContractService.fetchFlow1());
        return "skaderapportopret";
    }

    // Bekræfter valget af en leasingkontrakt til skaderapporten.
    @PostMapping("/oprettelseskaderapport")
    public String submitDamageReport(Model model, HttpSession session, Integer contract_id, RedirectAttributes redirectAttributes) {
        LeasingContract leasingContract = leasingContractService.findIdAndFlow(contract_id);
        if (leasingContract == null) {
            redirectAttributes.addFlashAttribute("fejl", "Vælg venligst et af kontrakterne nedenfor");
            return "redirect:/skaderapportopret";
        }
        session.setAttribute("contract", leasingContract.getContract_id());
        return "redirect:/opretskaderapport";
    }

    // Viser formularen til oprettelse af en ny skaderapport.
    @GetMapping("opretskaderapport")
    public String displayDamageReport(HttpSession session, Model model) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("category", damageService.fetchAllDamageCategories());
        model.addAttribute("contractid", session.getAttribute("contract"));
        return "opretskaderapport";
    }

    // Tilføjer en skadekategori til skaderapporten og opdaterer den samlede pris.
    @PostMapping("/tilføjRapport")
    public String addDamageReport(Model model, Integer category_id, RedirectAttributes redirectAttributes, Integer finish, HttpSession session, DamageReport damage_report) {
        Double totalPrice = (Double) session.getAttribute("totalPrice");
        if (totalPrice == null) totalPrice = 0.0;
        if (category_id != null) totalPrice += damageService.getSpecificDamagePrice(category_id);
        session.setAttribute("totalPrice", totalPrice);
        return (finish == null) ? "redirect:/opretskaderapport" : "redirect:/kvitteringSkadeRapport";
    }

    // Viser kvitteringen for skaderapporten.
    @GetMapping("/kvitteringSkadeRapport")
    public String receipt(HttpSession session, Model model) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("contractid", session.getAttribute("contract"));
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("totalPrice", session.getAttribute("totalPrice"));
        return "kvitteringSkadeRapport";
    }
}