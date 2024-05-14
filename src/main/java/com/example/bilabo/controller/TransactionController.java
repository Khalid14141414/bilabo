package com.example.bilabo.controller;

import com.example.bilabo.model.Transaction;
import com.example.bilabo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/show")
    public String showTransaction(@RequestParam("id") int transactionId, Model model) {
        Transaction transaction = transactionService.findTransactionById(transactionId);
        model.addAttribute("transaction", transaction);
        return "transaction-details"; // Assuming you have a corresponding HTML template
    }

    @GetMapping("/list")
    public String showAllTransactions(Model model) {
        List<Transaction> transactions = transactionService.findAllTransactions();
        model.addAttribute("transactions", transactions);
        return "transaction-list"; // Assuming you have a corresponding HTML template
    }

    @PostMapping("/add")
    public String addTransaction(@ModelAttribute("transaction") Transaction transaction, Model model) {
        transactionService.addTransaction(transaction);
        return "redirect:/transactions/list";
    }

    @GetMapping("/delete")
    public String deleteTransaction(@RequestParam("id") int transactionId) {
        transactionService.deleteTransactionById(transactionId);
        return "redirect:/transactions/list";
    }
}

