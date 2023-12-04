package com.gabriel.purchaseTransaction.transaction.crud;

import com.gabriel.purchaseTransaction.validation.ValidTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchase_transactions")
public class PurchaseTransaction {
    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank
    @Size(max = 50)
    private String description;

    @NotNull
    @ValidTime()
    private String transactionDate;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=10, fraction=2)
    private BigDecimal amount;

    private BigDecimal exchangeRate;
    private BigDecimal convertedAmount;

    public PurchaseTransaction() {
    }

    public PurchaseTransaction(String description, String transactionDate, BigDecimal amount) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    // getters and setters


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}
