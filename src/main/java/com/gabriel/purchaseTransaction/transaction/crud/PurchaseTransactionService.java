package com.gabriel.purchaseTransaction.transaction.crud;
import com.gabriel.purchaseTransaction.exception.TransactionNotFoundException;
import com.gabriel.purchaseTransaction.exchange.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseTransactionService {
    private final PurchaseTransactionRepository repository;

    @Autowired
    public PurchaseTransactionService(PurchaseTransactionRepository repository, CurrencyExchangeService currencyExchangeService) {
        this.repository = repository;
    }

    public PurchaseTransaction saveTransaction(String description, String transactionDate, BigDecimal amount) {
        PurchaseTransaction transaction = new PurchaseTransaction(description, transactionDate, amount);
        return repository.save(transaction);
    }

    public PurchaseTransaction getTransactionById(UUID id) throws TransactionNotFoundException {
        return repository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
    }

    public void deleteTransactionById(UUID id) throws TransactionNotFoundException {
        PurchaseTransaction transaction = getTransactionById(id);
        repository.delete(transaction);
    }

    public PurchaseTransaction updateTransactionById(UUID id, String description, String transactionDate, BigDecimal amount) throws TransactionNotFoundException {
        PurchaseTransaction transaction = getTransactionById(id);
        transaction.setDescription(description);
        transaction.setTransactionDate(transactionDate);
        transaction.setAmount(amount);
        return repository.save(transaction);
    }

    public List<PurchaseTransaction> getAllTransactions() {
        return repository.findAll();
    }

}
