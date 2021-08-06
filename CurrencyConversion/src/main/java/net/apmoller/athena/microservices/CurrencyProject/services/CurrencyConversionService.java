package net.apmoller.athena.microservices.CurrencyProject.services;

import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionDto;
import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversion;
import net.apmoller.athena.microservices.CurrencyProject.repository.CurrencyConversionRepository;
import net.apmoller.athena.microservices.CurrencyProject.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyConversionService
{
    @Autowired
    private CurrencyConversionRepository currencyConversionRepository;
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
    public Mono<CurrencyConversionDto> getCurrencyDataByCode(String key)
    {
        return currencyConversionRepository.findById(key).map(AppUtils::currencyConversionEntityToDto);
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
    public Flux<CurrencyConversionDto> getCurrencyDataByName(String conversion_name)
    {
        return currencyConversionRepository.findByConversionNameIgnoreCase(conversion_name).map(AppUtils::currencyConversionEntityToDto);
    }

    public Flux<CurrencyConversionDto> getCurrencyDataByConversionFactor(int conversionFactor)
    {
        return currencyConversionRepository.findByConversionFactorIgnoreCase(conversionFactor).map(AppUtils::currencyConversionEntityToDto);
    }
    public Flux<CurrencyConversionDto> getCurrencyDataByStatus(boolean status)
    {
        return currencyConversionRepository.findByStatusIgnoreCase(status).map(AppUtils::currencyConversionEntityToDto);
    }

    public Flux<CurrencyConversionDto> getCurrencyDataByCreatedBy(String createdBy)
    {
        return currencyConversionRepository.findByCreatedByIgnoreCase(createdBy).map(AppUtils::currencyConversionEntityToDto);
    }

    public Mono<Map<String, String>> getCodeAndFactor()
    {
    boolean s;
    return currencyConversionRepository.findByStatusIgnoreCase(true).map(AppUtils::currencyConversionEntityToDto)
            .collect(Collectors.toMap(a -> a.getConversionKey(), a -> a.getConversionName()));

    }

}