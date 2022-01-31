package com.graduationproject.studentinformationsystem.common.util.validation;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class OneOrZeroValidation implements ConstraintValidator<OneOrZero, Object> {

    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) return true;

        return NumberUtils.isCreatable(value.toString()) && (Objects.equals(1, value) || Objects.equals(0, value));
    }
}
