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
        CurrencyConversionError details = new CurrencyConversionError("Invalid","Not found");
        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CreatedByNotFoundException.class)
    public ResponseEntity<CurrencyConversionError> mapException(FactorNotFoundException ex)
    {
        CurrencyConversionError details = new CurrencyConversionError("Invalid","Not found");
        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CreatedByNotFoundException.class)
    public ResponseEntity<CurrencyConversionError> mapException(KeyNotFoundException ex)
    {
        CurrencyConversionError details = new CurrencyConversionError("Invalid","Not found");
        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CreatedByNotFoundException.class)
    public ResponseEntity<CurrencyConversionError> mapException(NameNotFoundException ex)
    {
        CurrencyConversionError details = new CurrencyConversionError("Invalid","Not found");
        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CreatedByNotFoundException.class)
    public ResponseEntity<CurrencyConversionError> mapException(StatusNotFoundException ex)
    {
        CurrencyConversionError details = new CurrencyConversionError("Invalid","Not found");
        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CurrencyConversionError> mapExceptions(Exception e)
    {
        CurrencyConversionError error = new CurrencyConversionError("Kindly check the Details and Enter them correctly", "Your Input is Invalid");
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
