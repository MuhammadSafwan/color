package com.assignment.color.exception;

/**
 * @author Safwan
 */
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String message) {
        super(message);
    }

    public PersonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonNotFoundException(Throwable cause) {
        super(cause);
    }
}
