package com.example.bilabo.controller;

import com.example.bilabo.model.DamageCategory;
import com.example.bilabo.service.DamageService;
import com.example.bilabo.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class DamageController {

    @Autowired
    DamageService damageService;

    @Autowired
    EmployeeService employeeService;

    // Viser en liste over alle skaderapporter.
    @GetMapping("/skader")
    public String skaddeRapportListe(Model model, HttpSession session){
        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        model.addAttribute("category", damageService.fetchAllDamageCategories());
        return "skader";
    }

    // Tilføjer en ny skadekategori til listen.
    @PostMapping("/createNewDamage")
    public String addDamagetoList(DamageCategory d) {
        damageService.addDamage(d);
        return "redirect:/skader";
    }

    // Henter information om en specifik skadekategori for at opdatere den.
    @GetMapping("/updateOneDamage/{category_id}")
    public String updateDamage(@PathVariable("category_id") int category_id, Model model, HttpSession session) {
        if (!employeeService.checkSession(session)){
            return "redirect:/";
        }
        model.addAttribute("opdater", damageService.findSpecifikDamage(category_id));
        return "opdaterSkade";
    }

    // Opdaterer en skadekategori i systemet.
    @PostMapping("/damageUpdate")
    public String updateDamageToList(DamageCategory damage_category, int category_id) {
        damageService.updateCategory(damage_category, category_id);
        return "redirect:/skader";
    }

    // Sletter en specifik skadekategori.
    @GetMapping("/deleteOneDamage/{category_id}")
    public String deleteOne(@PathVariable("category_id") int category_id){
        damageService.deleteDamage(category_id);
        return "redirect:/skader";
    }
}
