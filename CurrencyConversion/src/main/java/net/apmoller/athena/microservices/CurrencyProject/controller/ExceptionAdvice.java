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
    @ExceptionHandler(CurrencyConversionException.class)
    public ResponseEntity<CurrencyConversionError> mapException(CreatedByNotFoundException ex)
    {
        CurrencyConversionError details = new CurrencyConversionError("Invalid", ex.getMessage());
        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
