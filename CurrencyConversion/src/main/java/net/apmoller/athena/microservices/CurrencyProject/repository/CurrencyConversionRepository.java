package net.apmoller.athena.microservices.CurrencyProject.repository;

import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface CurrencyConversionRepository extends ReactiveMongoRepository<CurrencyConversion,String>
{

    Flux<CurrencyConversion> findByConversionNameIgnoreCase(String conversion_name);

    Flux<CurrencyConversion> findByStatus(boolean status);

    Flux<CurrencyConversion> findByCreatedByIgnoreCase(String createdBy);

    Flux<CurrencyConversion> findByConversionFactor(int conversionFactor);

    Flux<CurrencyConversion> findByconversionName(String conversionname);


}
