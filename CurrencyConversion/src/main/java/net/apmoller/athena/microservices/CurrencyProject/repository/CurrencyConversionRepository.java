package net.apmoller.athena.microservices.CurrencyProject.repository;

import net.apmoller.athena.microservices.CurrencyProject.dto.CurrencyConversionDto;
import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversion;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.channels.FileChannel;

@Repository
public interface CurrencyConversionRepository extends ReactiveMongoRepository<CurrencyConversion,String>
{

    Flux<CurrencyConversion> findByConversionNameIgnoreCase(String conversion_name);
    Flux<CurrencyConversion> findByStatus(boolean status);
    Flux<CurrencyConversion> findByCreatedByIgnoreCase(String createdBy);


    Flux<CurrencyConversion> findByConversionFactor(int conversionFactor);

    Flux<CurrencyConversion> findBysaved(boolean b);

    Flux<CurrencyConversion> findByconversionName(String conversionname);

    @Query("{ 'conversionKey':?0 , 'conversionName' :?1 }")
    Flux<CurrencyConversionDto> findByconversionKeyAndconversionName(String ckey, String cname);

    @Query("{ 'conversionKey':?0 , 'conversionName' :?1 ,'createdBy' :?2 }")
    Flux<CurrencyConversionDto> findByconversionKeyAndconversionNameAndcreatedBy(String ckey, String cname, String ccreatedby);

    @Query("{ 'conversionKey':?0 , 'conversionName' :?1 ,'conversionFactor' :?2 }")
    Flux<CurrencyConversionDto> findByconversionKeyAndconversionNameAndconversionFactor(String ckey, String cname, int cfactor);

    @Query("{ 'conversionKey':?0 , 'conversionName' :?1 ,'conversionFactor' :?2 ,'createdBy' :?3}")
    Flux<CurrencyConversionDto> findByconversionKeyAndconversionNameAndconversionFactorAndcreatedBy(String ckey, String cname, int cfactor, String ccreatedby);

    @Query("{'conversionFactor' :?0 ,'createdBy' :?1}")
    Flux<CurrencyConversionDto> findByconversionFactorAndcreatedBy(int cfactor, String ccreatedby);

    @Query("{ 'conversionKey':?0 , 'conversionFactor' :?1}")
    Flux<CurrencyConversionDto> findByconversionKeyAndconversionFactor(String ckey, int cfactor);

    @Query("{'conversionKey' :?0 ,'createdBy' :?1}")
    Flux<CurrencyConversionDto> findByconversionKeyAndcreatedBy(String ckey, String ccreatedby);
}
