package com.graduationproject.studentinformationsystem.common.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SurnameValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Surname {
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default " length must be between minLength and maxLength";
}
