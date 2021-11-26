package com.graduationproject.studentinformationsystem.common.util.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SisBaseResponseUtil {

    private SisBaseResponseUtil() {
    }

    public static final String SUCCESS = "Success";

    private static final String DEFAULT_FOUND_MESSAGE = "Found Successfully";
    private static final String DEFAULT_SAVED_MESSAGE = "Saved Successfully";
    private static final String DEFAULT_UPDATED_MESSAGE = "Updated Successfully";
    private static final String DEFAULT_DELETED_MESSAGE = "Deleted Successfully";

    public static <T> ResponseEntity<SisBaseResponse<T>> generateSisResponse(HttpStatus status, String message, T response) {
        SisBaseResponse<T> sisResponse = new SisBaseResponse<>();
        sisResponse.setHttpStatus(status);
        sisResponse.setMessage(message);
        sisResponse.setResult(response);

        return new ResponseEntity<>(sisResponse, status);
    }

    public static <T> ResponseEntity<SisBaseResponse<T>> successResponse(T response, String message) {
        return generateSisResponse(HttpStatus.OK, message, response);
    }

    public static <T> ResponseEntity<SisBaseResponse<T>> defaultSuccessResponseFound(T result) {
        return successResponse(result, DEFAULT_FOUND_MESSAGE);
    }

    public static <T> ResponseEntity<SisBaseResponse<T>> defaultSuccessResponseSaved(T result) {
        return successResponse(result, DEFAULT_SAVED_MESSAGE);
    }

    public static <T> ResponseEntity<SisBaseResponse<T>> defaultSuccessResponseUpdated(T result) {
        return successResponse(result, DEFAULT_UPDATED_MESSAGE);
    }

    public static <T> ResponseEntity<SisBaseResponse<T>> defaultSuccessResponseDeleted(T result) {
        return successResponse(result, DEFAULT_DELETED_MESSAGE);
    }
}
