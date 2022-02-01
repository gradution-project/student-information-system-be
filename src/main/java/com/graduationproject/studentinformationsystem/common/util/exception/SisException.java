package com.graduationproject.studentinformationsystem.common.util.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class SisException {

    protected SisException() {
    }

    protected static void throwNotExistException(final String message) throws SisNotExistException {
        throw new SisNotExistException(message);
    }

    protected static void throwAlreadyException(final String message) throws SisAlreadyException {
        throw new SisAlreadyException(message);
    }
}
