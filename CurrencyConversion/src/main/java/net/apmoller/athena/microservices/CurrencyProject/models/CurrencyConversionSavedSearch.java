package net.apmoller.athena.microservices.CurrencyProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "currencyconversionsavedsearch")
public class CurrencyConversionSavedSearch
{
    @Id
    private String conversionKey;
    private String conversionName;
    private int conversionFactor;
    private boolean status;
    private String createdBy;
    private Date createdDate;
}