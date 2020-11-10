package com.assignment.color.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Safwan
 */
@ControllerAdvice
public class PersonExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PersonExceptionResponse> handleException(PersonNotFoundException e) {
        PersonExceptionResponse exception = new PersonExceptionResponse(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ColorExceptionResponse> handleException(ColorNotFoundException e) {
        ColorExceptionResponse exception = new ColorExceptionResponse(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BadRequestResponse> handleException(Exception e) {
        BadRequestResponse exception = new BadRequestResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
