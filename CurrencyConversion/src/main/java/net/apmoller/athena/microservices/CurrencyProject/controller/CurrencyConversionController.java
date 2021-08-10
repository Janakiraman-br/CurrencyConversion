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

    //GET CURRENCY CONVERSION DATA BY CONVERSIONKEY
    @GetMapping("/conversionkey/{key}")
    public Mono<CurrencyConversionDto> getByConversionKey(@PathVariable String key)
    {
        return currencyConversionService.getCurrencyDataByCode(key);
    }

    //UPDATE CURRENCY CONVERSION DATA BY CONVERSIONKEY
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

    @GetMapping("/new/{ckey}/{cname}")
    public Flux<CurrencyConversionDto> getnew(@PathVariable String ckey, @PathVariable String cname)
    {
        return currencyConversionService.getnew(ckey, cname);
    }

    @GetMapping("/new/{ckey}/{cname}/{ccreatedby}")
    public Flux<CurrencyConversionDto> getnew1(@PathVariable String ckey, @PathVariable String cname, @PathVariable String ccreatedby)
    {
        return currencyConversionService.getnew1(ckey, cname,ccreatedby);
    }

    @GetMapping("/new2/{ckey}/{cname}/{cfactor}")
    public Flux<CurrencyConversionDto> getnew2(@PathVariable String ckey, @PathVariable String cname, @PathVariable int cfactor)
    {
        return currencyConversionService.getnew2(ckey, cname,cfactor);
    }

    @GetMapping("/new2/{ckey}/{cname}/{cfactor}/{ccreatedby}")
    public Flux<CurrencyConversionDto> getnew3(@PathVariable String ckey, @PathVariable String cname, @PathVariable int cfactor, @PathVariable String ccreatedby)
    {
        return currencyConversionService.getnew3(ckey, cname,cfactor,ccreatedby);
    }

    @GetMapping("/new4/{cfactor}/{ccreatedby}")
    public Flux<CurrencyConversionDto> getnew4(@PathVariable int cfactor, @PathVariable String ccreatedby)
    {
        return currencyConversionService.getnew4(cfactor, ccreatedby);
    }

    @GetMapping("/new5/{ckey}/{cfactor}")
    public Flux<CurrencyConversionDto> getnew5(@PathVariable String ckey, @PathVariable int cfactor)
    {
        return currencyConversionService.getnew5(ckey,cfactor);
    }

    @GetMapping("/new6/{ckey}/{ccreatedby}")
    public Flux<CurrencyConversionDto> getnew6(@PathVariable String ckey, @PathVariable String ccreatedby)
    {
        return currencyConversionService.getnew6(ckey, ccreatedby);
    }
}