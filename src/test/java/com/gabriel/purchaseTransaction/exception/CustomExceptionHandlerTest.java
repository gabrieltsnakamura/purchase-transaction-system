package com.gabriel.purchaseTransaction.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomExceptionHandlerTest {

    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleValidationExceptions() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);

        when(ex.getBindingResult()).thenReturn(bindingResult);
        FieldError fieldError = new FieldError("objectName", "description", "Description cannot be empty");
        List<ObjectError> objectErrorList = new ArrayList<>();
        objectErrorList.add(fieldError);
        when(bindingResult.getAllErrors()).thenReturn(objectErrorList);
        ResponseEntity<List<Error>> response = customExceptionHandler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("description", response.getBody().get(0).getField());
    }

    @Test
    public void testHandleTransactionNotFoundException() {
        TransactionNotFoundException ex = new TransactionNotFoundException(UUID.randomUUID());

        ResponseEntity<Error> response = customExceptionHandler.handleTransactionNotFoundException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testHandleNoSuchElementException() {
        NoExchangeRecordFound ex = new NoExchangeRecordFound("No exchange data found within 6 months of transaction date");

        ResponseEntity<Error> response = customExceptionHandler.handleNoSuchElementException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
