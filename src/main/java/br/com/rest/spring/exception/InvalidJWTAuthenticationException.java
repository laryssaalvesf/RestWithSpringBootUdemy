package br.com.rest.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJWTAuthenticationException extends AuthenticationException {


    public InvalidJWTAuthenticationException(String exception) {
        super(exception);
    }
}
