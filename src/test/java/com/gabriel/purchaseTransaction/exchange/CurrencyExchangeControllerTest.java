package com.gabriel.purchaseTransaction.exchange;

import com.gabriel.purchaseTransaction.transaction.crud.PurchaseTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CurrencyExchangeControllerTest {

    @InjectMocks
    private CurrencyExchangeController controller;

    @Mock
    private CurrencyExchangeService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTransactionInCurrency() throws Exception {
        CurrencyExchangeController controller = new CurrencyExchangeController(service);
        when(service.getTransactionInCurrency(Mockito.any(UUID.class), Mockito.anyString())).thenReturn(new PurchaseTransaction());
        PurchaseTransaction transaction = controller.getTransactionInCurrency(UUID.randomUUID(), "Real");

        assertNotNull(transaction);
    }
}
