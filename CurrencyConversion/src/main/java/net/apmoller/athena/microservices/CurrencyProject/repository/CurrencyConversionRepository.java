package net.apmoller.athena.microservices.CurrencyProject.repository;

import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.channels.FileChannel;

@Repository
public interface CurrencyConversionRepository extends ReactiveMongoRepository<CurrencyConversion,String>
{

    Flux<CurrencyConversion> findByConversionNameIgnoreCase(String conversion_name);
    Flux<CurrencyConversion> findByConversionFactorIgnoreCase(int conversionFactor);
    Flux<CurrencyConversion> findByStatusIgnoreCase(boolean status);
    Flux<CurrencyConversion> findByCreatedByIgnoreCase(String createdBy);


}
