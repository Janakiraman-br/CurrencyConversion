//package net.apmoller.athena.microservices.CurrencyProject.controller;
//
//import net.apmoller.athena.microservices.CurrencyProject.exception.*;
//import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversionError;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class ExceptionAdvice
//{
//    @ExceptionHandler(CurrencyConversionException.class)
//    public ResponseEntity<CurrencyConversionError> mapException(CurrencyConversionException ex)
//    {
//        CurrencyConversionError details = new CurrencyConversionError("404 NOT FOUND", ex.getMessage());
//        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<CurrencyConversionError> mapExceptions(Exception e)
//    {
//        CurrencyConversionError error = new CurrencyConversionError("Something Happened", "Please check and correct it");
//        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//}
