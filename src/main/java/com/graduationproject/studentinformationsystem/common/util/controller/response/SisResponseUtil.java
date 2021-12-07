package com.graduationproject.studentinformationsystem.common.util.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class SisResponseUtil {

    private SisResponseUtil() {
    }

    public static <T> ResponseEntity<SisBaseApiResponse<T>> successResponse(T result) {
        return new ResponseEntity<>(
                SisBaseApiResponse.<T>builder()
                        .requestTime(LocalDateTime.now())
                        .httpStatus(HttpStatus.OK)
                        .isSuccess(true)
                        .result(result).build(), HttpStatus.OK);
    }
}
