package com.example.bilabo.service;

import com.example.bilabo.model.Transaction;
import com.example.bilabo.reporsitories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction findTransactionById(int transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public void deleteTransactionById(int transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}

