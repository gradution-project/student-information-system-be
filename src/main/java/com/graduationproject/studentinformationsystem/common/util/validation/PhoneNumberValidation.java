package com.graduationproject.studentinformationsystem.common.util.validation;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

import static com.graduationproject.studentinformationsystem.common.util.constant.SisConstants.PHONE_NUMBER_PATTERN;

public class PhoneNumberValidation implements ConstraintValidator<PhoneNumber, Object> {

    public boolean isValid(Object value, final ConstraintValidatorContext context) {
        if (value == null) return true;
        final int length = 10;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("length must be " + length + " and must be this format : " + PHONE_NUMBER_PATTERN)
                .addConstraintViolation();

        value = SisUtil.getUnformattedPhoneNumber(value.toString());

        return NumberUtils.isCreatable(value.toString()) && Objects.equals(length, value.toString().length());
    }
}
