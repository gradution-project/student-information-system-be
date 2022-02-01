package com.graduationproject.studentinformationsystem.common.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidation implements ConstraintValidator<Name, Object> {

    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) return true;

        final int maxLength = 100;
        final int minLength = 3;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("length must be between " + minLength + " and " + maxLength)
                .addConstraintViolation();

        final int nameLength = value.toString().length();
        return nameLength >= minLength && nameLength <= maxLength;
    }
}
