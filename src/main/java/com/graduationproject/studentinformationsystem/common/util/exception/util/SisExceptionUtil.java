package com.graduationproject.studentinformationsystem.common.util.exception.util;

import com.graduationproject.studentinformationsystem.common.util.exception.message.SisError;
import com.graduationproject.studentinformationsystem.common.util.exception.message.SisSubError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

public class SisExceptionUtil {

    private SisExceptionUtil() {
    }

    public static SisError generateError(String message, HttpStatus httpStatus) {
        return SisError.builder()
                .requestTime(LocalDateTime.now())
                .httpStatus(httpStatus)
                .message(message)
                .isSuccess(false).build();
    }

    public static SisError generateErrorWithSubErrors(String message, HttpStatus httpStatus, List<SisSubError> subErrors) {
        return generateError(message, httpStatus).withSubErrors(subErrors);
    }

    public static SisError generateErrorWithDetail(String message, HttpStatus httpStatus, String detail) {
        return generateError(message, httpStatus).withDetail(detail);
    }

    public static SisSubError generateSubError(FieldError fieldError) {
        String[] codes;
        String name = "";

        String message = "";
        String type = "";
        String value = "";

        if (isFieldErrorExist(fieldError)) {
            codes = fieldError.getCodes();
            if (codes != null) {
                name = getFieldNameFromCodes(codes);
                type = getTypeFromCodes(codes);
            }
            value = fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : null;
            message = name + " field " + fieldError.getDefaultMessage();
        }

        return SisSubError.builder()
                .message(message)
                .type(type)
                .value(value).build();
    }

    private static boolean isFieldErrorExist(FieldError fieldError) {
        return fieldError != null;
    }

    private static String getFieldNameFromCodes(String[] codes) {
        return StringUtils.substringAfterLast(codes[0], ".");
    }

    private static String getTypeFromCodes(String[] codes) {
        String type = StringUtils.substringAfterLast(codes[3], ".");
        if ("".equals(type)) {
            return StringUtils.substringAfterLast(codes[2], ".");
        }
        return type;
    }
}
