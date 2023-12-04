package com.gabriel.purchaseTransaction.exchange;

import com.gabriel.purchaseTransaction.exception.NoExchangeRecordFound;
import com.gabriel.purchaseTransaction.transaction.crud.PurchaseTransaction;
import com.gabriel.purchaseTransaction.transaction.crud.PurchaseTransactionRepository;
import com.gabriel.purchaseTransaction.exception.TransactionNotFoundException;
import com.gabriel.purchaseTransaction.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CurrencyExchangeService {

    private final PurchaseTransactionRepository repository;
    private final RestTemplate restTemplate;

@Autowired
    public CurrencyExchangeService(PurchaseTransactionRepository repository, RestTemplate restTemplate) throws NoExchangeRecordFound {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public PurchaseTransaction getTransactionInCurrency(UUID id, String currencyCode) throws TransactionNotFoundException, NoExchangeRecordFound {
        PurchaseTransaction transaction = repository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        List<Data> exchangeData = getExchangeData(currencyCode, transaction.getTransactionDate());

        try {
            Data closestData = exchangeData.getLast();
            BigDecimal convertedAmount = transaction.getAmount().multiply(closestData.getExchange_rate());
            convertedAmount = convertedAmount.setScale(2, RoundingMode.HALF_EVEN);
            transaction.setExchangeRate(closestData.getExchange_rate());
            transaction.setConvertedAmount(convertedAmount);
        } catch (NoSuchElementException e) {
            throw new NoExchangeRecordFound("No exchange record found for " + currencyCode + " on " + transaction.getTransactionDate() + ".");
        }
        return transaction;
    }

    public List<Data> getExchangeData(String currencyCode, String date) {
        String url = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service" +
                "/v1/accounting/od/rates_of_exchange"
                + "?filter=record_date:lte:" + DateUtils.formatDate(LocalDateTime.parse(date), "yyyy-MM-dd")
                + ",currency:eq:" + currencyCode;
        Exchange response = restTemplate.getForObject(url, Exchange.class);
        return response.getData();
    }
}