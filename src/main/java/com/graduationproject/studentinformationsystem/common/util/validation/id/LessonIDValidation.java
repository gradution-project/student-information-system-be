package com.graduationproject.studentinformationsystem.common.util.validation.id;

import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class LessonIDValidation implements ConstraintValidator<LessonID, Object> {

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;
        int length = 8;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("length must be " + length).addConstraintViolation();

        return NumberUtils.isCreatable(value.toString()) && Objects.equals(length, value.toString().length());
    }
}
