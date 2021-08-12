package net.apmoller.athena.microservices.CurrencyProject.swaggerConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
@Configuration
@EnableSwagger2
@AllArgsConstructor
public class SwaggerConfiguration {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("net.apmoller.athena.microservices.CurrencyProject.controller"))
                .paths(regex("/currencyconversion.*"))
                .build();
    }

    private ApiInfo metaData()
    {
        ApiInfo apiInfo;
        apiInfo = new ApiInfo(
                "Currency Conversion Rest Api",
                "This is the Api Documentation for the Microservices Currency Conversion",
                "Version 1.0",
                "http://maersk.com",
                "We 4 BackEnd " ,//http://github.com/akash-v-maersk/ we4@maersk.com",
                "License",
                "License URL"
        );
        return apiInfo;
    }
}