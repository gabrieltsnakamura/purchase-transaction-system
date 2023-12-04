package com.gabriel.purchaseTransaction;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=10, fraction=2)
    private BigDecimal amount;

    public PurchaseTransaction() {
    }

    public PurchaseTransaction(String description, Date transactionDate, BigDecimal amount) {
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
