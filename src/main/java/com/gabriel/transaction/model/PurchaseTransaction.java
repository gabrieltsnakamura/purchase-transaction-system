package com.gabriel.transaction.model;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "purchase_transactions")
public class PurchaseTransaction {
    @Id
    @GeneratedValue
    private UUID id;
    private String description;
    private Date transactionDate;
    private double amount;

    public PurchaseTransaction(String description, Date transactionDate, double amount) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    // getters and setters
}
