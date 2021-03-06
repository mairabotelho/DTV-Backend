package com.zipcode.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Ambassador Request Not Found")
public class AmbassadorRequestNotFoundException extends RuntimeException {
    public AmbassadorRequestNotFoundException() {
        super();
    }
}
