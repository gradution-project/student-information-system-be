package com.graduationproject.studentinformationsystem.common.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidation implements ConstraintValidator<Address, Object> {

    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) return true;

        final int maxLength = 256;
        final int minLength = 1;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("length must be between " + minLength + " and " + maxLength)
                .addConstraintViolation();

        final int addressLength = value.toString().length();
        return addressLength >= minLength && addressLength <= maxLength;
    }
}
