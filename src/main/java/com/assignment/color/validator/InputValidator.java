package com.assignment.color.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @author Safwan
 */
@Component
public class InputValidator {
    private static final String COLOR_NAME = "[a-zA-Z]+";


    public boolean isValidColorName(String colorName) {
        return Pattern.matches(COLOR_NAME, colorName);
    }
}
