package net.apmoller.athena.microservices.CurrencyProject.controller;

import net.apmoller.athena.microservices.CurrencyProject.exception.*;
import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversionError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice
{
    @ExceptionHandler(CreatedByNotFoundException.class)
    public ResponseEntity<CurrencyConversionError> mapException(CreatedByNotFoundException ex)
    {
        CurrencyConversionError details = new CurrencyConversionError("Invalid", ex.getMessage());
        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FactorNotFoundException.class)
    public ResponseEntity<CurrencyConversionError> mapException1(FactorNotFoundException ex)
    {
        CurrencyConversionError details = new CurrencyConversionError("Invalid","Not found");
        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(KeyNotFoundException.class)
    public ResponseEntity<CurrencyConversionError> mapException2(KeyNotFoundException ex)
    {
        CurrencyConversionError details = new CurrencyConversionError("Invalid","Not found");
        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NameNotFoundException.class)
    public ResponseEntity<CurrencyConversionError> mapException3(NameNotFoundException ex)
    {
        CurrencyConversionError details = new CurrencyConversionError("Invalid","Not found");
        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<CurrencyConversionError> mapException4(StatusNotFoundException ex)
    {
        CurrencyConversionError details = new CurrencyConversionError("Invalid","Not found");
        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CurrencyConversionError> mapExceptions5(Exception e)
    {
        CurrencyConversionError error = new CurrencyConversionError("Kindly check the Details and Enter them correctly", "Your Input is Invalid");
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
