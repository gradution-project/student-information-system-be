package com.graduationproject.studentinformationsystem.common.util.exception;

import java.io.Serial;

public class SisAlreadyException extends Exception {

    @Serial
    private static final long serialVersionUID = -2380271036959211088L;

    public SisAlreadyException(final String message) {
        super(message);
    }
}
