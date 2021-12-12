package com.graduationproject.studentinformationsystem.common.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidation implements ConstraintValidator<Address, Object> {

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;
        int maxLength = 256;
        int minLength = 1;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("length must be between " + minLength + " and " + maxLength).addConstraintViolation();

        int addressLength = value.toString().length();
        return addressLength >= minLength && addressLength <= maxLength;
    }
}
