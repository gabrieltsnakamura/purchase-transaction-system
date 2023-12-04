package com.gabriel.purchaseTransaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            Error detailedError = new Error();
            detailedError.setField(((org.springframework.validation.FieldError) error).getField());
            detailedError.setMessage(error.getDefaultMessage());
            errors.add(detailedError);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}