package net.apmoller.athena.microservices.CurrencyProject.services;

import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionDto;
import net.apmoller.athena.microservices.CurrencyProject.exception.*;
import net.apmoller.athena.microservices.CurrencyProject.repository.CurrencyConversionCustomRepo;
import net.apmoller.athena.microservices.CurrencyProject.repository.CurrencyConversionRepository;
import net.apmoller.athena.microservices.CurrencyProject.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyConversionService
{
    @Autowired
    private CurrencyConversionRepository currencyConversionRepository;

    @Autowired
    private CurrencyConversionCustomRepo customRepo;

    //private CurrencyConversion currencyConversion;

    //GET ALL CURRENCY CONVERSION DATAS
    public Flux<CurrencyConversionDto> getAllCurrencyDatas()
    {
        return currencyConversionRepository.findAll()
                .map(AppUtils::currencyConversionEntityToDto);
    }

    //INSERT CURRENCY CONVERSION DATA
    public Mono<CurrencyConversionDto> saveCurrencyData(Mono<CurrencyConversionDto> currencyConversionDtoMono)
    {
      return currencyConversionDtoMono.map(AppUtils::currencyConversionDtoToEntity)
                .flatMap(currencyConversionRepository::insert)
                .map(AppUtils::currencyConversionEntityToDto);

    }

    //GET CURRENCY CONVERSION DATA BY CONVERSIONKEY
    public Mono<CurrencyConversionDto> getCurrencyDataByCode(String key)
    {
        return currencyConversionRepository
                .findById(key)
                .map(AppUtils::currencyConversionEntityToDto)
                .switchIfEmpty(Mono.defer(()->Mono.error(new CurrencyConversionException("Conversion Key Not Found"))));
    }

    //UPDATE CURRENCY CONVERSION DATA BY CONVERSIONKEY [CHANGING THE STATUS TO FALSE AND INSERTING NEW DATA]
    public Mono<CurrencyConversionDto> updateCurrencyData(Mono<CurrencyConversionDto> commodity, String key)
    {
        Mono<CurrencyConversionDto> r = currencyConversionRepository
                .findById(key)
                .map(AppUtils::currencyConversionEntityToDto);

        currencyConversionRepository.findById(key)
                .flatMap(p -> r.map(AppUtils::currencyConversionDtoToEntity)
                        .doOnNext(e -> e.setConversionKey(key)))
                .doOnNext(e -> e.setStatus(false))
                .flatMap(currencyConversionRepository::save)
                .map(AppUtils::currencyConversionEntityToDto).subscribe();

        return commodity.map(AppUtils::currencyConversionDtoToEntity)
                .flatMap(currencyConversionRepository::insert)
                .map(AppUtils::currencyConversionEntityToDto);

    }

    //GET CURRENCY CONVERSION DATA BY CONVERSION NAME
    public Flux<CurrencyConversionDto> getCurrencyDataByName(String conversion_name)
    {
        return currencyConversionRepository
                .findByConversionNameIgnoreCase(conversion_name)
                .map(AppUtils::currencyConversionEntityToDto)
                .switchIfEmpty(Mono.defer(()->Mono.error(new CurrencyConversionException("Conversion Name Not Found"))));
    }

    //GET CURRENCY CONVERSION DATA BY CONVERSION FACTOR
    public Flux<CurrencyConversionDto> getCurrencyDataByConversionFactor(int conversionFactor)
    {
        return currencyConversionRepository
                .findByConversionFactor(conversionFactor)
                .map(AppUtils::currencyConversionEntityToDto)
                .switchIfEmpty(Mono.defer(()->Mono.error(new CurrencyConversionException("Conversion Factor Not Found"))));
    }

    //GET CURRENCY CONVERSION DATA BY STATUS
    public Flux<CurrencyConversionDto> getCurrencyDataByStatus()
    {
        return currencyConversionRepository
                .findByStatus(true)
                .map(AppUtils::currencyConversionEntityToDto)
                .switchIfEmpty(Mono.defer(()->Mono.error(new CurrencyConversionException("Invalid Status Input is Provided"))));
    }

    //GET CURRENCY CONVERSION DATA BY CREATED BY
    public Flux<CurrencyConversionDto> getCurrencyDataByCreatedBy(String createdBy)
    {
        return currencyConversionRepository
                .findByCreatedByIgnoreCase(createdBy)
                .map(AppUtils::currencyConversionEntityToDto)
                .switchIfEmpty(Mono.defer(()->Mono.error(new CurrencyConversionException("Creator Name Not Found"))));
    }

    //GET CURRENCY CODE AND CONVERSION NAME
    public Mono<Map<String, Integer>> getNameAndFactor()
    {
        System.out.println("Incoming");
        return  currencyConversionRepository.findByStatus(true).map(AppUtils::currencyConversionEntityToDto)
                .collect(Collectors.toMap(a -> a.getConversionName(), a -> a.getConversionFactor()));
        //System.out.println(collect);


    }

    //GET SAVED SEARCH DATA BY CONVERSION NAME
    public Flux<CurrencyConversionDto> getCurrencyConversionByName(String name)
    {
        return currencyConversionRepository
                .findByconversionName(name)
                .filter(a->a.isSaved())
                .filter(a->a.isStatus())
                .map(AppUtils::currencyConversionEntityToDto);
    }

    //INSERT SAVED SEARCH
    public Mono<CurrencyConversionDto> addsearchedcurrency(Mono<CurrencyConversionDto> currencyConversionDtoMono)
    {
        return currencyConversionDtoMono
                .map(AppUtils::currencyConversionDtoToEntity)
                .flatMap(currencyConversionRepository::save)
                .map(AppUtils::currencyConversionEntityToDto);
    }

    //FOR DROP DOWN
    public Mono<List<String>> getAllConversionNameSavedSearch()
    {
        return currencyConversionRepository
                .findAll()
                .filter(a->a.isSaved())
                .filter(a->a.isStatus())
                .map(AppUtils::currencyConversionEntityToDto)
                .map(e->e.getConversionName())
                .distinct()
                .collect(Collectors.toList());
    }

    //DYNAMIC SEARCH
    public Flux<CurrencyConversionDto> getByProp(String conversionKey, String conversionName, Integer conversionFactor, boolean status, String createdBy, String createdDate)
    {
        return customRepo
                .findCurrencyProperties(conversionKey,conversionName,conversionFactor,status,createdBy,createdDate)
                .filter(a->a.isStatus())
                .map(AppUtils::currencyConversionEntityToDto);
    }
}