package net.apmoller.athena.microservices.CurrencyProject.controller;


import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionDto;
import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionSavedSearchDto;
import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversion;
import net.apmoller.athena.microservices.CurrencyProject.services.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    @GetMapping("/conversionkey/{key}")
    public Mono<CurrencyConversionDto> getByConversionKey(@PathVariable String key)
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
   public Flux<CurrencyConversionDto> getByConversionName(@PathVariable String name)
   {
       return currencyConversionService.getCurrencyDataByName(name);
   }

    @GetMapping("factor/{conversionFactor}")
    public Flux<CurrencyConversionDto> getByConversionFactor(@PathVariable int conversionFactor)
    {
        return currencyConversionService.getCurrencyDataByConversionFactor(conversionFactor);
    }
    @GetMapping("status/{status}")
    public Flux<CurrencyConversionDto> getByStatus(@PathVariable boolean status)
    {
        return currencyConversionService.getCurrencyDataByStatus(status);
    }

    @GetMapping("createdby/{createdBy}")
    public Flux<CurrencyConversionDto> getByCreatedBy(@PathVariable String createdBy)
    {
        return currencyConversionService.getCurrencyDataByCreatedBy(createdBy);
    }

    @GetMapping("/getcodeandname")
    public Mono<Map<String, Integer >> getCodeAndName()
    {
        return currencyConversionService.getCodeAndFactor();
    }

    @PutMapping("/savedsearch/update")
    public Mono<CurrencyConversionDto> updateSaved(@RequestBody Mono<CurrencyConversionDto> data)
    {
        return currencyConversionService.updateSaved(data);
    }

    //for drop down
    @GetMapping("/savedsearch/conversionname")
    public Mono<List<String>> getAllConversionNameSavedSearch()
    {
        return currencyConversionService.getAllConversionNameSavedSearch();
    }

    @GetMapping("/savedsearch/{name}")
    public Flux<CurrencyConversionDto> getCurrencyConversionByName(@PathVariable String name)
    {
        return currencyConversionService.getCurrencyConversionByName(name);
    }


}