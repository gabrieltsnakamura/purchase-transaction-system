package com.gabriel.purchaseTransaction.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CustomValidator implements ConstraintValidator<ValidTime, String> {

    private String timeFormat;

    @Override
    public void initialize(ValidTime constraintAnnotation) {
        timeFormat = constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) throw new IllegalArgumentException("Time cannot be null");

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat.replace("T", "'T'"));
            LocalDateTime.parse(value, formatter);
            return true;
        } catch (DateTimeException ex) {
            return false;
        }
    }
}
