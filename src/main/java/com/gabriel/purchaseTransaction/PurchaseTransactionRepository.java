package com.gabriel.purchaseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransaction, UUID> {
}
