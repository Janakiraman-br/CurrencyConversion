package net.apmoller.athena.microservices.CurrencyProject.repository;

import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversionSavedSearch;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CurrencyConversionSavedSearchRepository extends ReactiveMongoRepository<CurrencyConversionSavedSearch,String>
{

}
