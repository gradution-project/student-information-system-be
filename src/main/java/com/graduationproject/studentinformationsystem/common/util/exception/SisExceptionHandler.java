package com.graduationproject.studentinformationsystem.common.util.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.message.SisError;
import com.graduationproject.studentinformationsystem.common.util.exception.message.SisSubError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.util.exception.util.SisExceptionConstants.VALIDATION_ERROR;
import static com.graduationproject.studentinformationsystem.common.util.exception.util.SisExceptionUtil.*;

@RestControllerAdvice
public class SisExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<SisSubError> subErrors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            subErrors.add(generateSubError(fieldError));
        });
        SisError error = generateErrorWithSubErrors(VALIDATION_ERROR, status, subErrors);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(SisDatabaseException.class)
    protected ResponseEntity<Object> handleDatabaseError(SisDatabaseException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(generateErrorWithDetail(exception.getMessage(), status, exception.getCause().getMessage()), status);
    }

    @ExceptionHandler(SisNotExistException.class)
    protected ResponseEntity<Object> handleNotExist(SisNotExistException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(generateError(exception.getMessage(), status), status);
    }
}
