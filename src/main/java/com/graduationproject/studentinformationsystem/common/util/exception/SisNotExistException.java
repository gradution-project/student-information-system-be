package com.graduationproject.studentinformationsystem.common.util.exception;

import java.io.Serial;

public class SisNotExistException extends Exception {

    @Serial
    private static final long serialVersionUID = -8603482252068341241L;

    public SisNotExistException(final String message) {
        super(message);
    }
}
