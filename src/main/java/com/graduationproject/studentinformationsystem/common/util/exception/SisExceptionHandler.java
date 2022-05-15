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
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception,
                                                                  final HttpHeaders httpHeaders,
                                                                  final HttpStatus httpStatus,
                                                                  final WebRequest webRequest) {

        final List<SisSubError> subErrors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> subErrors.add(generateSubError(fieldError)));

        final SisError error = generateErrorWithSubErrors(VALIDATION_ERROR, httpStatus, subErrors);
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(SisDatabaseException.class)
    protected ResponseEntity<Object> handleDatabaseError(final SisDatabaseException databaseException) {
        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        final SisError error = generateErrorWithDetail(databaseException.getMessage(), httpStatus, databaseException.getCause().getMessage());
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(SisNotExistException.class)
    protected ResponseEntity<Object> handleNotExist(final SisNotExistException exception) {
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        final SisError error = generateError(exception.getMessage(), httpStatus);
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(SisAlreadyException.class)
    protected ResponseEntity<Object> handleAlready(final SisAlreadyException exception) {
        final HttpStatus httpStatus = HttpStatus.CONFLICT;
        final SisError error = generateError(exception.getMessage(), httpStatus);
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(SisProcessException.class)
    protected ResponseEntity<Object> handleProcessError(final SisProcessException processException) {
        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        final SisError error = generateError(processException.getMessage(), httpStatus);
        return new ResponseEntity<>(error, httpStatus);
    }
}
