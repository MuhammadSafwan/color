package com.assignment.color.exception;

/**
 * @author Safwan
 */
public class ColorNotFoundException extends RuntimeException {
    public ColorNotFoundException(String message) {
        super(message);
    }

    public ColorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColorNotFoundException(Throwable cause) {
        super(cause);
    }
}
