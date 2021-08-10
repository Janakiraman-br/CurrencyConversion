package net.apmoller.athena.microservices.CurrencyProject.models;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "currencyconversion")
public class CurrencyConversion
{

    @Id
    private String conversionKey;
    private String conversionName;
    private int conversionFactor;
    private boolean status;
    private String createdBy;
    private String createdDate;
    private boolean saved;


}