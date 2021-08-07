package net.apmoller.athena.microservices.CurrencyProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyConversionSavedSearchDto
{
    private String conversionKey;
    private String conversionName;
    private int conversionFactor;
    private boolean status;
    private String createdBy;
    private Date createdDate;
}