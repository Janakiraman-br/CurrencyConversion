package net.apmoller.athena.microservices.CurrencyProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NameNotFoundException extends Exception
{
    public NameNotFoundException(String message)
    {
        super(message);
    }
}
