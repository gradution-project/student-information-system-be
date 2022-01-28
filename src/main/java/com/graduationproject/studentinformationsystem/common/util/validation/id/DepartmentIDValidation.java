package com.graduationproject.studentinformationsystem.common.util.validation.id;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class DepartmentIDValidation implements ConstraintValidator<DepartmentID, Object> {

    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) return true;

        final int length = 5;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("length must be " + length)
                .addConstraintViolation();

        return NumberUtils.isCreatable(value.toString()) && Objects.equals(length, value.toString().length());
    }
}
