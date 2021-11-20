package com.graduationproject.studentinformationsystem.common.util.validation;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyNumberValidation implements ConstraintValidator<OnlyNumber, Object> {

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return NumberUtils.isCreatable(value.toString());
    }
}
