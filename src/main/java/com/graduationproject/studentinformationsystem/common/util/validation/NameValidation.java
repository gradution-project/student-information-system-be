package com.graduationproject.studentinformationsystem.common.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidation implements ConstraintValidator<Name, Object> {

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;
        int maxLength = 100;
        int minLength = 3;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("length must be between " + minLength + " and " + maxLength).addConstraintViolation();

        int nameLength = value.toString().length();
        return nameLength >= minLength && nameLength <= maxLength;
    }
}
