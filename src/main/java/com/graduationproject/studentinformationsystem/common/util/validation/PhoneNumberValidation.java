package com.graduationproject.studentinformationsystem.common.util.validation;

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

        value = formattedPhoneNumberToLongTypePhoneNumber(value.toString());

        return NumberUtils.isCreatable(value.toString()) && Objects.equals(length, value.toString().length());
    }

    private String formattedPhoneNumberToLongTypePhoneNumber(String phoneNumber) {
        String[] phoneNumberArr = phoneNumber.split(" ");
        if (phoneNumberArr.length == 5) {
            String first = phoneNumberArr[1].replace("(", "").replace(")", "");
            String second = phoneNumberArr[2];
            String third = phoneNumberArr[3];
            String fourth = phoneNumberArr[4];
            return first + second + third + fourth;
        }
        return "";
    }
}
