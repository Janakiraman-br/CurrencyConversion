package net.apmoller.athena.microservices.CurrencyProject.util;

import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionDto;
import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionSavedSearchDto;
import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversion;
import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversionSavedSearch;
import org.springframework.beans.BeanUtils;

public class AppUtils
{
    public static CurrencyConversionDto currencyConversionEntityToDto(CurrencyConversion currencyConversion)
    {
        CurrencyConversionDto currencyConversionDto=new CurrencyConversionDto();
        BeanUtils.copyProperties(currencyConversion,currencyConversionDto); // Source,Destination ,Copying product components to productDto
        return currencyConversionDto;
    }
    public static CurrencyConversion currencyConversionDtoToEntity(CurrencyConversionDto currencyConversionDto)
    {
        CurrencyConversion currencyConversion = new CurrencyConversion();
        BeanUtils.copyProperties(currencyConversionDto,currencyConversion);
        return currencyConversion;
    }

    public static CurrencyConversionSavedSearchDto currencyConversionSavedSearchEntityToDto(CurrencyConversionSavedSearch currencyConversionSavedSearch)
    {
        CurrencyConversionSavedSearchDto currencyConversionSavedSearchDto=new CurrencyConversionSavedSearchDto();
        BeanUtils.copyProperties(currencyConversionSavedSearch,currencyConversionSavedSearchDto); // Source,Destination ,Copying product components to productDto
        return currencyConversionSavedSearchDto;
    }
    public static CurrencyConversionSavedSearch currencyConversionSavedSearchDtoToEntity(CurrencyConversionSavedSearchDto currencyConversionSavedSearchDto)
    {
        CurrencyConversionSavedSearch currencyConversionSavedSearch = new CurrencyConversionSavedSearch();
        BeanUtils.copyProperties(currencyConversionSavedSearchDto,currencyConversionSavedSearch);
        return currencyConversionSavedSearch;
    }

}

