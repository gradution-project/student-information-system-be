package com.graduationproject.studentinformationsystem.common.util.validation;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class AddressValidation implements ConstraintValidator<Address, Object> {

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;
        int maxLength = 256;
        int minLength = 1;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("length must be between " + minLength + " and " + maxLength).addConstraintViolation();

        return NumberUtils.isCreatable(value.toString()) && Objects.equals(maxLength, value.toString().length());
    }
}
