package net.apmoller.athena.microservices.CurrencyProject.controller;


import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionDto;
import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionSavedSearchDto;
import net.apmoller.athena.microservices.CurrencyProject.exception.*;
import net.apmoller.athena.microservices.CurrencyProject.services.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/currencyconversion")
public class CurrencyConversionController
{
    @Autowired
    private CurrencyConversionService currencyConversionService;

    //GET ALL CURRENCY CONVERSION DATAS
    @GetMapping
    public Flux<CurrencyConversionDto> getAllDatas()
    {
        return currencyConversionService.getAllCurrencyDatas();
    }

    //INSERT CURRENCY CONVERSION DATA
    @PostMapping
    public Mono<CurrencyConversionDto> saveData(@RequestBody Mono<CurrencyConversionDto> currencyConversionDtoMono)
    {
        return currencyConversionService.saveCurrencyData(currencyConversionDtoMono);
    }

    //GET CURRENCY CONVERSION DATA BY ID
    @GetMapping("conversionkey/{key}")
    public Mono<CurrencyConversionDto> getByConversionKey(@PathVariable String key) throws KeyNotFoundException
    {
        return currencyConversionService.getCurrencyDataByCode(key);
    }

    //UPDATE CURRENCY CONVERSION DATA BY ID
    @PutMapping("/update/{key}")
    public Mono<CurrencyConversionDto> updateData(@RequestBody Mono<CurrencyConversionDto> currencyConversionDtoMono, @PathVariable String key)
    {
        return currencyConversionService.updateCurrencyData(currencyConversionDtoMono,key);
    }
    //GET DATA BY SPECIFIC DATA
   @GetMapping("name/{name}")
   public Flux<CurrencyConversionDto> getByConversionName(@PathVariable String name) throws NameNotFoundException
   {
       return currencyConversionService.getCurrencyDataByName(name);
   }

    @GetMapping("factor/{conversionFactor}")
    public Flux<CurrencyConversionDto> getByConversionFactor(@PathVariable int conversionFactor) throws FactorNotFoundException
    {
        return currencyConversionService.getCurrencyDataByConversionFactor(conversionFactor);
    }
    @GetMapping("status/{status}")
    public Flux<CurrencyConversionDto> getByStatus(@PathVariable boolean status) throws StatusNotFoundException
    {
        return currencyConversionService.getCurrencyDataByStatus(status);
    }

    @GetMapping("createdby/{createdBy}")
    public Flux<CurrencyConversionDto> getByCreatedBy(@PathVariable String createdBy) throws CreatedByNotFoundException
    {
        return currencyConversionService.getCurrencyDataByCreatedBy(createdBy);
    }

    @GetMapping("getcodeandname")
    public Mono<Map<String, Integer >> getCodeAndName()
    {
        return currencyConversionService.getCodeAndFactor();
    }

    @PostMapping("/savedsearch")
    public ResponseEntity addSearchedCurrency(@RequestBody Mono<CurrencyConversionSavedSearchDto> currencyConversionSavedSearchDtoMono)
    {
        return currencyConversionService.addSearchedCurrencyName(currencyConversionSavedSearchDtoMono);
    }

    @GetMapping("/savedsearch/conversionkey")
    public Mono<List<String>> getAllConversionFactorSavedSearch(@PathVariable String conversionfactor)
    {
        return currencyConversionService.getAllConversionKeySavedSearch(conversionfactor);
    }

    @GetMapping("/savedsearch/{name}")
    public Mono<CurrencyConversionSavedSearchDto> getCurrencyConversionByName(@PathVariable String name)
    {
        return currencyConversionService.getCurrencyConversionByName(name);
    }
}