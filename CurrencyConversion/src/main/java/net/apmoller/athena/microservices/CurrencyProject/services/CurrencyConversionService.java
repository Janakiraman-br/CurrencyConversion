package net.apmoller.athena.microservices.CurrencyProject.services;

import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionDto;
import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionSavedSearchDto;
import net.apmoller.athena.microservices.CurrencyProject.exception.*;
import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversion;
import net.apmoller.athena.microservices.CurrencyProject.repository.CurrencyConversionRepository;
import net.apmoller.athena.microservices.CurrencyProject.repository.CurrencyConversionSavedSearchRepository;
import net.apmoller.athena.microservices.CurrencyProject.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private CurrencyConversionSavedSearchRepository currencyConversionSavedSearchRepository;
    private CurrencyConversion currencyConversion;

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

    //GET CURRENCY CONVERSION DATA BY ID
    public Mono<CurrencyConversionDto> getCurrencyDataByCode(String key) throws CurrencyConversionException
    {
        return currencyConversionRepository
                .findById(key)
                .map(AppUtils::currencyConversionEntityToDto)
                .switchIfEmpty(Mono.defer(()->Mono.error(new CurrencyConversionException("Conversion Key Not Found"))));
    }

    //UPDATE CURRENCY CONVERSION DATA BY ID
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

    public Flux<CurrencyConversionDto> getCurrencyDataByName(String conversion_name) throws CurrencyConversionException
    {
        return currencyConversionRepository
                .findByConversionNameIgnoreCase(conversion_name)
                .map(AppUtils::currencyConversionEntityToDto)
                .switchIfEmpty(Mono.defer(()->Mono.error(new CurrencyConversionException("Conversion Name Not Found"))));
    }

    public Flux<CurrencyConversionDto> getCurrencyDataByConversionFactor(int conversionFactor) throws CurrencyConversionException
    {
        return currencyConversionRepository
                .findByConversionFactorIgnoreCase(conversionFactor)
                .map(AppUtils::currencyConversionEntityToDto)
                .switchIfEmpty(Mono.defer(()->Mono.error(new CurrencyConversionException("Conversion Factor Not Found"))));
    }
    public Flux<CurrencyConversionDto> getCurrencyDataByStatus(boolean status) throws CurrencyConversionException
    {
        return currencyConversionRepository
                .findByStatus(status)
                .map(AppUtils::currencyConversionEntityToDto)
                .switchIfEmpty(Mono.defer(()->Mono.error(new CurrencyConversionException("Invalid Status Input is Provided"))));
    }

    public Flux<CurrencyConversionDto> getCurrencyDataByCreatedBy(String createdBy) throws CurrencyConversionException
    {
        return currencyConversionRepository
                .findByCreatedByIgnoreCase(createdBy)
                .map(AppUtils::currencyConversionEntityToDto)
                .switchIfEmpty(Mono.defer(()->Mono.error(new CurrencyConversionException("Creator Name Not Found"))));
    }

    public Mono<Map<String, Integer>> getCodeAndFactor()
    {
        System.out.println("Incoming");
        Mono<Map<String, Integer>> collect = currencyConversionRepository.findByStatus(true).map(AppUtils::currencyConversionEntityToDto)
                .collect(Collectors.toMap(a -> a.getConversionName(), a -> a.getConversionFactor()));
        System.out.println(collect);
        return collect;

    }

    public ResponseEntity addSearchedCurrencyName(Mono<CurrencyConversionSavedSearchDto> currencyConversionSavedSearchDtoMono)
    {
        return new ResponseEntity(currencyConversionSavedSearchDtoMono.map(AppUtils::currencyConversionSavedSearchDtoToEntity).flatMap(currencyConversionSavedSearchRepository::insert), HttpStatus.ACCEPTED);
    }

    public Mono<CurrencyConversionSavedSearchDto> getCurrencyConversionByName(String name)
    {
        return currencyConversionSavedSearchRepository
                .findById(name)
                .map(AppUtils::currencyConversionSavedSearchEntityToDto);
    }


    public Mono<List<String>> getAllConversionKeySavedSearch(String conversionfactor)
    {
        return currencyConversionSavedSearchRepository.findAll()
                .map(AppUtils::currencyConversionSavedSearchEntityToDto)
                .map(e->e.getConversionKey())
                .collect(Collectors.toList());
    }

}