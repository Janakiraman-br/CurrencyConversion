package net.apmoller.athena.microservices.CurrencyProject.repository;

import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversion;
import reactor.core.publisher.Flux;

public interface CurrencyConversionCustomRepo
{
    Flux<CurrencyConversion> findCurrencyProperties(String conversionKey,
                                                    String conversionName,
                                                    Integer conversionFactor,
                                                    boolean status,
                                                    String createdBy,
                                                    String createdDate);
}
