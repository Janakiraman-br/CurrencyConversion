package net.apmoller.athena.microservices.CurrencyProject.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionDto;
import net.apmoller.athena.microservices.CurrencyProject.services.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/currencyconversion")

@ApiResponses(value =
        {
                @ApiResponse(code = 200, message = "Successfully retrieved list"),
                @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
                @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
                @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
                @ApiResponse(code = 202,message = "Inserted sucessfully")
        }
)
@Api(value="Currency Conversion EndPoints", description="Operations are performed in Currency Conversion")

public class CurrencyConversionController
{
    @Autowired
    private CurrencyConversionService currencyConversionService;

    //GET ALL CURRENCY CONVERSION DATAS
    @ApiOperation(value = "To Get All Currency Conversion Records",produces = "application/JSOn")
    @GetMapping
    public Flux<CurrencyConversionDto> getAllDatas()
    {
        return currencyConversionService.getAllCurrencyDatas();
    }

    //INSERT CURRENCY CONVERSION DATA
    @ApiOperation(value = "To Save the Currency Conversion Records into The DataBase", response = HttpStatus.class)
    @PostMapping
    public Mono<CurrencyConversionDto> saveData(@RequestBody Mono<CurrencyConversionDto> currencyConversionDtoMono)
    {
        return currencyConversionService.saveCurrencyData(currencyConversionDtoMono);
    }

    //GET CURRENCY CONVERSION DATA BY CONVERSIONKEY
    @ApiOperation(value = "To Get Currency Conversion Record by Conversion Key",produces = "application/JSOn")
    @GetMapping("/conversionkey/{key}")
    public Mono<CurrencyConversionDto> getByConversionKey(@PathVariable String key)
    {
        return currencyConversionService.getCurrencyDataByCode(key);
    }

    //UPDATE CURRENCY CONVERSION DATA BY CONVERSIONKEY [CHANGING THE STATUS TO FALSE AND INSERTING NEW DATA]
    @ApiOperation(value = "To Save the Currency Conversion Record into The DataBase", response = HttpStatus.class)
    @PutMapping("/update/{key}")
    public Mono<CurrencyConversionDto> updateData(@RequestBody Mono<CurrencyConversionDto> currencyConversionDtoMono, @PathVariable String key)
    {
        return currencyConversionService.updateCurrencyData(currencyConversionDtoMono,key);
    }

    //GET CURRENCY CONVERSION DATA BY CONVERSION NAME
    @ApiOperation(value = "To Get Currency Conversion Record by Conversion Name",produces = "application/JSOn")
   @GetMapping("/name/{name}")
   public Flux<CurrencyConversionDto> getByConversionName(@PathVariable String name)
   {
       return currencyConversionService.getCurrencyDataByName(name);
   }

    //GET CURRENCY CONVERSION DATA BY CONVERSION FACTOR
    @ApiOperation(value = "To Get Currency Conversion Record by Conversion Factor",produces = "application/JSOn")
    @GetMapping("/factor/{conversionFactor}")
    public Flux<CurrencyConversionDto> getByConversionFactor(@PathVariable int conversionFactor)
    {
        return currencyConversionService.getCurrencyDataByConversionFactor(conversionFactor);
    }

    //GET CURRENCY CONVERSION DATA BY STATUS
    @ApiOperation(value = "To Get Currency Conversion Record by Status",produces = "application/JSOn")
    @GetMapping("/status")
    public Flux<CurrencyConversionDto> getByStatus()
    {
        return currencyConversionService.getCurrencyDataByStatus();
    }

    //GET CURRENCY CONVERSION DATA BY CREATED BY
    @ApiOperation(value = "To Get Currency Conversion Record by Creator Name",produces = "application/JSOn")
    @GetMapping("/createdby/{createdBy}")
    public Flux<CurrencyConversionDto> getByCreatedBy(@PathVariable String createdBy)
    {
        return currencyConversionService.getCurrencyDataByCreatedBy(createdBy);
    }

    //GET CURRENCY CODE AND CONVERSION NAME
    @ApiOperation(value = "To Get Currency Conversion Name and Conversion Factor",produces = "application/JSOn")
    @GetMapping("/getnameandfactor")
    public Mono<Map<String, Integer >> getNameAndFactor()
    {
        return currencyConversionService.getNameAndFactor();
    }

    //FOR DROP DOWN
    @ApiOperation(value = "To Get Saved Search Currency Conversion Record by Conversion Name",produces = "application/JSOn")
    @GetMapping("/savedsearch/conversionname")
    public Mono<List<String>> getAllConversionNameSavedSearch()
    {
        return currencyConversionService.getAllConversionNameSavedSearch();
    }

    @ApiOperation(value = "To Save the Searched Currency Conversion Record into The DataBase", response = HttpStatus.class)
    @PostMapping("/savedsearch")
    public Mono<CurrencyConversionDto> saveSearchedData(@RequestBody Mono<CurrencyConversionDto> currencyConversionDtoMono)
    {
        return currencyConversionService.addsearchedcurrency(currencyConversionDtoMono);
    }

    //GET SAVED SEARCH DATA BY CONVERSION NAME
    @ApiOperation(value = "To Get Saved Search Currency Conversion Record by Creator Name",produces = "application/JSOn")
    @GetMapping("/savedsearch/{name}")
    public Flux<CurrencyConversionDto> getCurrencyConversionByName(@PathVariable String name)
    {
        return currencyConversionService.getCurrencyConversionByName(name);
    }

    // DYNAMIC SEARCH
    @ApiOperation(value = "To Get Currency Conversion Record by Dynamic Search",produces = "application/JSOn")
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