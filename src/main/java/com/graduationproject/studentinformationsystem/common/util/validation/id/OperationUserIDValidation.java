package com.graduationproject.studentinformationsystem.common.util.validation.id;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OperationUserIDValidation implements ConstraintValidator<OperationUserID, Object> {

    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) return true;

        final int officerIdLength = 5;
        final int teacherIdLength = 8;
        final int studentIdLength = 12;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("length must be " + officerIdLength + ", " + teacherIdLength + " or " + studentIdLength)
                .addConstraintViolation();

        final int idLength = value.toString().length();
        return switch (idLength) {
            case officerIdLength, teacherIdLength, studentIdLength -> true;
            default -> false;
        };
    }
}
