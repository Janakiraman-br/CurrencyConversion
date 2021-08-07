package net.apmoller.athena.microservices.CurrencyProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class CreatedByNotFoundException extends Exception
{
    public CreatedByNotFoundException(String message)
    {
        super(message);
    }
}
