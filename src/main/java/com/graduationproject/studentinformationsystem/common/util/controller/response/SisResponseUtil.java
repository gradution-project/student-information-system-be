package com.graduationproject.studentinformationsystem.common.util.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class SisResponseUtil {

    private SisResponseUtil() {
    }

    public static ResponseEntity<SisApiResponse> successResponse() {
        return new ResponseEntity<>(
                SisApiResponse.builder()
                        .requestTime(LocalDateTime.now())
                        .httpStatus(HttpStatus.OK)
                        .isSuccess(true).build(), HttpStatus.OK);
    }

    public static ResponseEntity<SisApiResponse> unauthorizedResponse() {
        return new ResponseEntity<>(
                SisApiResponse.builder()
                        .requestTime(LocalDateTime.now())
                        .httpStatus(HttpStatus.UNAUTHORIZED)
                        .isSuccess(false).build(), HttpStatus.UNAUTHORIZED);
    }

    public static <T> ResponseEntity<SisBaseApiResponse<T>> successResponse(final T response) {
        return new ResponseEntity<>(
                SisBaseApiResponse.<T>builder()
                        .requestTime(LocalDateTime.now())
                        .httpStatus(HttpStatus.OK)
                        .isSuccess(true)
                        .response(response).build(), HttpStatus.OK);
    }
}
