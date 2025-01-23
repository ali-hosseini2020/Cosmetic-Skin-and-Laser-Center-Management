package org.example.cosmeticskinandlasercenter.common.util;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;
import org.example.cosmeticskinandlasercenter.common.exception.ValidationException;

public class ValidationUtils {

    public static void notBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(fieldName + " cannot be blank.");
        }
    }

    public static void validateEmail(String email, String fieldName) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new ValidationException("Invalid email format for " + fieldName + ".");
        }
    }

    public static void validatePhoneNumber(String phoneNumber, String fieldName) {
        // Example: Simple US phone number validation (can be made more robust)
        String phoneRegex = "^\\d{10}$"; // 10 digits
        if (!new RegexValidator(phoneRegex).isValid(phoneNumber)) {
            throw new ValidationException("Invalid phone number format for " + fieldName + ". Expected 10 digits.");
        }
    }

    // You can add more validation methods here as needed (e.g., length constraints, range checks, pattern matching, etc.)
}