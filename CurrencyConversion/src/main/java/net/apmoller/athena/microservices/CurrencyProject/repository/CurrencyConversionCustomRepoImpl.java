package net.apmoller.athena.microservices.CurrencyProject.repository;

import net.apmoller.athena.microservices.CurrencyProject.models.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyConversionCustomRepoImpl implements CurrencyConversionCustomRepo
{
    @Autowired
    public ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<CurrencyConversion> findCurrencyProperties(String conversionKey, String conversionName, Integer conversionFactor, boolean status, String createdBy, String createdDate)
    {
        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();

        if (conversionKey != null && !conversionKey.isEmpty())
            criteria.add(Criteria.where("conversionKey").is(conversionKey));

        if (conversionName != null && !conversionName.isEmpty())
            criteria.add(Criteria.where("conversionName").is(conversionName));

     if (conversionFactor != null )
          criteria.add(Criteria.where("conversionFactor").is(conversionFactor));

       // if (status ==true || status==false)
            //criteria.add(Criteria.where("status").is(status));

        if (createdBy != null && !createdBy.isEmpty())
            criteria.add(Criteria.where("createdBy").is(createdBy));

        if (createdDate != null && !createdDate.isEmpty())
            criteria.add(Criteria.where("createdDate").is(createdDate));

        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        return mongoTemplate.find(query, CurrencyConversion.class);

    }
}
