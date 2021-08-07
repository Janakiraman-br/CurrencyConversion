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
    public Mono<CurrencyConversionDto> getCurrencyDataByCode(String key) throws KeyNotFoundException
    {
        return currencyConversionRepository.findById(key).map(AppUtils::currencyConversionEntityToDto).switchIfEmpty(Mono.defer(()->Mono.error(new KeyNotFoundException("Invalid ID Found"))));
    }

    //UPDATE CURRENCY CONVERSION DATA BY ID
    public Mono<CurrencyConversionDto> updateCurrencyData(Mono<CurrencyConversionDto> commodity, String key)
    {
        Mono<CurrencyConversionDto> r = currencyConversionRepository.findById(key).map(AppUtils::currencyConversionEntityToDto);

        currencyConversionRepository.findById(key)
                .flatMap(p -> r.map(AppUtils::currencyConversionDtoToEntity)
                        .doOnNext(e -> e.setConversionKey(key)))
                .doOnNext(e -> e.setStatus(false))
                .flatMap(currencyConversionRepository::save)
                .map(AppUtils::currencyConversionEntityToDto).subscribe();

        return commodity.map(AppUtils::currencyConversionDtoToEntity).flatMap(currencyConversionRepository::insert).map(AppUtils::currencyConversionEntityToDto);
    }

    public Flux<CurrencyConversionDto> getCurrencyDataByName(String conversion_name) throws NameNotFoundException
    {
        return currencyConversionRepository.findByConversionNameIgnoreCase(conversion_name).map(AppUtils::currencyConversionEntityToDto).switchIfEmpty(Mono.defer(()->Mono.error(new NameNotFoundException("Invalid ID Found"))));
    }

    public Flux<CurrencyConversionDto> getCurrencyDataByConversionFactor(int conversionFactor) throws FactorNotFoundException
    {
        return currencyConversionRepository.findByConversionFactorIgnoreCase(conversionFactor).map(AppUtils::currencyConversionEntityToDto).switchIfEmpty(Mono.defer(()->Mono.error(new FactorNotFoundException("Invalid ID Found"))));
    }
    public Flux<CurrencyConversionDto> getCurrencyDataByStatus(boolean status) throws StatusNotFoundException
    {
        return currencyConversionRepository.findByStatusIgnoreCase(status).map(AppUtils::currencyConversionEntityToDto).switchIfEmpty(Mono.defer(()->Mono.error(new StatusNotFoundException("Invalid ID Found"))));
    }

    public Flux<CurrencyConversionDto> getCurrencyDataByCreatedBy(String createdBy) throws CreatedByNotFoundException
    {
        return currencyConversionRepository.findByCreatedByIgnoreCase(createdBy).map(AppUtils::currencyConversionEntityToDto).switchIfEmpty(Mono.defer(()->Mono.error(new CreatedByNotFoundException("Invalid Name of the creator"))));
    }

    public Mono<Map<String, Integer>> getCodeAndFactor()
    {
        boolean s;
        return currencyConversionRepository.findByStatusIgnoreCase(true).map(AppUtils::currencyConversionEntityToDto)
                .collect(Collectors.toMap(a -> a.getConversionName(), a -> a.getConversionFactor()));

    }

    public ResponseEntity addSearchedCurrencyName(Mono<CurrencyConversionSavedSearchDto> currencyConversionSavedSearchDtoMono)
    {
        return new ResponseEntity(currencyConversionSavedSearchDtoMono.map(AppUtils::currencyConversionSavedSearchDtoToEntity).flatMap(currencyConversionSavedSearchRepository::insert), HttpStatus.ACCEPTED);
    }

    public Mono<CurrencyConversionSavedSearchDto> getCurrencyConversionByName(String name)
    {
        return currencyConversionSavedSearchRepository.findById(name).map(AppUtils::currencyConversionSavedSearchEntityToDto);
    }


    public Mono<List<String>> getAllConversionKeySavedSearch(String conversionfactor)
    {
        return currencyConversionSavedSearchRepository.findAll()
                .map(AppUtils::currencyConversionSavedSearchEntityToDto)
                .map(e->e.getConversionKey())
                .collect(Collectors.toList());
    }

}