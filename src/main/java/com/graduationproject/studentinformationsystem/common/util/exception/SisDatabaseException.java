package com.graduationproject.studentinformationsystem.common.util.exception;

import lombok.extern.slf4j.Slf4j;

import java.io.Serial;

import static com.graduationproject.studentinformationsystem.common.util.exception.util.SisExceptionConstants.DATABASE_ERROR;

@Slf4j
public class SisDatabaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4092737528643939521L;

    public SisDatabaseException(Exception exception) {
        super(DATABASE_ERROR, exception);
        log.error(exception.getMessage());
    }
}
