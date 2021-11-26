package com.graduationproject.studentinformationsystem.common.util.validation;

import org.apache.commons.validator.routines.UrlValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class URLValidation implements ConstraintValidator<URL, Object> {

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;

        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(value.toString());
    }
}
