package net.apmoller.athena.microservices.CurrencyProject.util;

import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionDto;
import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversion;
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
}

