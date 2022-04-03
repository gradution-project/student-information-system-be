package com.graduationproject.studentinformationsystem.university.graduation.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentGraduationException extends SisException {

    private StudentGraduationException() {
    }

    public static void throwNotExistException(final String graduationId) throws SisNotExistException {
        SisException.throwNotExistException("STUDENT GRADUATION IS NOT EXIST! graduationId:" + graduationId);
    }

    public static void throwAlreadyWaitingException(final String graduationId) throws SisAlreadyException {
        SisException.throwAlreadyException("STUDENT GRADUATION IS ALREADY WAITING! graduationId:" + graduationId);
    }

    public static void throwAlreadyApprovedException(final String graduationId) throws SisAlreadyException {
        SisException.throwAlreadyException("STUDENT GRADUATION IS ALREADY APPROVED! graduationId:" + graduationId);
    }

    public static void throwAlreadyRejectedException(final String graduationId) throws SisAlreadyException {
        SisException.throwAlreadyException("STUDENT GRADUATION IS ALREADY REJECTED! graduationId:" + graduationId);
    }

    public static void throwAlreadyConfirmedException(final String graduationId) throws SisAlreadyException {
        SisException.throwAlreadyException("STUDENT GRADUATION IS ALREADY CONFIRM! graduationId:" + graduationId);
    }

    public static void throwAlreadyUnconfirmedException(final String graduationId) throws SisAlreadyException {
        SisException.throwAlreadyException("STUDENT GRADUATION IS ALREADY UNCONFIRMED! graduationId:" + graduationId);
    }
}
