package br.com.rest.spring.exception.handler;

import br.com.rest.spring.exception.ExceptionResponse;
import br.com.rest.spring.exception.UnsuportedOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UnsuportedOperationException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception exception, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
