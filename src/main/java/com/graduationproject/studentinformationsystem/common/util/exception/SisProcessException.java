package com.graduationproject.studentinformationsystem.common.util.exception;

import java.io.Serial;

public class SisProcessException extends Exception {

    @Serial
    private static final long serialVersionUID = 6632162472798435500L;

    public SisProcessException(final String message) {
        super(message);
    }
}
