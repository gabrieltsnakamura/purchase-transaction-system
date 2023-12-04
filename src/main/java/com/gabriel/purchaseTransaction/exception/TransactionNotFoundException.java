package com.gabriel.purchaseTransaction.exception;

import java.util.UUID;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException(UUID id) {
        super("Transaction not found with id: " + id);
    }
}
