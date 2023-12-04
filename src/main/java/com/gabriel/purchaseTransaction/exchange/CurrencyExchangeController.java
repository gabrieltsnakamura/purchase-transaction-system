package com.gabriel.purchaseTransaction.exchange;

import com.gabriel.purchaseTransaction.exception.NoExchangeRecordFound;
import com.gabriel.purchaseTransaction.transaction.crud.PurchaseTransaction;
import com.gabriel.purchaseTransaction.exception.TransactionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/exchange")
public class CurrencyExchangeController {

    private final CurrencyExchangeService service;

    public CurrencyExchangeController(CurrencyExchangeService service) {
        this.service = service;
    }

    @GetMapping("/transaction/{id}/{currencyCode}")
    public PurchaseTransaction getTransactionInCurrency(@PathVariable UUID id, @PathVariable String currencyCode) throws TransactionNotFoundException, NoExchangeRecordFound {
        return service.getTransactionInCurrency(id, currencyCode);
    }
}
