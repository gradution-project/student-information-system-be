package com.graduationproject.studentinformationsystem.common.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SurnameValidation implements ConstraintValidator<Surname, Object> {

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;
        int maxLength = 100;
        int minLength = 2;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("length must be between " + minLength + " and " + maxLength).addConstraintViolation();

        int surnameLength = value.toString().length();
        return surnameLength >= minLength && surnameLength <= maxLength;
    }
}
