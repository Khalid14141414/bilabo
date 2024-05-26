package com.example.bilabo.controller;

import com.example.bilabo.model.Car;
import com.example.bilabo.model.Customer;
import com.example.bilabo.model.LeasingContract;
import com.example.bilabo.service.CarService;
import com.example.bilabo.service.CustomerService;
import com.example.bilabo.service.EmployeeService;
import com.example.bilabo.service.LeasingContractService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.Period;

@Controller
public class LeasingContractController {

    @Autowired
    LeasingContractService leasingContractService;
    @Autowired
    CarService carService;
    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;

    // Viser siden til oprettelse af en ny leasingkontrakt med tilgængelige biler.
    @GetMapping("/opretKontrakt")
    public String leasingKontrakt(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("available", carService.fetchAvailable());
        return "opretKontrakt";
    }

    // Vælger en bil til leasingkontrakten og viser detaljer om den valgte bil.
    @PostMapping("/chooseCar")
    public String seBiler(Model model, int vehicle_number, HttpSession session, RedirectAttributes redirectAttributes) {
        Car car = carService.findId(vehicle_number);
        if (car == null || car.getFlow() == 1) {
            redirectAttributes.addFlashAttribute("error", car == null ? "Bilen med det angivne vognnummer kunne ikke findes" : "Bilen er allerede lejet ud");
            return "redirect:/opretKontrakt";
        }
        model.addAttribute("opdater", car);
        session.setAttribute("numb", car.getVehicle_number());
        return "bilValgt";
    }

    // Viser alle eksisterende leasingkontrakter og den samlede pris for alle kontrakter.
    @GetMapping("/selejekontrakt")
    public String Leasing_contracts(Model model, HttpSession session){
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("LC", leasingContractService.fetchAll());
        model.addAttribute("totalPriceRent", leasingContractService.calculateTotalPriceOfLeasingContracts());
        return "seLejekontrakt";
    }

    // Viser siden til oprettelse af leasingkontrakt med oplysninger om den valgte bil og tilgængelige kunder.
    @GetMapping("/lej")
    public String leasing(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        Integer numb = (Integer) session.getAttribute("numb");
        if (numb != null) {
            model.addAttribute("opdater", carService.findId(numb));
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("customers", customerService.fetchAll());
            return "lej";
        }
        return "error";
    }

    // Opretter en ny leasingkontrakt og beregner den samlede pris baseret på leasingperioden.
    @PostMapping("/createLeasingContract")
    public String createLease(LocalDate start_date, LocalDate end_date, Model model, HttpSession session, int customer_id, String username, RedirectAttributes redirectAttributes) {
        int numb = (int) session.getAttribute("numb");
        Car car = carService.findId(numb);
        Period period = Period.between(start_date, end_date);
        if (period.getMonths() < 3) {
            redirectAttributes.addFlashAttribute("error", "Lej perioden skal være mindst 3 måneder.");
            return "redirect:/lej";
        }
        double totalPrice = calculateTotalPrice(period, car.getPrice());
        session.setAttribute("totalPriceRent", totalPrice);
        session.setAttribute("startDate", start_date);
        session.setAttribute("endDate", end_date);
        session.setAttribute("customer", customer_id);
        session.setAttribute("username", username);
        Customer customer = customerService.findId(customer_id);
        session.setAttribute("customer_id", customer);
        session.setAttribute("customername", customer.getFull_name());
        return "redirect:/leaseconfirm";
    }

    // Beregner den samlede pris for leasing baseret på leasingperioden og bilens månedlige pris.
    private double calculateTotalPrice(Period period, double monthlyPrice) {
        double totalPrice = monthlyPrice * period.getMonths();
        if (period.getDays() > 0) {
            double dailyPrice = monthlyPrice / 30;
            double extraDaysPrice = dailyPrice * period.getDays();
            totalPrice += Math.round(extraDaysPrice);
        }
        return totalPrice;
    }

    // Viser en bekræftelsesside for leasingkontrakten med detaljer om bil, kunde og pris.
    @GetMapping("/leaseconfirm")
    public String leasingConfirmation(Model model, HttpSession session) {
        if (!employeeService.checkSession(session)) return "redirect:/";
        model.addAttribute("opdater", carService.findId((Integer) session.getAttribute("numb")));
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("startDate", session.getAttribute("startDate"));
        model.addAttribute("endDate", session.getAttribute("endDate"));
        model.addAttribute("customer", session.getAttribute("customer"));
        model.addAttribute("totalPriceRent", session.getAttribute("totalPriceRent"));
        model.addAttribute("customername", session.getAttribute("customername"));
        return "leaseconfirm";
    }

    // Bekræfter og gemmer den oprettede leasingkontrakt.
    @PostMapping("/createLeasingContractConfirmed")
    public String leasingAdd(Model model, HttpSession session, LeasingContract leasing_contract) {
        leasingContractService.addLeasingContract(leasing_contract);
        carService.updateAfterDamageReport((Integer) session.getAttribute("numb"));
        return "redirect:/home";
    }
}
