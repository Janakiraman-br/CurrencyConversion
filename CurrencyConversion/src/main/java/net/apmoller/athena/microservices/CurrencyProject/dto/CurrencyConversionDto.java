package net.apmoller.athena.microservices.CurrencyProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyConversionDto
{

    private String conversionKey;
    private String conversionName;
    private int conversionFactor;
    private boolean status;
    private String createdBy;
    private String createdDate;
    private boolean saved;


}