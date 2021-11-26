package com.graduationproject.studentinformationsystem.common.util.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SisBaseResponseUtil {

    private SisBaseResponseUtil() {
    }

    public static final String SUCCESS = "Success";

    public static <T> ResponseEntity<SisBaseResponse<T>> generateSisResponse(HttpStatus status, String message, T response) {
        SisBaseResponse<T> sisResponse = new SisBaseResponse<>();
        sisResponse.setHttpStatus(status);
        sisResponse.setMessage(message);
        sisResponse.setResult(response);

        return new ResponseEntity<>(sisResponse, status);
    }

    public static <T> ResponseEntity<SisBaseResponse<T>> successResponse(T response) {
        return generateSisResponse(HttpStatus.OK, SUCCESS, response);
    }
}
