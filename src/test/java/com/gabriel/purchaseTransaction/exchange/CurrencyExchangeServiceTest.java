package com.gabriel.purchaseTransaction.exchange;

import com.gabriel.purchaseTransaction.transaction.crud.PurchaseTransaction;
import com.gabriel.purchaseTransaction.transaction.crud.PurchaseTransactionRepository;
import com.gabriel.purchaseTransaction.exception.TransactionNotFoundException;
import com.gabriel.purchaseTransaction.exception.NoExchangeRecordFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CurrencyExchangeServiceTest {

    @InjectMocks
    private CurrencyExchangeService service;

    @Mock
    private PurchaseTransactionRepository repository;

    @Mock
    private RestTemplate restTemplate;

    private Exchange exchange;

    @BeforeEach
    public void init() {
        exchange = new Exchange();
        Data data = new Data();
        data.setCurrency("Real");
        data.setExchange_rate(BigDecimal.valueOf(5.5));
        List<Data> dataList = new ArrayList<>();
        dataList.add(data);
        exchange.setData(dataList);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTransactionInCurrency() throws TransactionNotFoundException, NoExchangeRecordFound {
        UUID id = UUID.randomUUID();
        String currencyCode = "Real";
        PurchaseTransaction transaction = new PurchaseTransaction();
        transaction.setId(id);
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setTransactionDate("2022-12-01T14:30:00");

        when(repository.findById(id)).thenReturn(java.util.Optional.of(transaction));
        when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Exchange.class))).thenReturn(exchange);

        PurchaseTransaction result = service.getTransactionInCurrency(id, currencyCode);

        assertEquals(id, result.getId());
        assertEquals(BigDecimal.valueOf(100), result.getAmount());
        assertEquals("2022-12-01T14:30:00", result.getTransactionDate());
        assertEquals(BigDecimal.valueOf(5.5), result.getExchangeRate());
        assertEquals(BigDecimal.valueOf(550).setScale(2, RoundingMode.HALF_EVEN), result.getConvertedAmount());
    }

    @Test
    public void testGetTransactionInCurrency_TransactionNotFoundException() {
        UUID id = UUID.randomUUID();
        String currencyCode = "USD";

        when(repository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(TransactionNotFoundException.class, () -> service.getTransactionInCurrency(id, currencyCode));
    }

    @Test
    public void testGetTransactionInCurrency_NoExchangeRecordFound() {
        UUID id = UUID.randomUUID();
        String currencyCode = "USD";
        PurchaseTransaction transaction = new PurchaseTransaction();
        transaction.setId(id);
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setTransactionDate("2022-12-01T14:30:00");

        when(repository.findById(id)).thenReturn(java.util.Optional.of(transaction));

        exchange.setData(new ArrayList<>());
        when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Exchange.class))).thenReturn(exchange);

        assertThrows(NoExchangeRecordFound.class, () -> service.getTransactionInCurrency(id, currencyCode));
    }
}
