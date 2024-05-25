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

    @GetMapping("/skaderapport")
    public String showDamageReport(Model model, HttpSession session){
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("damage_report", damageReportService.showReport());
        return "skaderapport";
    }

    @GetMapping("/skaderapportopret")
    public String createDamageReport(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("contract", leasingContractService.fetchFlow1());
        return "skaderapportopret";
    }

    @PostMapping("/oprettelseskaderapport")
    public String submitDamageReport(Model model, HttpSession session, Integer contract_id, RedirectAttributes redirectAttributes){
        LeasingContract leasingContract = leasingContractService.findIdAndFlow(contract_id);
        if (leasingContract == null){
            redirectAttributes.addFlashAttribute("fejl", "Vælg venligst et af kontrakterne nedenfor");
            return "redirect:/skaderapportopret";
        }
        session.setAttribute("contract", leasingContract.getContract_id());
        return "redirect:/opretskaderapport";
    }

    @GetMapping("opretskaderapport")
    public String displayDamageReport(HttpSession session, Model model) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("category", damageService.fetchAllDamageCategories());
        model.addAttribute("contractid", session.getAttribute("contract"));
        return "opretskaderapport";
    }

    @PostMapping("/tilføjRapport")
    public String addDamageReport(Model model, Integer category_id, RedirectAttributes redirectAttributes, Integer finish, HttpSession session, DamageReport damage_report) {
        Double totalPrice = (Double) session.getAttribute("totalPrice");
        if (totalPrice == null) totalPrice = 0.0;
        if (category_id != null) totalPrice += damageService.getSpecificDamagePrice(category_id);
        session.setAttribute("totalPrice", totalPrice);
        return (finish == null) ? "redirect:/opretskaderapport" : "redirect:/kvitteringSkadeRapport";
    }

    @GetMapping("/kvitteringSkadeRapport")
    public String receipt(HttpSession session, Model model){
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("contractid", session.getAttribute("contract"));
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("totalprisen", session.getAttribute("totalPrice"));
        return "kvitteringSkadeRapport";
    }

    @PostMapping("/Bekræftkvittering")
    public String confirmReceipt(Model model, HttpSession session, DamageReport damage_report) {
        damageReportService.addDamageReport(damage_report);
        return "redirect:/skaderapport";
    }

    @GetMapping("/updateOneDamageReport/{report_id}")
    public String updateDamage(@PathVariable("report_id") int report_id, Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("opdater", damageReportService.findSpecificReport(report_id));
        return "opdaterSkadeRapport";
    }

    @PostMapping("/reportUpdate")
    public String updateReport(DamageReport damage_report, int report_id) {
        damageReportService.updateReport(damage_report, report_id);
        return "redirect:/skaderapport";
    }

    @GetMapping("/deleteOneReport/{report_id}")
    public String deleteReport(@PathVariable("report_id") int report_id, HttpSession session){
        damageReportService.deleteReport(report_id);
        return "redirect:/skaderapport";
    }
}