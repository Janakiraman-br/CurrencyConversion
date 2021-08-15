package net.apmoller.athena.microservices.CurrencyProject.controller;

import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionDto;
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

    //INSERT CURRENCY CONVERSION DATA
    @PostMapping
    public Mono<CurrencyConversionDto> saveData(@RequestBody Mono<CurrencyConversionDto> currencyConversionDtoMono)
    {
        return currencyConversionService.saveCurrencyData(currencyConversionDtoMono);
    }

    //INSERT SAVED SEARCH
    @PutMapping("/savedsearch")
    public Mono<CurrencyConversionDto> saveSearchedData(@RequestBody Mono<CurrencyConversionDto> currencyConversionDtoMono)
    {
        return currencyConversionService.addsearchedcurrency(currencyConversionDtoMono);
    }

    //UPDATE CURRENCY CONVERSION DATA BY CONVERSIONKEY [CHANGING THE STATUS TO FALSE AND INSERTING NEW DATA]
    @PutMapping("/update/{key}")
    public Mono<CurrencyConversionDto> updateData(@RequestBody Mono<CurrencyConversionDto> currencyConversionDtoMono, @PathVariable String key)
    {
        return currencyConversionService.updateCurrencyData(currencyConversionDtoMono,key);
    }

    //GET ALL CURRENCY CONVERSION DATAS
    @GetMapping
    public Flux<CurrencyConversionDto> getAllDatas()
    {
        return currencyConversionService.getAllCurrencyDatas();
    }

    //GET CURRENCY CONVERSION DATA BY CONVERSIONKEY
    @GetMapping("/conversionkey/{key}")
    public Mono<CurrencyConversionDto> getByConversionKey(@PathVariable String key)
    {
        return currencyConversionService.getCurrencyDataByCode(key);
    }

    //GET CURRENCY CONVERSION DATA BY CONVERSION NAME
    @GetMapping("/name/{name}")
    public Flux<CurrencyConversionDto> getByConversionName(@PathVariable String name)
    {
        return currencyConversionService.getCurrencyDataByName(name);
    }

    //GET CURRENCY CONVERSION DATA BY CONVERSION FACTOR
     @GetMapping("/factor/{conversionFactor}")
     public Flux<CurrencyConversionDto> getByConversionFactor(@PathVariable int conversionFactor)
     {
        return currencyConversionService.getCurrencyDataByConversionFactor(conversionFactor);
     }

    //GET CURRENCY CONVERSION DATA BY CREATED BY
    @GetMapping("/createdby/{createdBy}")
    public Flux<CurrencyConversionDto> getByCreatedBy(@PathVariable String createdBy)
    {
        return currencyConversionService.getCurrencyDataByCreatedBy(createdBy);
    }

    //GET CURRENCY CONVERSION DATA BY STATUS
    @GetMapping("/status")
    public Flux<CurrencyConversionDto> getByStatus()
    {
        return currencyConversionService.getCurrencyDataByStatus();
    }

    //GET CURRENCY CODE AND CONVERSION NAME
    @GetMapping("/getname")
    public Mono<List<String>> getName()
    {
        return currencyConversionService.getName();
    }
    //Get Conversion Factor
    @GetMapping("/getfactor/{conversionName}")
    public Mono<List<Integer>> getFactor(@PathVariable String conversionName)
    {
        return  currencyConversionService.getFactor(conversionName);
    }
    //FOR DROP DOWN
    @GetMapping("/savedsearch/conversionname")
    public Mono<List<String>> getAllConversionNameSavedSearch()
    {
        return currencyConversionService.getAllConversionNameSavedSearch();
    }

    //GET SAVED SEARCH DATA BY CONVERSION NAME
    @GetMapping("/savedsearch/{name}")
    public Flux<CurrencyConversionDto> getCurrencyConversionByName(@PathVariable String name)
    {
        return currencyConversionService.getCurrencyConversionByName(name);
    }

    // DYNAMIC SEARCH
    @GetMapping("/customrepo")
    public Flux<CurrencyConversionDto> getCurrencyByProperties(  @RequestParam(required = false) String conversionKey,
                                                                 @RequestParam(required = false) String conversionName,
                                                                 @RequestParam(required = false) Integer conversionFactor,
                                                                 @RequestParam(required = false) boolean status,
                                                                 @RequestParam(required = false) String createdBy,
                                                                 @RequestParam(required = false) String createdDate)
    {
        return currencyConversionService.getByProp(conversionKey,conversionName,conversionFactor,status,createdBy,createdDate);
    }
}