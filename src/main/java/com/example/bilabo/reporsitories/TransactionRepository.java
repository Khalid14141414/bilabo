package com.example.bilabo.reporsitories;

import com.example.bilabo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method to save a new transaction
    public Transaction save(Transaction transaction) {
        String sql = "INSERT INTO transaktion (lejeaftale_id, beløb, dato) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, transaction.getRentalAgreementID(), transaction.getPrice(), transaction.getDate());
        return transaction;
    }


    public Transaction findById(int transactionId) {
        String sql = "SELECT * FROM transaktion WHERE transaktion_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{transactionId}, (resultSet, rowNum) ->
                mapRowToTransaction(resultSet));
    }

    public List<Transaction> findAll() {
        String sql = "SELECT * FROM transaktion";
        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                mapRowToTransaction(resultSet));
    }

    public void deleteById(int transactionId) {
        String sql = "DELETE FROM transaktion WHERE transaktion_id = ?";
        jdbcTemplate.update(sql, transactionId);
    }

    private Transaction mapRowToTransaction(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTransactionID(resultSet.getInt("transaktion_id"));
        transaction.setRentalAgreementID(resultSet.getInt("lejeaftale_id"));
        transaction.setPrice(resultSet.getInt("beløb"));
        transaction.setDate(resultSet.getTimestamp("dato").toLocalDateTime());
        return transaction;
    }
}

