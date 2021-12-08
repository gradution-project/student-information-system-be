package com.graduationproject.studentinformationsystem.common.util.validation;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class NumberValidation implements ConstraintValidator<Number, Object> {

    private int length;

    public void initialize(Number constraintAnnotation) {
        this.length = constraintAnnotation.length();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("length must be " + length).addConstraintViolation();

        return NumberUtils.isCreatable(value.toString()) && Objects.equals(length, value.toString().length());
    }
}
