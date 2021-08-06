package net.apmoller.athena.microservices.CurrencyProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;


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
    private Date createdDate;


}