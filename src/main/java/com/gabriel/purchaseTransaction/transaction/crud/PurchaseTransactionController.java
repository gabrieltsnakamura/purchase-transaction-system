package com.gabriel.purchaseTransaction.transaction.crud;

import com.gabriel.purchaseTransaction.exception.TransactionNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PurchaseTransactionController {

    private final PurchaseTransactionService service;

    private static final Logger logger = LoggerFactory.getLogger(PurchaseTransactionController.class);

    public PurchaseTransactionController(PurchaseTransactionService service) {
        this.service = service;
    }

    @PostMapping("/transaction")
    public PurchaseTransaction createTransaction(@RequestBody @NotNull @Valid PurchaseTransaction transaction) {
        logger.info("Creating transaction: {}", transaction);
        return service.saveTransaction(transaction.getDescription(), transaction.getTransactionDate(), transaction.getAmount());
    }

    @GetMapping("/transaction/{id}")
    public PurchaseTransaction getTransaction(@PathVariable UUID id) throws TransactionNotFoundException {
        logger.info("Getting transaction: id={}", id);
        return service.getTransactionById(id);
    }

    @GetMapping("/transaction")
    public List<PurchaseTransaction> getAllTransactions() {
        logger.info("Getting all transactions");
        return service.getAllTransactions();
    }

    @PutMapping("/transaction/{id}")
    public PurchaseTransaction updateTransaction(@PathVariable UUID id, @RequestBody @NotNull @Valid PurchaseTransaction transaction) throws TransactionNotFoundException {
        logger.info("Updating transaction: id={}, transaction={}", id, transaction);
        return service.updateTransactionById(id, transaction.getDescription(), transaction.getTransactionDate(), transaction.getAmount());
    }

    @DeleteMapping("/transaction/{id}")
    public void deleteTransaction(@PathVariable UUID id) throws TransactionNotFoundException {
        logger.info("Deleting transaction: id={}", id);
        service.deleteTransactionById(id);
    }
}